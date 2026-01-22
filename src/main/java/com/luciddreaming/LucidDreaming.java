package com.luciddreaming;

import com.luciddreaming.config.ModConfig;
import com.luciddreaming.http.HTTPServer;
import com.luciddreaming.proxy.CommonProxy;
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
    
    public static ModConfig config;
    public static HTTPServer httpServer;
    
    @SidedProxy(clientSide = "com.luciddreaming.proxy.ClientProxy", serverSide = "com.luciddreaming.proxy.CommonProxy")
    public static CommonProxy proxy;
    
    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        LOGGER.info("Initializing Lucid Dreaming Mod...");
        config = new ModConfig(event.getSuggestedConfigurationFile());
        config.loadConfig();
        LOGGER.info("Configuration loaded. HTTP Server Port: {}", config.httpPort);
    }
    
    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {
        if (config.enableWebServer) {
            LOGGER.info("Starting HTTP Server...");
            httpServer = new HTTPServer(config.httpPort, config.bindAddress);
            httpServer.start();
            LOGGER.info("HTTP Server started successfully on {}:{}", config.bindAddress, config.httpPort);
            LOGGER.info("Access game information at http://{}:{}/", config.bindAddress.equals("0.0.0.0") ? "localhost" : config.bindAddress, config.httpPort);
        } else {
            LOGGER.info("Web Server is disabled in configuration. Skipping HTTP server startup.");
        }
    }
    
    @Mod.EventHandler
    public void postInit(FMLPostInitializationEvent event) {
        LOGGER.info("Lucid Dreaming Mod fully initialized!");
    }
}