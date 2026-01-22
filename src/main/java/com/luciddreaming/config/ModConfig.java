package com.luciddreaming.config;

import com.luciddreaming.LucidDreaming;
import net.minecraftforge.common.config.Config;
import net.minecraftforge.common.config.ConfigManager;
import net.minecraftforge.fml.client.event.ConfigChangedEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@Config(modid = LucidDreaming.MODID, name = LucidDreaming.NAME, category = "")
public class ModConfig {
    @Config.Name("Server")
    @Config.Comment("Server settings")
    public static Server server = new Server();

    @Config.Name("Web Interface")
    @Config.Comment("Web interface settings")
    public static WebInterface webInterface = new WebInterface();

    public static class Server {
        @Config.Name("Enable Web Server")
        @Config.Comment("Enable or disable the web server interface (default: true)")
        @Config.RequiresWorldRestart
        public boolean enableWebServer = true;

        @Config.Name("HTTP Server Port")
        @Config.Comment("Port number for the HTTP server (default: 1122)")
        @Config.RangeInt(min = 1024, max = 65535)
        @Config.RequiresWorldRestart
        public int httpPort = 1122;

        @Config.Name("Bind Address")
        @Config.Comment("Network interface to bind to. Use '0.0.0.0' for all interfaces (recommended for Android/LAN access), or '127.0.0.1' for localhost only.")
        @Config.RequiresWorldRestart
        public String bindAddress = "0.0.0.0";

        @Config.Name("Enable LAN Access")
        @Config.Comment("Allow devices on the same network to access the HTTP server (recommended for Android devices)")
        public boolean enableLANAccess = true;
    }

    public static class WebInterface {
        @Config.Name("Enable Screenshot")
        @Config.Comment("Enable screenshot functionality in web interface")
        public boolean enableScreenshot = true;

        @Config.Name("Screenshot Quality")
        @Config.Comment("Screenshot quality (0.0 - 1.0)")
        @Config.RangeDouble(min = 0.1, max = 1.0)
        public double screenshotQuality = 0.8;

        @Config.Name("Theme Mode")
        @Config.Comment("Theme mode: 0 = Auto, 1 = Light, 2 = Dark")
        @Config.RangeInt(min = 0, max = 2)
        public int themeMode = 0;

        @Config.Name("Auto Refresh Interval")
        @Config.Comment("Auto refresh interval in milliseconds (0 = disabled)")
        @Config.RangeInt(min = 0, max = 60000)
        public int autoRefreshInterval = 1000;

        @Config.Name("Enable Module Control")
        @Config.Comment("Enable module control panel in web interface")
        public boolean enableModuleControl = true;
    }

    @Mod.EventBusSubscriber(modid = LucidDreaming.MODID)
    public static class ConfigEventHandler {
        @SubscribeEvent
        public static void onConfigChanged(ConfigChangedEvent.OnConfigChangedEvent event) {
            if (event.getModID().equals(LucidDreaming.MODID)) {
                ConfigManager.sync(LucidDreaming.MODID, Config.Type.INSTANCE);
                LucidDreaming.LOGGER.info("Configuration reloaded");
            }
        }
    }
}