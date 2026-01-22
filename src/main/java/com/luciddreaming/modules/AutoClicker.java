package com.luciddreaming.modules;

import com.luciddreaming.LucidDreaming;
import com.luciddreaming.config.ModuleConfigs;
import net.minecraft.client.Minecraft;
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
        super("AutoClicker", "Automatically click for you", ModuleCategory.PLAYER, 0);
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

        // Left click
        if (currentLeftCPS > 0) {
            int leftClickDelay = (int) (1000.0 / currentLeftCPS);
            leftClickTimer++;
            
            if (leftClickTimer >= leftClickDelay) {
                leftClick();
                leftClickTimer = 0;
            }
        }

        // Right click
        if (currentRightCPS > 0) {
            int rightClickDelay = (int) (1000.0 / currentRightCPS);
            rightClickTimer++;
            
            if (rightClickTimer >= rightClickDelay) {
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
        if (mc.objectMouseOver == null) {
            return;
        }

        mc.playerController.attackEntity(mc.player, mc.objectMouseOver.entityHit);
        mc.player.swingArm(EnumHand.MAIN_HAND);
    }

    private void rightClick() {
        if (mc.objectMouseOver == null) {
            return;
        }

        mc.playerController.processRightClick(mc.player, mc.world, EnumHand.MAIN_HAND);
    }
}