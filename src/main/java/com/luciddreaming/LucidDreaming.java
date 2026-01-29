package com.luciddreaming;

import com.luciddreaming.automation.AutomationManager;
import com.luciddreaming.config.ModConfig;
import com.luciddreaming.http.HTTPServer;
import com.luciddreaming.modules.Module;
import com.luciddreaming.modules.ModuleManager;
import com.luciddreaming.proxy.CommonProxy;
import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.I18n;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod(modid = LucidDreaming.MODID, name = LucidDreaming.NAME, version = LucidDreaming.VERSION, acceptableRemoteVersions = "*")
public class LucidDreaming {
    public static final String MODID = "luciddreaming";
    public static final String NAME = "Lucid Dreaming";
    public static final String VERSION = "1.0.0";

    public static final Logger LOGGER = LogManager.getLogger(NAME);

    public static HTTPServer httpServer;
    public static ModuleManager moduleManager;
    public static AutomationManager automationManager;
    public static Minecraft mc;

    @SidedProxy(clientSide = "com.luciddreaming.proxy.ClientProxy", serverSide = "com.luciddreaming.proxy.CommonProxy")
    public static CommonProxy proxy;

    public static String getLocalizedName() {
        return I18n.format("mod.luciddreaming.name");
    }

    public static String getLocalizedDescription() {
        return I18n.format("mod.luciddreaming.description");
    }

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        LOGGER.info("Initializing Lucid Dreaming Mod...");
        LOGGER.info("Configuration loaded. HTTP Server Port: {}", ModConfig.server.httpPort);
    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {
        LOGGER.info("Initializing Module Manager...");
        moduleManager = new ModuleManager();
        MinecraftForge.EVENT_BUS.register(moduleManager);

        proxy.registerModules(moduleManager);

        if (ModConfig.server.enableWebServer) {
            LOGGER.info("Starting HTTP Server...");
            httpServer = new HTTPServer(ModConfig.server.httpPort, ModConfig.server.bindAddress);
            httpServer.start();
            LOGGER.info("HTTP Server started successfully on {}:{}", ModConfig.server.bindAddress, ModConfig.server.httpPort);
            LOGGER.info("Access game information at http://{}:{}/", ModConfig.server.bindAddress.equals("0.0.0.0") ? "localhost" : ModConfig.server.bindAddress, ModConfig.server.httpPort);
        } else {
            LOGGER.info("Web Server is disabled in configuration. Skipping HTTP server startup.");
        }

        LOGGER.info("Initializing Automation Manager...");
        automationManager = AutomationManager.getInstance();
        LOGGER.info("Automation Manager initialized with {} tasks", automationManager.getAllTasks().size());
    }

    @Mod.EventHandler
    public void postInit(FMLPostInitializationEvent event) {
        LOGGER.info("Lucid Dreaming Mod fully initialized!");
        LOGGER.info("Registered {} modules", moduleManager.getModules().size());

        // Set Minecraft instance and call proxy postInit
        mc = Minecraft.getMinecraft();
        proxy.postInit(moduleManager);

        // Check localization
        String currentLanguage = mc.gameSettings.language;
        LOGGER.info("=== Localization Debug ===");
        LOGGER.info("Current game language: {}", currentLanguage);
        LOGGER.info("Mod localized name: {}", getLocalizedName());
        LOGGER.info("Mod localized description: {}", getLocalizedDescription());

        // Test module localization
        if (moduleManager != null && !moduleManager.getModules().isEmpty()) {
            Module firstModule = moduleManager.getModules().get(0);
            LOGGER.info("First module: {} (localized: {})", firstModule.getName(), firstModule.getLocalizedName());
            LOGGER.info("First module description: {} (localized: {})", firstModule.getDescription(), firstModule.getLocalizedDescription());
        }
        LOGGER.info("========================");
    }
}