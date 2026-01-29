package com.luciddreaming.http;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.luciddreaming.LucidDreaming;
import com.luciddreaming.automation.*;

import java.io.*;
import java.net.URI;
import java.nio.charset.StandardCharsets;
import java.util.*;

/**
 * 自动化 API 处理器
 * 处理自动化任务的所有 API 请求
 */
public class AutomationAPIHandler implements com.sun.net.httpserver.HttpHandler {
    private static final Gson gson = new GsonBuilder().setPrettyPrinting().create();

    @Override
    public void handle(com.sun.net.httpserver.HttpExchange exchange) throws IOException {
        String method = exchange.getRequestMethod();
        String path = exchange.getRequestURI().getPath();

        try {
            // GET /api/automation/blocks - 获取所有可用积木 (必须在通用匹配之前)
            if ("GET".equals(method) && "/api/automation/blocks".equals(path)) {
                handleGetBlocks(exchange);
            }
            // POST /api/automation/validate - 验证积木脚本
            else if ("POST".equals(method) && "/api/automation/validate".equals(path)) {
                handleValidateBlocks(exchange);
            }
            // GET /api/automation - 获取所有任务
            else if ("GET".equals(method) && "/api/automation".equals(path)) {
                handleGetAllTasks(exchange);
            }
            // POST /api/automation - 创建新任务
            else if ("POST".equals(method) && "/api/automation".equals(path)) {
                handleCreateTask(exchange);
            }
            // POST /api/automation/{id}/run - 运行任务
            else if ("POST".equals(method) && path.startsWith("/api/automation/") && path.endsWith("/run")) {
                handleRunTask(exchange, path);
            }
            // POST /api/automation/{id}/stop - 停止任务
            else if ("POST".equals(method) && path.startsWith("/api/automation/") && path.endsWith("/stop")) {
                handleStopTask(exchange, path);
            }
            // POST /api/automation/{id}/toggle - 切换任务状态
            else if ("POST".equals(method) && path.startsWith("/api/automation/") && path.endsWith("/toggle")) {
                handleToggleTask(exchange, path);
            }
            // GET /api/automation/{id} - 获取指定任务
            else if ("GET".equals(method) && path.startsWith("/api/automation/")) {
                handleGetTask(exchange, path);
            }
            // PUT /api/automation/{id} - 更新任务
            else if ("PUT".equals(method) && path.startsWith("/api/automation/")) {
                handleUpdateTask(exchange, path);
            }
            // DELETE /api/automation/{id} - 删除任务
            else if ("DELETE".equals(method) && path.startsWith("/api/automation/")) {
                handleDeleteTask(exchange, path);
            }
            else {
                sendErrorResponse(exchange, 404, "Not Found");
            }
        } catch (Exception e) {
            LucidDreaming.LOGGER.error("Error handling automation API request", e);
            sendErrorResponse(exchange, 500, e.getMessage());
        }
    }

    /**
     * 获取所有任务
     */
    private void handleGetAllTasks(com.sun.net.httpserver.HttpExchange exchange) throws IOException {
        AutomationManager manager = AutomationManager.getInstance();
        List<AutomationTask> tasks = manager.getAllTasks();

        List<Map<String, Object>> taskList = new ArrayList<>();
        for (AutomationTask task : tasks) {
            taskList.add(task.toMap());
        }

        Map<String, Object> response = new HashMap<>();
        response.put("tasks", taskList);
        response.put("total", tasks.size());
        response.put("running", manager.getRunningTaskCount());

        sendJsonResponse(exchange, 200, response);
    }

    /**
     * 创建新任务
     */
    private void handleCreateTask(com.sun.net.httpserver.HttpExchange exchange) throws IOException {
        String requestBody = readRequestBody(exchange);
        Map<String, Object> data = gson.fromJson(requestBody, Map.class);

        String name = (String) data.get("name");
        String description = (String) data.get("description");

        if (name == null || name.trim().isEmpty()) {
            sendErrorResponse(exchange, 400, "Name is required");
            return;
        }

        AutomationTask task = new AutomationTask(name.trim(), description != null ? description.trim() : "");

        // 如果提供了积木数据，解析它们
        if (data.containsKey("trigger")) {
            Map<String, Object> triggerMap = (Map<String, Object>) data.get("trigger");
            task.setTrigger(Block.fromMap(triggerMap));
        }

        if (data.containsKey("blocks")) {
            List<Map<String, Object>> blocksList = (List<Map<String, Object>>) data.get("blocks");
            for (Map<String, Object> blockMap : blocksList) {
                task.addBlock(Block.fromMap(blockMap));
            }
        }

        AutomationManager.getInstance().addTask(task);

        Map<String, Object> response = new HashMap<>();
        response.put("success", true);
        response.put("task", task.toMap());

        sendJsonResponse(exchange, 201, response);
    }

    /**
     * 获取指定任务
     */
    private void handleGetTask(com.sun.net.httpserver.HttpExchange exchange, String path) throws IOException {
        String id = extractIdFromPath(path, "/api/automation/");

        AutomationTask task = AutomationManager.getInstance().getTask(id);
        if (task == null) {
            sendErrorResponse(exchange, 404, "Task not found");
            return;
        }

        Map<String, Object> response = new HashMap<>();
        response.put("task", task.toMap());

        sendJsonResponse(exchange, 200, response);
    }

    /**
     * 更新任务
     */
    private void handleUpdateTask(com.sun.net.httpserver.HttpExchange exchange, String path) throws IOException {
        String id = extractIdFromPath(path, "/api/automation/");

        AutomationTask task = AutomationManager.getInstance().getTask(id);
        if (task == null) {
            sendErrorResponse(exchange, 404, "Task not found");
            return;
        }

        String requestBody = readRequestBody(exchange);
        Map<String, Object> data = gson.fromJson(requestBody, Map.class);

        // 更新基本信息
        if (data.containsKey("name")) {
            task.setName((String) data.get("name"));
        }
        if (data.containsKey("description")) {
            task.setDescription((String) data.get("description"));
        }
        if (data.containsKey("enabled")) {
            task.setEnabled((Boolean) data.get("enabled"));
        }

        // 更新触发器
        if (data.containsKey("trigger")) {
            Map<String, Object> triggerMap = (Map<String, Object>) data.get("trigger");
            if (triggerMap != null) {
                task.setTrigger(Block.fromMap(triggerMap));
            } else {
                task.setTrigger(null);
            }
        }

        // 更新积木
        if (data.containsKey("blocks")) {
            List<Map<String, Object>> blocksList = (List<Map<String, Object>>) data.get("blocks");
            List<Block> blocks = new ArrayList<>();
            for (Map<String, Object> blockMap : blocksList) {
                blocks.add(Block.fromMap(blockMap));
            }
            
            // 设置积木链
            for (int i = 0; i < blocks.size() - 1; i++) {
                blocks.get(i).setNextBlock(blocks.get(i + 1));
            }
            if (!blocks.isEmpty()) {
                blocks.get(blocks.size() - 1).setNextBlock(null);
            }
            
            task.setBlocks(blocks);
        }

        AutomationManager.getInstance().updateTask(task);

        Map<String, Object> response = new HashMap<>();
        response.put("success", true);
        response.put("task", task.toMap());

        sendJsonResponse(exchange, 200, response);
    }

    /**
     * 删除任务
     */
    private void handleDeleteTask(com.sun.net.httpserver.HttpExchange exchange, String path) throws IOException {
        String id = extractIdFromPath(path, "/api/automation/");

        AutomationManager.getInstance().deleteTask(id);

        Map<String, Object> response = new HashMap<>();
        response.put("success", true);
        response.put("message", "Task deleted");

        sendJsonResponse(exchange, 200, response);
    }

    /**
     * 运行任务
     */
    private void handleRunTask(com.sun.net.httpserver.HttpExchange exchange, String path) throws IOException {
        String id = extractIdFromPath(path, "/api/automation/");
        id = id.substring(0, id.lastIndexOf("/run"));

        AutomationTask task = AutomationManager.getInstance().getTask(id);
        if (task == null) {
            sendErrorResponse(exchange, 404, "Task not found");
            return;
        }

        AutomationManager.getInstance().runTask(id);

        Map<String, Object> response = new HashMap<>();
        response.put("success", true);
        response.put("message", "Task started");

        sendJsonResponse(exchange, 200, response);
    }

    /**
     * 停止任务
     */
    private void handleStopTask(com.sun.net.httpserver.HttpExchange exchange, String path) throws IOException {
        String id = extractIdFromPath(path, "/api/automation/");
        id = id.substring(0, id.lastIndexOf("/stop"));

        AutomationManager.getInstance().stopTask(id);

        Map<String, Object> response = new HashMap<>();
        response.put("success", true);
        response.put("message", "Task stopped");

        sendJsonResponse(exchange, 200, response);
    }

    /**
     * 切换任务状态
     */
    private void handleToggleTask(com.sun.net.httpserver.HttpExchange exchange, String path) throws IOException {
        String id = extractIdFromPath(path, "/api/automation/");
        id = id.substring(0, id.lastIndexOf("/toggle"));

        AutomationManager.getInstance().toggleTask(id);

        AutomationTask task = AutomationManager.getInstance().getTask(id);

        Map<String, Object> response = new HashMap<>();
        response.put("success", true);
        response.put("enabled", task != null && task.isEnabled());

        sendJsonResponse(exchange, 200, response);
    }

    /**
     * 获取所有可用积木
     */
    private void handleGetBlocks(com.sun.net.httpserver.HttpExchange exchange) throws IOException {
        Map<String, Object> response = new HashMap<>();
        Map<String, List<Map<String, Object>>> blocksByCategory = new HashMap<>();

        // 事件积木
        List<Map<String, Object>> eventBlocks = new ArrayList<>();
        eventBlocks.add(createBlockInfo(BlockType.EVENT_SCOREBOARD_CHANGED, BlockCategory.EVENT,
            "当计分板有变化时", "scoreboard"));
        eventBlocks.add(createBlockInfo(BlockType.EVENT_SCOREBOARD_CHANGED_FROM_TO, BlockCategory.EVENT,
            "当计分板从 X 变为 Y 时", "scoreboard", "from", "to"));
        eventBlocks.add(createBlockInfo(BlockType.EVENT_HEALTH_CHANGED, BlockCategory.EVENT,
            "当血量有变化时", "health"));
        eventBlocks.add(createBlockInfo(BlockType.EVENT_HEALTH_CHANGED_FROM_TO, BlockCategory.EVENT,
            "当血量从 X 变为 Y 时", "health", "from", "to"));
        eventBlocks.add(createBlockInfo(BlockType.EVENT_ENTITY_NEARBY, BlockCategory.EVENT,
            "当半径 X 格内有实体时", "radius"));
        blocksByCategory.put("event", eventBlocks);

        // 运动积木
        List<Map<String, Object>> motionBlocks = new ArrayList<>();
        motionBlocks.add(createBlockInfo(BlockType.MOTION_FACE_DIRECTION, BlockCategory.MOTION,
            "面向 X 方向", "yaw"));
        motionBlocks.add(createBlockInfo(BlockType.MOTION_TURN_LEFT, BlockCategory.MOTION,
            "左转 X 度", "degrees"));
        motionBlocks.add(createBlockInfo(BlockType.MOTION_TURN_RIGHT, BlockCategory.MOTION,
            "右转 X 度", "degrees"));
        motionBlocks.add(createBlockInfo(BlockType.MOTION_MOVE_FORWARD, BlockCategory.MOTION,
            "向前移动 X 格", "blocks"));
        motionBlocks.add(createBlockInfo(BlockType.MOTION_MOVE_BACKWARD, BlockCategory.MOTION,
            "向后移动 X 格", "blocks"));
        motionBlocks.add(createBlockInfo(BlockType.MOTION_MOVE_LEFT, BlockCategory.MOTION,
            "向左移动 X 格", "blocks"));
        motionBlocks.add(createBlockInfo(BlockType.MOTION_MOVE_RIGHT, BlockCategory.MOTION,
            "向右移动 X 格", "blocks"));
        motionBlocks.add(createBlockInfo(BlockType.MOTION_MOVE_TO, BlockCategory.MOTION,
            "移动到 XYZ 坐标", "x", "y", "z"));
        motionBlocks.add(createBlockInfo(BlockType.MOTION_INTERACT, BlockCategory.MOTION,
            "与面前的实体互动", ""));
        motionBlocks.add(createBlockInfo(BlockType.MOTION_INTERACT_NEAREST, BlockCategory.MOTION,
            "与半径 X 格内最近的实体互动", "radius"));
        blocksByCategory.put("motion", motionBlocks);

        // 侦测积木
        List<Map<String, Object>> sensingBlocks = new ArrayList<>();
        sensingBlocks.add(createBlockInfo(BlockType.SENSING_SCOREBOARD_CHANGED, BlockCategory.SENSING,
            "计分板有变化？", "scoreboard"));
        sensingBlocks.add(createBlockInfo(BlockType.SENSING_SCOREBOARD_CHANGED_FROM_TO, BlockCategory.SENSING,
            "计分板从 X 变为 Y？", "scoreboard", "from", "to"));
        sensingBlocks.add(createBlockInfo(BlockType.SENSING_HEALTH_CHANGED, BlockCategory.SENSING,
            "血量有变化？", "health"));
        sensingBlocks.add(createBlockInfo(BlockType.SENSING_HEALTH_CHANGED_FROM_TO, BlockCategory.SENSING,
            "血量从 X 变为 Y？", "health", "from", "to"));
        sensingBlocks.add(createBlockInfo(BlockType.SENSING_ENTITY_NEARBY, BlockCategory.SENSING,
            "半径 X 格内有实体？", "radius"));
        blocksByCategory.put("sensing", sensingBlocks);

        // 控制积木
        List<Map<String, Object>> controlBlocks = new ArrayList<>();
        controlBlocks.add(createBlockInfo(BlockType.CONTROL_WAIT, BlockCategory.CONTROL,
            "等待 X 秒", "seconds"));
        controlBlocks.add(createBlockInfo(BlockType.CONTROL_IF, BlockCategory.CONTROL,
            "如果 条件 那么", "condition"));
        controlBlocks.add(createBlockInfo(BlockType.CONTROL_IF_ELSE, BlockCategory.CONTROL,
            "如果 条件 那么 否则", "condition"));
        controlBlocks.add(createBlockInfo(BlockType.CONTROL_REPEAT, BlockCategory.CONTROL,
            "重复 X 次", "times"));
        controlBlocks.add(createBlockInfo(BlockType.CONTROL_FOREVER, BlockCategory.CONTROL,
            "永远", ""));
        controlBlocks.add(createBlockInfo(BlockType.CONTROL_STOP, BlockCategory.CONTROL,
            "停止", ""));
        controlBlocks.add(createBlockInfo(BlockType.CONTROL_COMMENT, BlockCategory.CONTROL,
            "注释", "text"));
        blocksByCategory.put("control", controlBlocks);

        response.put("blocks", blocksByCategory);
        response.put("categories", getCategories());

        sendJsonResponse(exchange, 200, response);
    }

    /**
     * 验证积木脚本
     */
    private void handleValidateBlocks(com.sun.net.httpserver.HttpExchange exchange) throws IOException {
        String requestBody = readRequestBody(exchange);
        Map<String, Object> data = gson.fromJson(requestBody, Map.class);

        List<Map<String, Object>> blocks = (List<Map<String, Object>>) data.get("blocks");
        Map<String, Object> trigger = (Map<String, Object>) data.get("trigger");

        List<String> errors = new ArrayList<>();

        // 验证触发器（如果有）
        if (trigger != null) {
            validateBlock(trigger, errors, "trigger");
        }

        // 验证积木
        if (blocks != null) {
            for (int i = 0; i < blocks.size(); i++) {
                validateBlock(blocks.get(i), errors, "block_" + i);
            }
        }

        Map<String, Object> response = new HashMap<>();
        response.put("valid", errors.isEmpty());
        response.put("errors", errors);

        sendJsonResponse(exchange, 200, response);
    }

    /**
     * 验证单个积木
     */
    private void validateBlock(Map<String, Object> blockData, List<String> errors, String prefix) {
        String type = (String) blockData.get("type");
        if (type == null) {
            errors.add(prefix + ": Missing type");
            return;
        }

        BlockType blockType = BlockType.fromValue(type);
        if (blockType == null) {
            errors.add(prefix + ": Invalid block type: " + type);
            return;
        }

        // 验证必需参数
        Map<String, Object> parameters = (Map<String, Object>) blockData.get("parameters");
        if (parameters == null) {
            return;
        }

        // 根据积木类型验证参数
        switch (blockType) {
            case MOTION_FACE_DIRECTION:
                if (!parameters.containsKey("yaw")) {
                    errors.add(prefix + ": Missing required parameter: yaw");
                }
                break;
            case MOTION_TURN_LEFT:
            case MOTION_TURN_RIGHT:
                if (!parameters.containsKey("degrees")) {
                    errors.add(prefix + ": Missing required parameter: degrees");
                }
                break;
            case MOTION_MOVE_FORWARD:
            case MOTION_MOVE_BACKWARD:
            case MOTION_MOVE_LEFT:
            case MOTION_MOVE_RIGHT:
                if (!parameters.containsKey("blocks")) {
                    errors.add(prefix + ": Missing required parameter: blocks");
                }
                break;
            case MOTION_MOVE_TO:
                if (!parameters.containsKey("x") || !parameters.containsKey("y") || !parameters.containsKey("z")) {
                    errors.add(prefix + ": Missing required parameters: x, y, z");
                }
                break;
            case MOTION_INTERACT_NEAREST:
                if (!parameters.containsKey("radius")) {
                    errors.add(prefix + ": Missing required parameter: radius");
                }
                break;
            case CONTROL_WAIT:
                if (!parameters.containsKey("seconds")) {
                    errors.add(prefix + ": Missing required parameter: seconds");
                }
                break;
            case CONTROL_REPEAT:
                if (!parameters.containsKey("times")) {
                    errors.add(prefix + ": Missing required parameter: times");
                }
                break;
        }
    }

    /**
     * 创建积木信息
     */
    private Map<String, Object> createBlockInfo(BlockType type, BlockCategory category, String displayName, String... params) {
        Map<String, Object> info = new HashMap<>();
        info.put("type", type.getValue());
        info.put("category", category.getId());
        info.put("displayName", displayName);
        info.put("parameters", Arrays.asList(params));
        return info;
    }

    /**
     * 获取分类信息
     */
    private List<Map<String, Object>> getCategories() {
        List<Map<String, Object>> categories = new ArrayList<>();

        for (BlockCategory category : BlockCategory.values()) {
            Map<String, Object> catInfo = new HashMap<>();
            catInfo.put("id", category.getId());
            catInfo.put("displayName", category.getDisplayName());
            catInfo.put("color", category.getColor());
            categories.add(catInfo);
        }

        return categories;
    }

    /**
     * 从路径中提取 ID
     */
    private String extractIdFromPath(String path, String prefix) {
        return path.substring(prefix.length());
    }

    /**
     * 读取请求体
     */
    private String readRequestBody(com.sun.net.httpserver.HttpExchange exchange) throws IOException {
        try (BufferedReader reader = new BufferedReader(
                new InputStreamReader(exchange.getRequestBody(), StandardCharsets.UTF_8))) {
            StringBuilder sb = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                sb.append(line);
            }
            return sb.toString();
        }
    }

    /**
     * 发送 JSON 响应
     */
    private void sendJsonResponse(com.sun.net.httpserver.HttpExchange exchange, int statusCode, Object data) throws IOException {
        String json = gson.toJson(data);
        exchange.getResponseHeaders().set("Content-Type", "application/json");
        exchange.sendResponseHeaders(statusCode, json.getBytes(StandardCharsets.UTF_8).length);
        try (OutputStream os = exchange.getResponseBody()) {
            os.write(json.getBytes(StandardCharsets.UTF_8));
        }
    }

    /**
     * 发送错误响应
     */
    private void sendErrorResponse(com.sun.net.httpserver.HttpExchange exchange, int statusCode, String message) throws IOException {
        Map<String, Object> error = new HashMap<>();
        error.put("error", message);

        sendJsonResponse(exchange, statusCode, error);
    }
}