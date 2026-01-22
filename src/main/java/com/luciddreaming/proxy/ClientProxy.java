package com.luciddreaming.proxy;

import com.luciddreaming.LucidDreaming;
import com.luciddreaming.modules.*;
import net.minecraft.client.settings.KeyBinding;

public class ClientProxy extends CommonProxy {
    @Override
    public void registerRenderers() {
        // Client-side rendering registration if needed
    }

    @Override
    public void registerModules(ModuleManager moduleManager) {
        // Register AutoFish module
        moduleManager.registerModule(new AutoFish());
        
        // Register AutoClicker module
        moduleManager.registerModule(new AutoClicker());
        
        // Register KillAura module
        moduleManager.registerModule(new KillAura());
        
        // Register AntiKick module
        moduleManager.registerModule(new AntiKick());
        
        // Register NoRender module
        moduleManager.registerModule(new NoRender());
        
        // Register FakeBlackScreen module
        moduleManager.registerModule(new FakeBlackScreen());
    }
    
    @Override
    public void postInit(ModuleManager moduleManager) {
        // Register all key bindings to GameSettings after game is initialized
        registerKeyBindings(moduleManager);
    }
    
    private void registerKeyBindings(ModuleManager moduleManager) {
        try {
            if (LucidDreaming.mc != null && LucidDreaming.mc.gameSettings != null) {
                KeyBinding[] keyBindings = LucidDreaming.mc.gameSettings.keyBindings;
                for (Module module : moduleManager.getModules()) {
                    KeyBinding keyBinding = module.getKeybind().getKeyBinding();
                    if (keyBinding != null) {
                        KeyBinding[] newKeyBindings = new KeyBinding[keyBindings.length + 1];
                        System.arraycopy(keyBindings, 0, newKeyBindings, 0, keyBindings.length);
                        newKeyBindings[keyBindings.length] = keyBinding;
                        LucidDreaming.mc.gameSettings.keyBindings = newKeyBindings;
                        keyBindings = newKeyBindings;
                        LucidDreaming.LOGGER.info("Registered key binding: {}", keyBinding.getKeyDescription());
                    }
                }
            }
        } catch (Exception e) {
            LucidDreaming.LOGGER.error("Failed to register key bindings", e);
        }
    }
}