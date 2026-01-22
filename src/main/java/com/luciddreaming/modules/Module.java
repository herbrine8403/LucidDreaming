package com.luciddreaming.modules;

import com.luciddreaming.utils.Keybind;
import com.luciddreaming.LucidDreaming;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.InputEvent;
import org.lwjgl.input.Keyboard;

public abstract class Module {
    protected String name;
    protected String description;
    protected ModuleCategory category;
    protected boolean enabled;
    protected Keybind keybind;

    public Module(String name, String description, ModuleCategory category) {
        this.name = name;
        this.description = description;
        this.category = category;
        this.enabled = false;
        this.keybind = new Keybind("key." + name.toLowerCase(), description);
    }

    public Module(String name, String description, ModuleCategory category, int defaultKey) {
        this(name, description, category);
        this.keybind.setKeyCode(defaultKey);
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public ModuleCategory getCategory() {
        return category;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        if (this.enabled != enabled) {
            this.enabled = enabled;
            if (enabled) {
                onEnable();
                LucidDreaming.LOGGER.info("Module '{}' enabled", name);
            } else {
                onDisable();
                LucidDreaming.LOGGER.info("Module '{}' disabled", name);
            }
        }
    }

    public void toggle() {
        setEnabled(!enabled);
    }

    public Keybind getKeybind() {
        return keybind;
    }

    public void setKeybind(int keyCode) {
        keybind.setKeyCode(keyCode);
    }

    protected void onEnable() {
        // Override in subclasses
    }

    protected void onDisable() {
        // Override in subclasses
    }

    @SubscribeEvent
    public void onKeyInput(InputEvent.KeyInputEvent event) {
        if (keybind.isPressed()) {
            toggle();
        }
    }

    public abstract void onTick();
}