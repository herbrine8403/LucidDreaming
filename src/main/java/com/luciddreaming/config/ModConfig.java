package com.luciddreaming.config;

import com.luciddreaming.LucidDreaming;
import net.minecraftforge.common.config.Config;
import net.minecraftforge.common.config.ConfigManager;
import net.minecraftforge.fml.client.event.ConfigChangedEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@Config(modid = LucidDreaming.MODID, name = LucidDreaming.NAME, category = "")
public class ModConfig {
    @Config.Name("config.luciddreaming.server")
    @Config.Comment("config.luciddreaming.server.comment")
    public static Server server = new Server();

    @Config.Name("config.luciddreaming.web_interface")
    @Config.Comment("config.luciddreaming.web_interface.comment")
    public static WebInterface webInterface = new WebInterface();

    public static class Server {
        @Config.Name("config.luciddreaming.server.enable_web_server")
        @Config.Comment("config.luciddreaming.server.enable_web_server.comment")
        @Config.RequiresWorldRestart
        public boolean enableWebServer = true;

        @Config.Name("config.luciddreaming.server.http_port")
        @Config.Comment("config.luciddreaming.server.http_port.comment")
        @Config.RangeInt(min = 1024, max = 65535)
        @Config.RequiresWorldRestart
        public int httpPort = 1122;

        @Config.Name("config.luciddreaming.server.bind_address")
        @Config.Comment("config.luciddreaming.server.bind_address.comment")
        @Config.RequiresWorldRestart
        public String bindAddress = "0.0.0.0";

        @Config.Name("config.luciddreaming.server.enable_lan_access")
        @Config.Comment("config.luciddreaming.server.enable_lan_access.comment")
        public boolean enableLANAccess = true;
    }

    public static class WebInterface {
        @Config.Name("config.luciddreaming.web_interface.enable_screenshot")
        @Config.Comment("config.luciddreaming.web_interface.enable_screenshot.comment")
        public boolean enableScreenshot = true;

        @Config.Name("config.luciddreaming.web_interface.screenshot_quality")
        @Config.Comment("config.luciddreaming.web_interface.screenshot_quality.comment")
        @Config.RangeDouble(min = 0.1, max = 1.0)
        public double screenshotQuality = 0.8;

        @Config.Name("config.luciddreaming.web_interface.auto_save_screenshots")
        @Config.Comment("config.luciddreaming.web_interface.auto_save_screenshots.comment")
        public boolean autoSaveScreenshots = false;

        @Config.Name("config.luciddreaming.web_interface.screenshot_save_path")
        @Config.Comment("config.luciddreaming.web_interface.screenshot_save_path.comment")
        public String screenshotSavePath = "screenshots/luciddreaming/";

        @Config.Name("config.luciddreaming.web_interface.theme_mode")
        @Config.Comment("config.luciddreaming.web_interface.theme_mode.comment")
        @Config.RangeInt(min = 0, max = 2)
        public int themeMode = 0;

        @Config.Name("config.luciddreaming.web_interface.auto_refresh_interval")
        @Config.Comment("config.luciddreaming.web_interface.auto_refresh_interval.comment")
        @Config.RangeInt(min = 0, max = 60000)
        public int autoRefreshInterval = 1000;

        @Config.Name("config.luciddreaming.web_interface.enable_module_control")
        @Config.Comment("config.luciddreaming.web_interface.enable_module_control.comment")
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