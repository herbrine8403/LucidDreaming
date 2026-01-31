package com.luciddreaming.proxy;

import com.luciddreaming.modules.ModuleManager;

public class CommonProxy {
    public void registerRenderers() {
        // Empty in common proxy
    }

    public void registerModules(ModuleManager moduleManager) {
        // Modules will be registered in ClientProxy
    }
    
    public void postInit(ModuleManager moduleManager) {
        // Empty in common proxy
    }
}