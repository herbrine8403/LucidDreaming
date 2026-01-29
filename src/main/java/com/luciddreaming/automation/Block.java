package com.luciddreaming.automation;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 积木基类
 * 表示一个编程积木，包含类型、参数和子积木
 */
public class Block {
    private String id;
    private BlockType type;
    private BlockCategory category;
    private Map<String, Object> parameters;
    private List<Block> children;
    private Block nextBlock;

    public Block() {
        this.id = generateId();
        this.parameters = new HashMap<>();
        this.children = new ArrayList<>();
    }

    public Block(BlockType type, BlockCategory category) {
        this();
        this.type = type;
        this.category = category;
    }

    private String generateId() {
        return "block_" + System.currentTimeMillis() + "_" + (int)(Math.random() * 10000);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public BlockType getType() {
        return type;
    }

    public void setType(BlockType type) {
        this.type = type;
    }

    public BlockCategory getCategory() {
        return category;
    }

    public void setCategory(BlockCategory category) {
        this.category = category;
    }

    public Map<String, Object> getParameters() {
        return parameters;
    }

    public void setParameters(Map<String, Object> parameters) {
        this.parameters = parameters;
    }

    public Object getParameter(String key) {
        return parameters.get(key);
    }

    public void setParameter(String key, Object value) {
        parameters.put(key, value);
    }

    public List<Block> getChildren() {
        return children;
    }

    public void setChildren(List<Block> children) {
        this.children = children;
    }

    public void addChild(Block child) {
        children.add(child);
    }

    public void removeChild(String blockId) {
        children.removeIf(b -> b.getId().equals(blockId));
    }

    public Block getNextBlock() {
        return nextBlock;
    }

    public void setNextBlock(Block nextBlock) {
        this.nextBlock = nextBlock;
    }

    /**
     * 将积木序列化为 Map
     */
    public Map<String, Object> toMap() {
        Map<String, Object> map = new HashMap<>();
        map.put("id", id);
        map.put("type", type != null ? type.getValue() : null);
        map.put("category", category != null ? category.getId() : null);
        map.put("parameters", parameters);

        List<Map<String, Object>> childrenList = new ArrayList<>();
        for (Block child : children) {
            childrenList.add(child.toMap());
        }
        map.put("children", childrenList);

        if (nextBlock != null) {
            map.put("next", nextBlock.toMap());
        }

        return map;
    }

    /**
     * 从 Map 反序列化积木
     */
    public static Block fromMap(Map<String, Object> map) {
        Block block = new Block();

        if (map.containsKey("id")) {
            block.setId((String) map.get("id"));
        }

        if (map.containsKey("type")) {
            String typeValue = (String) map.get("type");
            block.setType(BlockType.fromValue(typeValue));
        }

        if (map.containsKey("category")) {
            String categoryId = (String) map.get("category");
            block.setCategory(BlockCategory.fromId(categoryId));
        }

        if (map.containsKey("parameters")) {
            @SuppressWarnings("unchecked")
            Map<String, Object> params = (Map<String, Object>) map.get("parameters");
            block.setParameters(params);
        }

        if (map.containsKey("children")) {
            @SuppressWarnings("unchecked")
            List<Map<String, Object>> childrenList = (List<Map<String, Object>>) map.get("children");
            for (Map<String, Object> childMap : childrenList) {
                block.addChild(Block.fromMap(childMap));
            }
        }

        if (map.containsKey("next")) {
            @SuppressWarnings("unchecked")
            Map<String, Object> nextMap = (Map<String, Object>) map.get("next");
            block.setNextBlock(Block.fromMap(nextMap));
        }

        return block;
    }

    @Override
    public String toString() {
        return "Block{" +
                "id='" + id + '\'' +
                ", type=" + type +
                ", category=" + category +
                ", parameters=" + parameters +
                ", children=" + children.size() +
                '}';
    }
}