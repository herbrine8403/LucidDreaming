package com.luciddreaming.proxy;

import com.luciddreaming.modules.*;

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
}