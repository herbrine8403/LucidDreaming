package com.luciddreaming.automation;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 自动化任务类
 * 表示一个完整的自动化任务，包含触发器和执行逻辑
 */
public class AutomationTask {
    private String id;
    private String name;
    private String description;
    private boolean enabled;
    private Block trigger;
    private List<Block> blocks;
    private long createdAt;
    private long updatedAt;
    private boolean running;
    private boolean stopped;

    private static final Gson gson = new GsonBuilder()
            .registerTypeAdapter(Block.class, new BlockDeserializer())
            .setPrettyPrinting()
            .create();

    public AutomationTask() {
        this.id = generateId();
        this.blocks = new ArrayList<>();
        this.createdAt = System.currentTimeMillis();
        this.updatedAt = System.currentTimeMillis();
        this.enabled = false;
        this.running = false;
        this.stopped = false;
    }

    public AutomationTask(String name, String description) {
        this();
        this.name = name;
        this.description = description;
    }

    private String generateId() {
        return "task_" + System.currentTimeMillis() + "_" + (int)(Math.random() * 10000);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
        this.updatedAt = System.currentTimeMillis();
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
        this.updatedAt = System.currentTimeMillis();
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
        this.updatedAt = System.currentTimeMillis();
    }

    public Block getTrigger() {
        return trigger;
    }

    public void setTrigger(Block trigger) {
        this.trigger = trigger;
        this.updatedAt = System.currentTimeMillis();
    }

    public List<Block> getBlocks() {
        return blocks;
    }

    public void setBlocks(List<Block> blocks) {
        this.blocks = blocks;
        this.updatedAt = System.currentTimeMillis();
    }

    public void addBlock(Block block) {
        this.blocks.add(block);
        this.updatedAt = System.currentTimeMillis();
    }

    public void removeBlock(String blockId) {
        this.blocks.removeIf(b -> b.getId().equals(blockId));
        this.updatedAt = System.currentTimeMillis();
    }

    public long getCreatedAt() {
        return createdAt;
    }

    public long getUpdatedAt() {
        return updatedAt;
    }

    public boolean isRunning() {
        return running;
    }

    public void setRunning(boolean running) {
        this.running = running;
    }

    public boolean isStopped() {
        return stopped;
    }

    public void setStopped(boolean stopped) {
        this.stopped = stopped;
    }

    public void stop() {
        this.stopped = true;
        this.running = false;
    }

    public void reset() {
        this.stopped = false;
        this.running = false;
    }

    /**
     * 将任务序列化为 JSON 字符串
     */
    public String toJson() {
        return gson.toJson(this);
    }

    /**
     * 从 JSON 字符串反序列化任务
     */
    public static AutomationTask fromJson(String json) {
        return gson.fromJson(json, AutomationTask.class);
    }

    /**
     * 将任务序列化为 Map
     */
    public Map<String, Object> toMap() {
        Map<String, Object> map = new HashMap<>();
        map.put("id", id);
        map.put("name", name);
        map.put("description", description);
        map.put("enabled", enabled);
        map.put("running", running);
        map.put("stopped", stopped);
        map.put("createdAt", createdAt);
        map.put("updatedAt", updatedAt);

        if (trigger != null) {
            map.put("trigger", trigger.toMap());
        }

        List<Map<String, Object>> blocksList = new ArrayList<>();
        for (Block block : blocks) {
            blocksList.add(block.toMap());
        }
        map.put("blocks", blocksList);

        return map;
    }
}