package com.luciddreaming.modules;

import net.minecraft.client.resources.I18n;

public enum ModuleCategory {
    SERVER("Server", "server"),
    COMBAT("Combat", "combat"),
    MOVEMENT("Movement", "movement"),
    PLAYER("Player", "player"),
    RENDER("Render", "render"),
    MISC("Misc", "misc");

    private final String name;
    private final String key;

    ModuleCategory(String name, String key) {
        this.name = name;
        this.key = key;
    }

    public String getName() {
        return name;
    }

    public String getLocalizedName() {
        return I18n.format("category.luciddreaming." + key);
    }
}