package com.luciddreaming.modules;

public enum ModuleCategory {
    SERVER("Server"),
    COMBAT("Combat"),
    MOVEMENT("Movement"),
    PLAYER("Player"),
    RENDER("Render"),
    MISC("Misc");

    private final String name;

    ModuleCategory(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}