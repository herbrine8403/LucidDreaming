package com.luciddreaming.config;

import com.luciddreaming.LucidDreaming;
import net.minecraftforge.common.config.Config;
import net.minecraftforge.common.config.ConfigManager;
import net.minecraftforge.fml.client.event.ConfigChangedEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@Config(modid = LucidDreaming.MODID, name = LucidDreaming.NAME, category = "")
public class ModConfig {
    @Config.Name("服务器")
    @Config.Comment("服务器设置")
    public static Server server = new Server();

    @Config.Name("网络界面")
    @Config.Comment("网络界面设置")
    public static WebInterface webInterface = new WebInterface();

    public static class Server {
        @Config.Name("启用网络服务器")
        @Config.Comment("启用或禁用网络服务器界面（默认：true）")
        @Config.RequiresWorldRestart
        public boolean enableWebServer = true;

        @Config.Name("HTTP服务器端口")
        @Config.Comment("HTTP服务器的端口号（默认：1122）")
        @Config.RangeInt(min = 1024, max = 65535)
        @Config.RequiresWorldRestart
        public int httpPort = 1122;

        @Config.Name("绑定地址")
        @Config.Comment("要绑定的网络接口。使用'0.0.0.0'表示所有接口（推荐用于Android/LAN访问），或使用'127.0.0.1'仅用于本地主机。")
        @Config.RequiresWorldRestart
        public String bindAddress = "0.0.0.0";

        @Config.Name("启用局域网访问")
        @Config.Comment("允许同一网络上的设备访问HTTP服务器（推荐用于Android设备）")
        public boolean enableLANAccess = true;
    }

    public static class WebInterface {
        @Config.Name("启用截图")
        @Config.Comment("在网络界面中启用截图功能")
        public boolean enableScreenshot = true;

        @Config.Name("截图质量")
        @Config.Comment("截图质量（0.0 - 1.0）")
        @Config.RangeDouble(min = 0.1, max = 1.0)
        public double screenshotQuality = 0.8;

        @Config.Name("自动保存截图")
        @Config.Comment("自动将截图保存到本地文件")
        public boolean autoSaveScreenshots = false;

        @Config.Name("截图保存路径")
        @Config.Comment("保存截图的目录（默认：screenshots/luciddreaming/）")
        public String screenshotSavePath = "screenshots/luciddreaming/";

        @Config.Name("主题模式")
        @Config.Comment("主题模式：0 = 自动，1 = 亮色，2 = 暗色")
        @Config.RangeInt(min = 0, max = 2)
        public int themeMode = 0;

        @Config.Name("自动刷新间隔")
        @Config.Comment("自动刷新间隔（毫秒），0表示禁用")
        @Config.RangeInt(min = 0, max = 60000)
        public int autoRefreshInterval = 1000;

        @Config.Name("启用模块控制")
        @Config.Comment("在网络界面中启用模块控制面板")
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