package com.luciddreaming.config;

import com.luciddreaming.LucidDreaming;
import net.minecraftforge.common.config.Config;

@Config(modid = LucidDreaming.MODID, name = LucidDreaming.NAME + "_modules", category = "")
public class ModuleConfigs {
    @Config.Name("AutoFish")
    @Config.Comment("Auto Fish module settings")
    public static AutoFish autoFish = new AutoFish();

    public static class AutoFish {
        @Config.Name("Enabled")
        @Config.Comment("Enable or disable auto fish")
        public boolean enabled = false;

        @Config.Name("Keybind")
        @Config.Comment("Keybind to toggle auto fish")
        public int keybind = 0;

        @Config.Name("Recast Delay")
        @Config.Comment("Delay in milliseconds before recasting (default: 1500)")
        @Config.RangeInt(min = 0, max = 10000)
        public int recastDelay = 1500;

        @Config.Name("Multi Rod")
        @Config.Comment("Use multiple fishing rods")
        public boolean multiRod = false;

        @Config.Name("No Break")
        @Config.Comment("Prevent rod from breaking")
        public boolean noBreak = false;
    }

    @Config.Name("AutoClicker")
    @Config.Comment("Auto Clicker module settings")
    public static AutoClicker autoClicker = new AutoClicker();

    public static class AutoClicker {
        @Config.Name("Enabled")
        @Config.Comment("Enable or disable auto clicker")
        public boolean enabled = false;

        @Config.Name("Keybind")
        @Config.Comment("Keybind to toggle auto clicker")
        public int keybind = 0;

        @Config.Name("Left CPS")
        @Config.Comment("Clicks per second for left click")
        @Config.RangeInt(min = 1, max = 30)
        public int leftCPS = 8;

        @Config.Name("Right CPS")
        @Config.Comment("Clicks per second for right click")
        @Config.RangeInt(min = 1, max = 30)
        public int rightCPS = 4;

        @Config.Name("CPS Fluctuation")
        @Config.Comment("Enable CPS fluctuation to avoid detection")
        public boolean cpsFluctuation = true;

        @Config.Name("Fluctuation Amount")
        @Config.Comment("Amount of CPS fluctuation")
        @Config.RangeDouble(min = 0.0, max = 5.0)
        public double fluctuationAmount = 1.0;
    }

    @Config.Name("KillAura")
    @Config.Comment("Kill Aura module settings")
    public static KillAura killAura = new KillAura();

    public static class KillAura {
        @Config.Name("Enabled")
        @Config.Comment("Enable or disable kill aura")
        public boolean enabled = false;

        @Config.Name("Keybind")
        @Config.Comment("Keybind to toggle kill aura")
        public int keybind = 0;

        @Config.Name("Attack Speed")
        @Config.Comment("Attack speed in hits per second")
        @Config.RangeDouble(min = 1.0, max = 20.0)
        public double attackSpeed = 8.0;

        @Config.Name("Range")
        @Config.Comment("Attack range in blocks")
        @Config.RangeDouble(min = 1.0, max = 6.0)
        public double range = 4.5;

        @Config.Name("CPS Fluctuation")
        @Config.Comment("Enable CPS fluctuation to avoid detection")
        public boolean cpsFluctuation = true;

        @Config.Name("Miss Chance")
        @Config.Comment("Chance to miss an attack (for anti-cheat evasion)")
        @Config.RangeDouble(min = 0.0, max = 0.5)
        public double missChance = 0.05;
    }

    @Config.Name("AntiKick")
    @Config.Comment("Anti Kick module settings")
    public static AntiKick antiKick = new AntiKick();

    public static class AntiKick {
        @Config.Name("Enabled")
        @Config.Comment("Enable or disable anti kick")
        public boolean enabled = false;

        @Config.Name("Keybind")
        @Config.Comment("Keybind to toggle anti kick")
        public int keybind = 0;

        @Config.Name("Mode")
        @Config.Comment("Anti kick mode: 0 = Jump, 1 = Rotate, 2 = Move")
        @Config.RangeInt(min = 0, max = 2)
        public int mode = 0;

        @Config.Name("Interval")
        @Config.Comment("Action interval in ticks")
        @Config.RangeInt(min = 10, max = 1200)
        public int interval = 300;
    }

    @Config.Name("AutoWalk")
    @Config.Comment("Auto Walk module settings")
    public static AutoWalk autoWalk = new AutoWalk();

    public static class AutoWalk {
        @Config.Name("Enabled")
        @Config.Comment("Enable or disable auto walk")
        public boolean enabled = false;

        @Config.Name("Keybind")
        @Config.Comment("Keybind to toggle auto walk")
        public int keybind = 0;

        @Config.Name("Speed")
        @Config.Comment("Walking speed multiplier")
        @Config.RangeDouble(min = 0.1, max = 5.0)
        public double speed = 1.0;
    }

    @Config.Name("NoRender")
    @Config.Comment("No Render module settings")
    public static NoRender noRender = new NoRender();

    public static class NoRender {
        @Config.Name("Enabled")
        @Config.Comment("Enable or disable no render")
        public boolean enabled = false;

        @Config.Name("Keybind")
        @Config.Comment("Keybind to toggle no render")
        public int keybind = 0;

        @Config.Name("Hide Entities")
        @Config.Comment("Hide all entities")
        public boolean hideEntities = false;

        @Config.Name("Hide Particles")
        @Config.Comment("Hide particles")
        public boolean hideParticles = false;

        @Config.Name("Hide Weather")
        @Config.Comment("Hide weather")
        public boolean hideWeather = false;

        @Config.Name("Hide Sky")
        @Config.Comment("Hide sky")
        public boolean hideSky = false;

        @Config.Name("Hide Fog")
        @Config.Comment("Hide fog")
        public boolean hideFog = false;
    }

    @Config.Name("FakeBlackScreen")
    @Config.Comment("Fake Black Screen module settings")
    public static FakeBlackScreen fakeBlackScreen = new FakeBlackScreen();

    public static class FakeBlackScreen {
        @Config.Name("Enabled")
        @Config.Comment("Enable or disable fake black screen")
        public boolean enabled = false;

        @Config.Name("Keybind")
        @Config.Comment("Keybind to toggle fake black screen")
        public int keybind = 0;

        @Config.Name("Opacity")
        @Config.Comment("Black screen opacity (0.0 - 1.0)")
        @Config.RangeDouble(min = 0.0, max = 1.0)
        public double opacity = 1.0;
    }
}