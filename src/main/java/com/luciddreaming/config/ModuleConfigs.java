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

        @Config.Name("Click Mode")
        @Config.Comment("Click mode: 0 = Left Click, 1 = Right Click, 2 = Both")
        @Config.RangeInt(min = 0, max = 2)
        public int clickMode = 0;

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

    @Config.Name("AutoKill")
    @Config.Comment("Auto Kill module settings")
    public static AutoKill autoKill = new AutoKill();

    public static class AutoKill {
        @Config.Name("Enabled")
        @Config.Comment("Enable or disable auto kill")
        public boolean enabled = false;

        @Config.Name("Keybind")
        @Config.Comment("Keybind to toggle auto kill")
        public int keybind = 0;

        // Targeting settings
        @Config.Name("Target Players")
        @Config.Comment("Attack other players")
        public boolean targetPlayers = true;

        @Config.Name("Target Hostile Mobs")
        @Config.Comment("Attack hostile mobs")
        public boolean targetHostileMobs = true;

        @Config.Name("Target Passive Mobs")
        @Config.Comment("Attack passive mobs (animals)")
        public boolean targetPassiveMobs = false;

        @Config.Name("Range")
        @Config.Comment("Attack range in blocks")
        @Config.RangeDouble(min = 1.0, max = 6.0)
        public double range = 4.5;

        @Config.Name("Walls Range")
        @Config.Comment("Attack range through walls in blocks")
        @Config.RangeDouble(min = 0.0, max = 6.0)
        public double wallsRange = 3.5;

        @Config.Name("Max Targets")
        @Config.Comment("Maximum number of targets to attack at once")
        @Config.RangeInt(min = 1, max = 5)
        public int maxTargets = 1;

        @Config.Name("Priority")
        @Config.Comment("Target priority: 0 = Closest, 1 = Lowest Health, 2 = Closest Angle")
        @Config.RangeInt(min = 0, max = 2)
        public int priority = 0;

        @Config.Name("Ignore Named")
        @Config.Comment("Ignore mobs with custom names")
        public boolean ignoreNamed = false;

        @Config.Name("Ignore Tamed")
        @Config.Comment("Ignore tamed mobs")
        public boolean ignoreTamed = false;

        @Config.Name("Mob Age Filter")
        @Config.Comment("Mob age filter: 0 = Both, 1 = Baby, 2 = Adult")
        @Config.RangeInt(min = 0, max = 2)
        public int mobAgeFilter = 2;

        // Rotation settings
        @Config.Name("Rotation Mode")
        @Config.Comment("Rotation mode: 0 = Always, 1 = On Hit, 2 = None")
        @Config.RangeInt(min = 0, max = 2)
        public int rotationMode = 0;

        @Config.Name("Rotation Speed")
        @Config.Comment("Rotation speed (0.0 - 1.0)")
        @Config.RangeDouble(min = 0.1, max = 1.0)
        public double rotationSpeed = 0.5;

        // Attack settings
        @Config.Name("Attack Speed")
        @Config.Comment("Attack speed in hits per second")
        @Config.RangeDouble(min = 1.0, max = 20.0)
        public double attackSpeed = 8.0;

        @Config.Name("CPS Fluctuation")
        @Config.Comment("Enable CPS fluctuation to avoid detection")
        public boolean cpsFluctuation = true;

        @Config.Name("Fluctuation Amount")
        @Config.Comment("Amount of CPS fluctuation")
        @Config.RangeDouble(min = 0.0, max = 5.0)
        public double fluctuationAmount = 1.0;

        @Config.Name("Miss Chance")
        @Config.Comment("Chance to miss an attack (for anti-cheat evasion)")
        @Config.RangeDouble(min = 0.0, max = 0.5)
        public double missChance = 0.05;

        // Extra settings
        @Config.Name("Only On Click")
        @Config.Comment("Only attack when holding left click")
        public boolean onlyOnClick = false;

        @Config.Name("Only On Look")
        @Config.Comment("Only attack when looking at an entity")
        public boolean onlyOnLook = false;

        @Config.Name("Require Weapon")
        @Config.Comment("Only attack when holding a weapon")
        public boolean requireWeapon = false;
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

        // Action settings
        @Config.Name("Jump")
        @Config.Comment("Jump randomly to prevent AFK kick")
        public boolean jump = true;

        @Config.Name("Swing")
        @Config.Comment("Swing hand randomly")
        public boolean swing = false;

        @Config.Name("Sneak")
        @Config.Comment("Sneak and unsneak quickly")
        public boolean sneak = false;

        @Config.Name("Sneak Time")
        @Config.Comment("How many ticks to stay sneaked")
        @Config.RangeInt(min = 1, max = 20)
        public int sneakTime = 5;

        @Config.Name("Rotate")
        @Config.Comment("Rotate player randomly")
        public boolean rotate = true;

        @Config.Name("Rotate Speed")
        @Config.Comment("Rotation speed")
        @Config.RangeInt(min = 1, max = 20)
        public int rotateSpeed = 7;

        @Config.Name("Move")
        @Config.Comment("Move slightly to prevent AFK kick")
        public boolean move = false;

        @Config.Name("Move Distance")
        @Config.Comment("Movement distance")
        @Config.RangeDouble(min = 0.01, max = 0.5)
        public double moveDistance = 0.1;

        // Timing settings
        @Config.Name("Interval")
        @Config.Comment("Action interval in ticks")
        @Config.RangeInt(min = 10, max = 1200)
        public int interval = 300;

        @Config.Name("Random Interval")
        @Config.Comment("Add random variation to interval")
        public boolean randomInterval = true;

        @Config.Name("Max Random Variation")
        @Config.Comment("Maximum random interval variation in ticks")
        @Config.RangeInt(min = 0, max = 100)
        public int maxRandomVariation = 50;
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