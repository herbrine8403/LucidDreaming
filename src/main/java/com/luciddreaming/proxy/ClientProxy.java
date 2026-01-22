package com.luciddreaming.proxy;

import com.luciddreaming.modules.AutoFish;
import com.luciddreaming.modules.ModuleManager;

public class ClientProxy extends CommonProxy {
    @Override
    public void registerRenderers() {
        // Client-side rendering registration if needed
    }

    @Override
    public void registerModules(ModuleManager moduleManager) {
        // Register AutoFish module
        moduleManager.registerModule(new AutoFish());
        
        // More modules will be added here:
        // moduleManager.registerModule(new AutoClicker());
        // moduleManager.registerModule(new KillAura());
        // moduleManager.registerModule(new AntiKick());
        // moduleManager.registerModule(new AutoWalk());
        // moduleManager.registerModule(new NoRender());
        // moduleManager.registerModule(new FakeBlackScreen());
    }
}