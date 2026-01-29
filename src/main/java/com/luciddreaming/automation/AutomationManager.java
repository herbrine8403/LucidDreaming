package com.luciddreaming.automation;

import com.luciddreaming.LucidDreaming;

import java.io.*;
import java.util.*;
import java.util.concurrent.*;

/**
 * 自动化管理器
 * 负责管理所有自动化任务的生命周期
 */
public class AutomationManager {
    private static AutomationManager instance;

    private final Map<String, AutomationTask> tasks;
    private final File automationFile;
    private final ExecutorService executorService;
    private final Map<String, Future<?>> runningTasks;

    private static final String AUTOMATION_DIR = "config/automation";
    private static final String AUTOMATION_FILE = "config/automation/tasks.json";

    private AutomationManager() {
        this.tasks = new ConcurrentHashMap<>();
        this.executorService = Executors.newCachedThreadPool();
        this.runningTasks = new ConcurrentHashMap<>();

        // 创建自动化目录
        File dir = new File(AUTOMATION_DIR);
        if (!dir.exists()) {
            dir.mkdirs();
        }

        this.automationFile = new File(AUTOMATION_FILE);

        // 加载保存的任务
        loadTasks();
    }

    public static AutomationManager getInstance() {
        if (instance == null) {
            instance = new AutomationManager();
        }
        return instance;
    }

    /**
     * 添加任务
     */
    public void addTask(AutomationTask task) {
        tasks.put(task.getId(), task);
        saveTasks();
    }

    /**
     * 获取任务
     */
    public AutomationTask getTask(String id) {
        return tasks.get(id);
    }

    /**
     * 获取所有任务
     */
    public List<AutomationTask> getAllTasks() {
        return new ArrayList<>(tasks.values());
    }

    /**
     * 更新任务
     */
    public void updateTask(AutomationTask task) {
        if (tasks.containsKey(task.getId())) {
            tasks.put(task.getId(), task);
            saveTasks();
        }
    }

    /**
     * 删除任务
     */
    public void deleteTask(String id) {
        // 停止正在运行的任务
        stopTask(id);

        tasks.remove(id);
        saveTasks();
    }

    /**
     * 运行任务（手动触发，忽略触发条件）
     */
    public void runTask(String id) {
        AutomationTask task = tasks.get(id);
        if (task == null) {
            LucidDreaming.LOGGER.warn("Task not found: {}", id);
            return;
        }

        if (task.isRunning()) {
            LucidDreaming.LOGGER.warn("Task is already running: {}", id);
            return;
        }

        task.reset();
        task.setRunning(true);

        Future<?> future = executorService.submit(() -> {
            try {
                LucidDreaming.LOGGER.info("Running task: {}", task.getName());
                BlockInterpreter interpreter = new BlockInterpreter(task);
                interpreter.execute();
                LucidDreaming.LOGGER.info("Task completed: {}", task.getName());
            } catch (Exception e) {
                LucidDreaming.LOGGER.error("Error running task: {}", task.getName(), e);
            } finally {
                task.setRunning(false);
                runningTasks.remove(id);
            }
        });

        runningTasks.put(id, future);
    }

    /**
     * 停止任务
     */
    public void stopTask(String id) {
        AutomationTask task = tasks.get(id);
        if (task == null) {
            return;
        }

        task.stop();

        Future<?> future = runningTasks.get(id);
        if (future != null) {
            future.cancel(true);
            runningTasks.remove(id);
        }

        LucidDreaming.LOGGER.info("Task stopped: {}", task.getName());
    }

    /**
     * 切换任务启用状态
     */
    public void toggleTask(String id) {
        AutomationTask task = tasks.get(id);
        if (task != null) {
            task.setEnabled(!task.isEnabled());
            saveTasks();
        }
    }

    /**
     * 启用任务
     */
    public void enableTask(String id) {
        AutomationTask task = tasks.get(id);
        if (task != null) {
            task.setEnabled(true);
            saveTasks();
        }
    }

    /**
     * 禁用任务
     */
    public void disableTask(String id) {
        AutomationTask task = tasks.get(id);
        if (task != null) {
            task.setEnabled(false);
            saveTasks();
            // 如果任务正在运行，停止它
            if (task.isRunning()) {
                stopTask(id);
            }
        }
    }

    /**
     * 保存任务到文件
     */
    private void saveTasks() {
        try {
            StringBuilder json = new StringBuilder();
            json.append("[\n");

            boolean first = true;
            for (AutomationTask task : tasks.values()) {
                if (!first) {
                    json.append(",\n");
                }
                json.append("  ").append(task.toJson());
                first = false;
            }

            json.append("\n]");

            try (FileWriter writer = new FileWriter(automationFile)) {
                writer.write(json.toString());
            }

            LucidDreaming.LOGGER.debug("Saved {} automation tasks", tasks.size());
        } catch (IOException e) {
            LucidDreaming.LOGGER.error("Failed to save automation tasks", e);
        }
    }

    /**
     * 从文件加载任务
     */
    private void loadTasks() {
        if (!automationFile.exists()) {
            LucidDreaming.LOGGER.info("No automation tasks file found, starting with empty list");
            return;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(automationFile))) {
            StringBuilder json = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                json.append(line);
            }

            // 简单解析 JSON 数组
            String content = json.toString();
            content = content.trim();
            if (content.startsWith("[") && content.endsWith("]")) {
                content = content.substring(1, content.length() - 1);

                String[] taskJsons = splitJsonArray(content);
                for (String taskJson : taskJsons) {
                    if (!taskJson.trim().isEmpty()) {
                        try {
                            AutomationTask task = AutomationTask.fromJson(taskJson.trim());
                            tasks.put(task.getId(), task);
                        } catch (Exception e) {
                            LucidDreaming.LOGGER.error("Failed to parse task: {}", taskJson, e);
                        }
                    }
                }
            }

            LucidDreaming.LOGGER.info("Loaded {} automation tasks", tasks.size());
        } catch (IOException e) {
            LucidDreaming.LOGGER.error("Failed to load automation tasks", e);
        }
    }

    /**
     * 简单的 JSON 数组分割（用于避免依赖复杂的 JSON 库）
     */
    private String[] splitJsonArray(String json) {
        List<String> parts = new ArrayList<>();
        int depth = 0;
        int start = 0;
        boolean inString = false;

        for (int i = 0; i < json.length(); i++) {
            char c = json.charAt(i);

            if (c == '"' && (i == 0 || json.charAt(i - 1) != '\\')) {
                inString = !inString;
            }

            if (!inString) {
                if (c == '{') {
                    depth++;
                } else if (c == '}') {
                    depth--;
                    if (depth == 0 && i > start) {
                        parts.add(json.substring(start, i + 1));
                        start = i + 1;
                    }
                }
            }
        }

        return parts.toArray(new String[0]);
    }

    /**
     * 清空所有任务
     */
    public void clearAllTasks() {
        // 停止所有正在运行的任务
        for (String id : new ArrayList<>(runningTasks.keySet())) {
            stopTask(id);
        }

        tasks.clear();
        saveTasks();
    }

    /**
     * 获取运行中的任务数量
     */
    public int getRunningTaskCount() {
        return runningTasks.size();
    }

    /**
     * 关闭管理器
     */
    public void shutdown() {
        // 停止所有正在运行的任务
        for (String id : new ArrayList<>(runningTasks.keySet())) {
            stopTask(id);
        }

        executorService.shutdown();
        try {
            if (!executorService.awaitTermination(5, TimeUnit.SECONDS)) {
                executorService.shutdownNow();
            }
        } catch (InterruptedException e) {
            executorService.shutdownNow();
            Thread.currentThread().interrupt();
        }
    }
}
