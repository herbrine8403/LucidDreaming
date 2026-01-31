package com.luciddreaming.modules;

import com.luciddreaming.LucidDreaming;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ModuleManager {
    private final List<Module> modules = new ArrayList<>();
    private final Map<String, Module> moduleMap = new HashMap<>();

    public void registerModule(Module module) {
        modules.add(module);
        moduleMap.put(module.getName().toLowerCase(), module);
        LucidDreaming.LOGGER.info("Registered module: {}", module.getName());
    }

    public Module getModule(String name) {
        return moduleMap.get(name.toLowerCase());
    }

    public List<Module> getModules() {
        return new ArrayList<>(modules);
    }

    public List<Module> getModulesByCategory(ModuleCategory category) {
        List<Module> result = new ArrayList<>();
        for (Module module : modules) {
            if (module.getCategory() == category) {
                result.add(module);
            }
        }
        return result;
    }

    @SubscribeEvent
    public void onClientTick(TickEvent.ClientTickEvent event) {
        if (event.phase == TickEvent.Phase.END) {
            // Process main thread tasks
            com.luciddreaming.utils.MainThreadTaskQueue.processTasks();
            
            // Run enabled modules
            for (Module module : modules) {
                if (module.isEnabled()) {
                    module.onTick();
                }
            }
        }
    }
}