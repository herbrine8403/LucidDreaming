package com.luciddreaming.modules;

import com.luciddreaming.utils.Keybind;
import com.luciddreaming.LucidDreaming;
import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.I18n;
import net.minecraft.util.text.TextComponentString;
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
        // Default to no key binding (KEY_NONE)
        this.keybind = new Keybind("key.luciddreaming." + name.toLowerCase(), description, Keyboard.KEY_NONE);
    }

    public String getName() {
        return name;
    }

    public String getLocalizedName() {
        // 直接使用驼峰转小写作为键，例如 AutoFish -> autofish
        String formattedName = name.toLowerCase();
        String fullKey = "module.luciddreaming." + formattedName + ".name";
        String localized = I18n.format(fullKey);
        // 如果本地化返回键本身或包含键前缀，使用默认名称
        if (localized.equals(fullKey) || localized.startsWith("module.luciddreaming.")) {
            return name;
        }
        return localized;
    }

    public String getDescription() {
        return description;
    }

    public String getLocalizedDescription() {
        // 直接使用驼峰转小写作为键，例如 AutoFish -> autofish
        String formattedName = name.toLowerCase();
        String fullKey = "module.luciddreaming." + formattedName + ".description";
        String localized = I18n.format(fullKey);
        // 如果本地化返回键本身或包含键前缀，使用默认描述
        if (localized.equals(fullKey) || localized.startsWith("module.luciddreaming.")) {
            return description;
        }
        return localized;
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
                sendLocalChatMessage(I18n.format("message.luciddreaming.enabled", getLocalizedName()));
            } else {
                onDisable();
                LucidDreaming.LOGGER.info("Module '{}' disabled", name);
                sendLocalChatMessage(I18n.format("message.luciddreaming.disabled", getLocalizedName()));
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

    /**
     * Send a local chat message that is only visible to the player
     * @param message The message to send
     */
    protected void sendLocalChatMessage(String message) {
        if (Minecraft.getMinecraft().player != null) {
            TextComponentString textComponent = new TextComponentString(message);
            Minecraft.getMinecraft().player.sendMessage(textComponent);
        }
    }
}