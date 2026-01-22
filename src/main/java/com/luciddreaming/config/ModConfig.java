package com.luciddreaming.config;

import com.luciddreaming.LucidDreaming;
import net.minecraftforge.common.config.Config;
import net.minecraftforge.common.config.ConfigManager;
import net.minecraftforge.fml.client.event.ConfigChangedEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.io.File;

public class ModConfig {
    private File configFile;
    
    public boolean enableWebServer = true; // Enable/disable web server
    public int httpPort = 1122;
    public String bindAddress = "0.0.0.0"; // Bind to all interfaces for Android/LAN support
    public boolean enableLANAccess = true;
    
    public ModConfig(File configFile) {
        this.configFile = configFile;
    }
    
    public void loadConfig() {
        net.minecraftforge.common.config.Configuration config = new net.minecraftforge.common.config.Configuration(configFile);
        config.load();
        
        enableWebServer = config.getBoolean(
            "Enable Web Server",
            "Server",
            true,
            "Enable or disable the web server interface (default: true)"
        );
        
        httpPort = config.getInt(
            "HTTP Server Port",
            "Server",
            1122,
            1024,
            65535,
            "Port number for the HTTP server (default: 1122)"
        );
        
        bindAddress = config.getString(
            "Bind Address",
            "Server",
            "0.0.0.0",
            "Network interface to bind to. Use '0.0.0.0' for all interfaces (recommended for Android/LAN access), or '127.0.0.1' for localhost only."
        );
        
        enableLANAccess = config.getBoolean(
            "Enable LAN Access",
            "Server",
            true,
            "Allow devices on the same network to access the HTTP server (recommended for Android devices)"
        );
        
        config.save();
    }
    
    public void saveConfig() {
        net.minecraftforge.common.config.Configuration config = new net.minecraftforge.common.config.Configuration(configFile);
        config.load();
        
        config.getCategory("Server").get("Enable Web Server").set(enableWebServer);
        config.getCategory("Server").get("HTTP Server Port").set(httpPort);
        config.getCategory("Server").get("Bind Address").set(bindAddress);
        config.getCategory("Server").get("Enable LAN Access").set(enableLANAccess);
        
        config.save();
    }
    
    @Mod.EventBusSubscriber(modid = "luciddreaming")
    public static class ConfigEventHandler {
        @SubscribeEvent
        public static void onConfigChanged(ConfigChangedEvent.OnConfigChangedEvent event) {
            if (event.getModID().equals("luciddreaming")) {
                LucidDreaming.config.saveConfig();
            }
        }
    }
}