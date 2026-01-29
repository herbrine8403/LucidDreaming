package com.luciddreaming.http;

import com.luciddreaming.LucidDreaming;
import com.luciddreaming.automation.AutomationEngine;
import com.luciddreaming.automation.AutomationTask;
import com.luciddreaming.automation.AutomationInstruction;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

public class AutomationAPIHandler implements HttpHandler {
    // 自动化引擎实例
    private static AutomationEngine automationEngine = LucidDreaming.automationEngine;
    
    // 存储自动化任务的简单列表（在实际实现中，这应该连接到持久化存储）
    private static List<Map<String, Object>> automations = new ArrayList<>();
    
    static {
        // 初始化一些示例自动化任务
        Map<String, Object> example1 = new HashMap<>();
        example1.put("id", "1");
        example1.put("name", "示例自动化1");
        example1.put("description", "这是一个示例自动化任务");
        example1.put("type", "movement");
        example1.put("enabled", false);
        example1.put("trigger", "手动");
        automations.add(example1);
        
        Map<String, Object> example2 = new HashMap<>();
        example2.put("id", "2");
        example2.put("name", "示例自动化2");
        example2.put("description", "另一个示例自动化任务");
        example2.put("type", "detection");
        example2.put("enabled", true);
        example2.put("trigger", "计分板变化");
        automations.add(example2);
    }

    @Override
    public void handle(HttpExchange exchange) throws IOException {
        String method = exchange.getRequestMethod();
        String path = exchange.getRequestURI().getPath();
        String query = exchange.getRequestURI().getQuery();
        
        // 解析路径参数
        String[] pathParts = path.split("/");
        String automationId = null;
        if (pathParts.length >= 3) {
            automationId = pathParts[3]; // /api/automation/{id}
        }
        
        if ("GET".equals(method)) {
            handleGet(exchange, automationId);
        } else if ("POST".equals(method)) {
            handlePost(exchange, automationId);
        } else if ("DELETE".equals(method)) {
            handleDelete(exchange, automationId);
        } else {
            sendResponse(exchange, 405, "{\"error\":\"Method not allowed\"}");
        }
    }
    
    private void handleGet(HttpExchange exchange, String automationId) throws IOException {
        if (automationId == null) {
            // 返回所有自动化任务
            StringBuilder response = new StringBuilder();
            response.append("{\"automations\":[");
            
            for (int i = 0; i < automations.size(); i++) {
                Map<String, Object> automation = automations.get(i);
                if (i > 0) response.append(",");
                
                response.append("{");
                response.append("\"id\":\"").append(automation.get("id")).append("\" ,");
                response.append("\"name\":\"").append(automation.get("name")).append("\" ,");
                response.append("\"description\":\"").append(automation.get("description")).append("\" ,");
                response.append("\"type\":\"").append(automation.get("type")).append("\" ,");
                response.append("\"enabled\":").append(automation.get("enabled")).append(",");
                response.append("\"trigger\":\"").append(automation.get("trigger")).append("\"");
                response.append("}");
            }
            
            response.append("]}");
            sendResponse(exchange, 200, response.toString());
        } else {
            // 返回特定自动化任务
            Map<String, Object> automation = findAutomationById(automationId);
            if (automation != null) {
                StringBuilder response = new StringBuilder();
                response.append("{");
                response.append("\"id\":\"").append(automation.get("id")).append("\" ,");
                response.append("\"name\":\"").append(automation.get("name")).append("\" ,");
                response.append("\"description\":\"").append(automation.get("description")).append("\" ,");
                response.append("\"type\":\"").append(automation.get("type")).append("\" ,");
                response.append("\"enabled\":").append(automation.get("enabled")).append(",");
                response.append("\"trigger\":\"").append(automation.get("trigger")).append("\"");
                response.append("}");
                sendResponse(exchange, 200, response.toString());
            } else {
                sendResponse(exchange, 404, "{\"error\":\"Automation not found\"}");
            }
        }
    }
    
    private void handlePost(HttpExchange exchange, String automationId) throws IOException {
        String body = new String(exchange.getRequestBody().readAllBytes(), StandardCharsets.UTF_8);
        Map<String, Object> requestData = LucidDreaming.gson.fromJson(body, Map.class);
        
        if (automationId == null) {
            // 创建新的自动化任务
            String action = (String) requestData.get("action");
            if ("create".equals(action)) {
                // 创建新任务
                String id = String.valueOf(System.currentTimeMillis()); // 简单的ID生成
                String name = (String) requestData.get("name");
                String description = (String) requestData.get("description");
                String type = (String) requestData.get("type");
                boolean enabled = (Boolean) requestData.getOrDefault("enabled", false);
                String trigger = (String) requestData.get("trigger");
                
                Map<String, Object> newAutomation = new HashMap<>();
                newAutomation.put("id", id);
                newAutomation.put("name", name);
                newAutomation.put("description", description);
                newAutomation.put("type", type);
                newAutomation.put("enabled", enabled);
                newAutomation.put("trigger", trigger);
                
                automations.add(newAutomation);
                
                // 如果任务是启用状态，添加到自动化引擎
                if (enabled) {
                    // 在实际实现中，这里应该将任务添加到自动化引擎
                }
                
                String response = "{\"success\":true,\"id\":\"" + id + "\"}";
                sendResponse(exchange, 200, response);
            } else {
                sendResponse(exchange, 400, "{\"error\":\"Action 'create' is required for new automation\"}");
            }
        } else {
            // 处理自动化任务的启用/禁用或更新
            String action = (String) requestData.get("action");
            
            if ("toggle".equals(action)) {
                // 切换自动化任务状态
                Map<String, Object> automation = findAutomationById(automationId);
                if (automation != null) {
                    boolean currentState = (Boolean) automation.get("enabled");
                    boolean newState = !currentState;
                    automation.put("enabled", newState);
                    
                    // 更新自动化引擎中的任务状态
                    if (newState) {
                        automationEngine.startTask(automationId);
                    } else {
                        automationEngine.stopTask(automationId);
                    }
                    
                    String response = "{\"success\":true,\"enabled\":" + newState + "}";
                    sendResponse(exchange, 200, response);
                } else {
                    sendResponse(exchange, 404, "{\"error\":\"Automation not found\"}");
                }
            } else if ("update".equals(action)) {
                // 更新自动化任务配置
                Map<String, Object> automation = findAutomationById(automationId);
                if (automation != null) {
                    // 更新可更新的字段
                    if (requestData.containsKey("name")) {
                        automation.put("name", requestData.get("name"));
                    }
                    if (requestData.containsKey("description")) {
                        automation.put("description", requestData.get("description"));
                    }
                    if (requestData.containsKey("type")) {
                        automation.put("type", requestData.get("type"));
                    }
                    if (requestData.containsKey("trigger")) {
                        automation.put("trigger", requestData.get("trigger"));
                    }
                    if (requestData.containsKey("enabled")) {
                        boolean newEnabled = (Boolean) requestData.get("enabled");
                        automation.put("enabled", newEnabled);
                        
                        // 更新自动化引擎中的任务状态
                        if (newEnabled) {
                            automationEngine.startTask(automationId);
                        } else {
                            automationEngine.stopTask(automationId);
                        }
                    }
                    
                    String response = "{\"success\":true}";
                    sendResponse(exchange, 200, response);
                } else {
                    sendResponse(exchange, 404, "{\"error\":\"Automation not found\"}");
                }
            } else if ("execute".equals(action)) {
                // 立即执行自动化任务
                Map<String, Object> automation = findAutomationById(automationId);
                if (automation != null) {
                    // 在自动化引擎中执行任务
                    automationEngine.executeTask(automationId);
                    String response = "{\"success\":true,\"message\":\"Task executed\"}";
                    sendResponse(exchange, 200, response);
                } else {
                    sendResponse(exchange, 404, "{\"error\":\"Automation not found\"}");
                }
            } else {
                sendResponse(exchange, 400, "{\"error\":\"Invalid action\"}");
            }
        }
    }
    
    private void handleDelete(HttpExchange exchange, String automationId) throws IOException {
        if (automationId == null) {
            sendResponse(exchange, 400, "{\"error\":\"Automation ID is required\"}");
            return;
        }
        
        // 删除特定自动化任务
        Map<String, Object> automation = findAutomationById(automationId);
        if (automation != null) {
            // 先停止任务
            automationEngine.stopTask(automationId);
            
            // 然后从列表中移除
            automations.remove(automation);
            String response = "{\"success\":true}";
            sendResponse(exchange, 200, response);
        } else {
            sendResponse(exchange, 404, "{\"error\":\"Automation not found\"}");
        }
    }
    
    private Map<String, Object> findAutomationById(String id) {
        for (Map<String, Object> automation : automations) {
            if (id.equals(automation.get("id"))) {
                return automation;
            }
        }
        return null;
    }
    
    private void sendResponse(HttpExchange exchange, int statusCode, String response) throws IOException {
        exchange.getResponseHeaders().set("Content-Type", "application/json; charset=utf-8");
        exchange.sendResponseHeaders(statusCode, response.getBytes(StandardCharsets.UTF_8).length);
        try (OutputStream os = exchange.getResponseBody()) {
            os.write(response.getBytes(StandardCharsets.UTF_8));
        }
    }
}