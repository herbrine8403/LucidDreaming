package com.luciddreaming.automation;

/**
 * 积木分类枚举
 */
public enum BlockCategory {
    EVENT("event", "事件", "#FF6B6B"),
    MOTION("motion", "运动", "#4ECDC4"),
    SENSING("sensing", "侦测", "#FFE66D"),
    CONTROL("control", "控制", "#95E1D3");

    private final String id;
    private final String displayName;
    private final String color;

    BlockCategory(String id, String displayName, String color) {
        this.id = id;
        this.displayName = displayName;
        this.color = color;
    }

    public String getId() {
        return id;
    }

    public String getDisplayName() {
        return displayName;
    }

    public String getColor() {
        return color;
    }

    public static BlockCategory fromId(String id) {
        for (BlockCategory category : BlockCategory.values()) {
            if (category.id.equals(id)) {
                return category;
            }
        }
        return null;
    }
}