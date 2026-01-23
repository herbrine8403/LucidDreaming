package com.luciddreaming.config;

import com.luciddreaming.LucidDreaming;
import net.minecraftforge.common.config.Config;

@Config(modid = LucidDreaming.MODID, name = LucidDreaming.NAME + "_modules", category = "")
public class ModuleConfigs {
    @Config.Name("自动钓鱼")
    @Config.Comment("自动钓鱼模块设置")
    public static AutoFish autoFish = new AutoFish();

    public static class AutoFish {
        @Config.Name("启用")
        @Config.Comment("启用或禁用自动钓鱼")
        public boolean enabled = false;

        @Config.Name("快捷键")
        @Config.Comment("切换自动钓鱼的快捷键")
        public int keybind = 0;

        @Config.Name("重新投掷延迟")
        @Config.Comment("重新投掷前的延迟（毫秒，默认：1500）")
        @Config.RangeInt(min = 0, max = 10000)
        public int recastDelay = 1500;

        @Config.Name("多鱼竿")
        @Config.Comment("使用多个钓鱼竿")
        public boolean multiRod = false;

        @Config.Name("无损坏")
        @Config.Comment("防止鱼竿损坏")
        public boolean noBreak = false;
    }

    @Config.Name("自动点击")
    @Config.Comment("自动点击模块设置")
    public static AutoClicker autoClicker = new AutoClicker();

    public static class AutoClicker {
        @Config.Name("启用")
        @Config.Comment("启用或禁用自动点击")
        public boolean enabled = false;

        @Config.Name("快捷键")
        @Config.Comment("切换自动点击的快捷键")
        public int keybind = 0;

        @Config.Name("点击模式")
        @Config.Comment("点击模式：0 = 左键，1 = 右键，2 = 两者都")
        @Config.RangeInt(min = 0, max = 2)
        public int clickMode = 0;

        @Config.Name("左键点击速度")
        @Config.Comment("左键每秒点击次数")
        @Config.RangeInt(min = 1, max = 30)
        public int leftCPS = 8;

        @Config.Name("右键点击速度")
        @Config.Comment("右键每秒点击次数")
        @Config.RangeInt(min = 1, max = 30)
        public int rightCPS = 4;

        @Config.Name("点击速度波动")
        @Config.Comment("启用点击速度波动以避免检测")
        public boolean cpsFluctuation = true;

        @Config.Name("波动幅度")
        @Config.Comment("点击速度波动幅度")
        @Config.RangeDouble(min = 0.0, max = 5.0)
        public double fluctuationAmount = 1.0;
    }

    @Config.Name("自动攻击")
    @Config.Comment("自动攻击模块设置")
    public static AutoKill autoKill = new AutoKill();

    public static class AutoKill {
        @Config.Name("启用")
        @Config.Comment("启用或禁用自动攻击")
        public boolean enabled = false;

        @Config.Name("快捷键")
        @Config.Comment("切换自动攻击的快捷键")
        public int keybind = 0;

        // 目标设置
        @Config.Name("攻击玩家")
        @Config.Comment("攻击其他玩家")
        public boolean targetPlayers = true;

        @Config.Name("攻击敌对生物")
        @Config.Comment("攻击敌对生物")
        public boolean targetHostileMobs = true;

        @Config.Name("攻击被动生物")
        @Config.Comment("攻击被动生物（动物）")
        public boolean targetPassiveMobs = false;

        @Config.Name("攻击范围")
        @Config.Comment("攻击范围（方块）")
        @Config.RangeDouble(min = 1.0, max = 6.0)
        public double range = 4.5;

        @Config.Name("穿墙范围")
        @Config.Comment("穿墙攻击范围（方块）")
        @Config.RangeDouble(min = 0.0, max = 6.0)
        public double wallsRange = 3.5;

        @Config.Name("最大目标数")
        @Config.Comment("一次攻击的最大目标数")
        @Config.RangeInt(min = 1, max = 5)
        public int maxTargets = 1;

        @Config.Name("优先级")
        @Config.Comment("目标优先级：0 = 最近，1 = 最低生命值，2 = 最近角度")
        @Config.RangeInt(min = 0, max = 2)
        public int priority = 0;

        @Config.Name("忽略命名生物")
        @Config.Comment("忽略自定义名称的生物")
        public boolean ignoreNamed = false;

        @Config.Name("忽略驯服生物")
        @Config.Comment("忽略驯服的生物")
        public boolean ignoreTamed = false;

        @Config.Name("生物年龄过滤")
        @Config.Comment("生物年龄过滤：0 = 两者都，1 = 幼年，2 = 成年")
        @Config.RangeInt(min = 0, max = 2)
        public int mobAgeFilter = 2;

        // 旋转设置
        @Config.Name("旋转模式")
        @Config.Comment("旋转模式：0 = 始终，1 = 击中时，2 = 无")
        @Config.RangeInt(min = 0, max = 2)
        public int rotationMode = 0;

        @Config.Name("旋转速度")
        @Config.Comment("旋转速度（0.0 - 1.0）")
        @Config.RangeDouble(min = 0.1, max = 1.0)
        public double rotationSpeed = 0.5;

        // 攻击设置
        @Config.Name("攻击速度")
        @Config.Comment("每秒攻击次数")
        @Config.RangeDouble(min = 1.0, max = 20.0)
        public double attackSpeed = 8.0;

        @Config.Name("点击速度波动")
        @Config.Comment("启用点击速度波动以避免检测")
        public boolean cpsFluctuation = true;

        @Config.Name("波动幅度")
        @Config.Comment("点击速度波动幅度")
        @Config.RangeDouble(min = 0.0, max = 5.0)
        public double fluctuationAmount = 1.0;

        @Config.Name("未命中概率")
        @Config.Comment("攻击未命中概率（用于反作弊规避）")
        @Config.RangeDouble(min = 0.0, max = 0.5)
        public double missChance = 0.05;

        // 额外设置
        @Config.Name("仅点击时")
        @Config.Comment("仅在按住左键时攻击")
        public boolean onlyOnClick = false;

        @Config.Name("仅瞄准时有")
        @Config.Comment("仅在瞄准时攻击")
        public boolean onlyOnLook = false;

        @Config.Name("需要武器")
        @Config.Comment("仅在手持武器时攻击")
        public boolean requireWeapon = false;
    }

    @Config.Name("防踢出")
    @Config.Comment("防踢出模块设置")
    public static AntiKick antiKick = new AntiKick();

    public static class AntiKick {
        @Config.Name("启用")
        @Config.Comment("启用或禁用防踢出")
        public boolean enabled = false;

        @Config.Name("快捷键")
        @Config.Comment("切换防踢出的快捷键")
        public int keybind = 0;

        // 动作设置
        @Config.Name("跳跃")
        @Config.Comment("随机跳跃以防止AFK踢出")
        public boolean jump = true;

        @Config.Name("挥手")
        @Config.Comment("随机挥手")
        public boolean swing = false;

        @Config.Name("潜行")
        @Config.Comment("快速潜行和取消潜行")
        public boolean sneak = false;

        @Config.Name("潜行时间")
        @Config.Comment("潜行持续的刻数")
        @Config.RangeInt(min = 1, max = 20)
        public int sneakTime = 5;

        @Config.Name("旋转")
        @Config.Comment("随机旋转玩家")
        public boolean rotate = true;

        @Config.Name("旋转速度")
        @Config.Comment("旋转速度")
        @Config.RangeInt(min = 1, max = 20)
        public int rotateSpeed = 7;

        @Config.Name("移动")
        @Config.Comment("轻微移动以防止AFK踢出")
        public boolean move = false;

        @Config.Name("移动距离")
        @Config.Comment("移动距离")
        @Config.RangeDouble(min = 0.01, max = 0.5)
        public double moveDistance = 0.1;

        // 时间设置
        @Config.Name("间隔")
        @Config.Comment("动作间隔（刻）")
        @Config.RangeInt(min = 10, max = 1200)
        public int interval = 300;

        @Config.Name("随机间隔")
        @Config.Comment("为间隔添加随机变化")
        public boolean randomInterval = true;

        @Config.Name("最大随机变化")
        @Config.Comment("最大随机间隔变化（刻）")
        @Config.RangeInt(min = 0, max = 100)
        public int maxRandomVariation = 50;
    }

    @Config.Name("自动行走")
    @Config.Comment("自动行走模块设置")
    public static AutoWalk autoWalk = new AutoWalk();

    public static class AutoWalk {
        @Config.Name("启用")
        @Config.Comment("启用或禁用自动行走")
        public boolean enabled = false;

        @Config.Name("快捷键")
        @Config.Comment("切换自动行走的快捷键")
        public int keybind = 0;

        @Config.Name("速度")
        @Config.Comment("行走速度倍数")
        @Config.RangeDouble(min = 0.1, max = 5.0)
        public double speed = 1.0;
        
        @Config.Name("允许破坏方块")
        @Config.Comment("允许在寻路期间破坏方块（默认：false）")
        public boolean allowBreak = false;
        
        @Config.Name("允许放置方块")
        @Config.Comment("允许在寻路期间放置方块（默认：false）")
        public boolean allowPlace = false;
        
        @Config.Name("破坏方块成本")
        @Config.Comment("破坏方块的额外成本（越高 = 越不倾向于破坏）")
        @Config.RangeDouble(min = 0.0, max = 100.0)
        public double breakBlockCost = 10.0;
        
        @Config.Name("放置方块成本")
        @Config.Comment("放置方块的额外成本（越高 = 越不倾向于放置）")
        @Config.RangeDouble(min = 0.0, max = 100.0)
        public double placeBlockCost = 10.0;
        
        @Config.Name("最大寻路距离")
        @Config.Comment("寻找路径的最大距离（方块）")
        @Config.RangeInt(min = 50, max = 1000)
        public int maxPathfindingDistance = 200;
        
        @Config.Name("最大寻路时间")
        @Config.Comment("寻路的最大时间（毫秒）")
        @Config.RangeInt(min = 1000, max = 30000)
        public int maxPathfindingTime = 10000;
    }

    @Config.Name("禁用渲染")
    @Config.Comment("禁用渲染模块设置")
    public static NoRender noRender = new NoRender();

    public static class NoRender {
        @Config.Name("启用")
        @Config.Comment("启用或禁用禁用渲染")
        public boolean enabled = false;

        @Config.Name("快捷键")
        @Config.Comment("切换禁用渲染的快捷键")
        public int keybind = 0;

        @Config.Name("隐藏实体")
        @Config.Comment("隐藏所有实体")
        public boolean hideEntities = false;

        @Config.Name("隐藏粒子")
        @Config.Comment("隐藏粒子效果")
        public boolean hideParticles = false;

        @Config.Name("隐藏天气")
        @Config.Comment("隐藏天气效果")
        public boolean hideWeather = false;

        @Config.Name("隐藏天空")
        @Config.Comment("隐藏天空")
        public boolean hideSky = false;

        @Config.Name("隐藏雾")
        @Config.Comment("隐藏雾效")
        public boolean hideFog = false;
    }

    @Config.Name("假黑屏")
    @Config.Comment("假黑屏模块设置")
    public static FakeBlackScreen fakeBlackScreen = new FakeBlackScreen();

    public static class FakeBlackScreen {
        @Config.Name("启用")
        @Config.Comment("启用或禁用假黑屏")
        public boolean enabled = false;

        @Config.Name("快捷键")
        @Config.Comment("切换假黑屏的快捷键")
        public int keybind = 0;

        @Config.Name("不透明度")
        @Config.Comment("黑屏不透明度（0.0 - 1.0）")
        @Config.RangeDouble(min = 0.0, max = 1.0)
        public double opacity = 1.0;
    }
}