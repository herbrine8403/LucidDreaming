package com.luciddreaming.utils;

import net.minecraft.client.settings.KeyBinding;
import org.lwjgl.input.Keyboard;

public class Keybind {
    private String name;
    private KeyBinding keyBinding;

    public Keybind(String name, String description, int keyCode) {
        this.name = name;
        // The name parameter already includes the full key name with prefix
        this.keyBinding = new KeyBinding(name, keyCode, "Lucid Dreaming");
    }

    public Keybind(String name, String description) {
        this(name, description, Keyboard.KEY_NONE);
    }

    public boolean isPressed() {
        return keyBinding != null && keyBinding.isPressed();
    }

    public int getKeyCode() {
        return keyBinding != null ? keyBinding.getKeyCode() : Keyboard.KEY_NONE;
    }

    public void setKeyCode(int keyCode) {
        if (keyBinding != null) {
            keyBinding.setKeyCode(keyCode);
        }
    }

    public String getKeyName() {
        return Keyboard.getKeyName(getKeyCode());
    }

    public KeyBinding getKeyBinding() {
        return keyBinding;
    }

    public String getName() {
        return name;
    }
}