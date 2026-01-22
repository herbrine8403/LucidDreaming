package com.luciddreaming.modules;

import com.luciddreaming.LucidDreaming;
import com.luciddreaming.config.ModuleConfigs;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.network.play.client.CPacketUseEntity;
import net.minecraft.util.EnumHand;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;

import java.util.Random;

public class AutoClicker extends Module {
    private final Minecraft mc = Minecraft.getMinecraft();
    private final Random random = new Random();

    private int leftClickTimer = 0;
    private int rightClickTimer = 0;
    private double currentLeftCPS;
    private double currentRightCPS;

    public AutoClicker() {
        super("AutoClicker", "Automatically click for you", ModuleCategory.PLAYER);
    }

    @Override
    protected void onEnable() {
        LucidDreaming.LOGGER.info("AutoClicker enabled");
        currentLeftCPS = ModuleConfigs.autoClicker.leftCPS;
        currentRightCPS = ModuleConfigs.autoClicker.rightCPS;
    }

    @Override
    protected void onDisable() {
        LucidDreaming.LOGGER.info("AutoClicker disabled");
        leftClickTimer = 0;
        rightClickTimer = 0;
    }

    @Override
    public void onTick() {
        if (mc.world == null || mc.player == null) {
            return;
        }

        // Don't click if in a GUI
        if (mc.currentScreen != null) {
            return;
        }

        // Update CPS with fluctuation
        if (ModuleConfigs.autoClicker.cpsFluctuation) {
            updateCPSFluctuation();
        }

        // Left click (mode 0 or 2)
        int clickMode = ModuleConfigs.autoClicker.clickMode;
        if ((clickMode == 0 || clickMode == 2) && currentLeftCPS > 0) {
            // Convert CPS to tick delay (like Meteor)
            // Using > comparison, so delay = (20 / CPS) - 1
            // For 20 CPS: (20 / 20) - 1 = 0 ticks (click every tick)
            // For 10 CPS: (20 / 10) - 1 = 1 tick (click every 2 ticks)
            int leftClickDelay = Math.max(0, (int) (20.0 / currentLeftCPS) - 1);
            leftClickTimer++;

            if (leftClickTimer > leftClickDelay) {
                leftClick();
                leftClickTimer = 0;
            }
        }

        // Right click (mode 1 or 2)
        if ((clickMode == 1 || clickMode == 2) && currentRightCPS > 0) {
            // Convert CPS to tick delay (like Meteor)
            int rightClickDelay = Math.max(0, (int) (20.0 / currentRightCPS) - 1);
            rightClickTimer++;

            if (rightClickTimer > rightClickDelay) {
                rightClick();
                rightClickTimer = 0;
            }
        }
    }

    private void updateCPSFluctuation() {
        double fluctuation = ModuleConfigs.autoClicker.fluctuationAmount;

        // Randomly adjust CPS within fluctuation range
        if (random.nextDouble() < 0.1) {
            double leftFluctuation = (random.nextDouble() - 0.5) * 2 * fluctuation;
            double rightFluctuation = (random.nextDouble() - 0.5) * 2 * fluctuation;

            currentLeftCPS = Math.max(1, Math.min(30, ModuleConfigs.autoClicker.leftCPS + leftFluctuation));
            currentRightCPS = Math.max(1, Math.min(30, ModuleConfigs.autoClicker.rightCPS + rightFluctuation));
        }
    }

    private void leftClick() {
        try {
            // Check attack cooldown (like GateClient)
            if (mc.player.getCooledAttackStrength(0) < 1.0F) {
                return;
            }

            // Attack entity if mouse is over one, otherwise just click
            if (mc.objectMouseOver != null && mc.objectMouseOver.entityHit != null) {
                // Use packet method (like Mousse and GateClient)
                Entity target = mc.objectMouseOver.entityHit;
                mc.player.connection.sendPacket(new CPacketUseEntity(target));
            } else {
                // Use playerController for block breaking
                mc.playerController.attackEntity(mc.player, null);
            }

            // Swing arm
            mc.player.swingArm(EnumHand.MAIN_HAND);

            // Reset cooldown to bypass attack speed limit
            mc.player.resetCooldown();
        } catch (Exception e) {
            LucidDreaming.LOGGER.error("Failed to perform left click", e);
        }
    }

    private void rightClick() {
        try {
            // Use playerController for right click (item use, block placement, etc.)
            mc.playerController.processRightClick(mc.player, mc.world, EnumHand.MAIN_HAND);
        } catch (Exception e) {
            LucidDreaming.LOGGER.error("Failed to perform right click", e);
        }
    }
}