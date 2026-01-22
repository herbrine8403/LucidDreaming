package com.luciddreaming.modules;

import com.luciddreaming.LucidDreaming;
import com.luciddreaming.config.ModuleConfigs;
import net.minecraft.client.Minecraft;
import net.minecraft.util.EnumHand;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;

import java.util.Random;

public class AntiKick extends Module {
    private final Minecraft mc = Minecraft.getMinecraft();
    private final Random random = new Random();
    
    private int tickCounter = 0;
    private int currentInterval;
    private int sneakTimer = 0;
    private boolean isSneaking = false;
    private float lastYaw;

    public AntiKick() {
        super("AntiKick", "Prevent being kicked for being AFK", ModuleCategory.MISC);
    }

    @Override
    protected void onEnable() {
        LucidDreaming.LOGGER.info("AntiKick enabled");
        tickCounter = 0;
        sneakTimer = 0;
        isSneaking = false;
        lastYaw = mc.player.rotationYaw;
        updateInterval();
    }

    @Override
    protected void onDisable() {
        LucidDreaming.LOGGER.info("AntiKick disabled");
        // Reset movement input
        if (mc.player != null) {
            mc.player.movementInput.moveForward = 0;
            mc.player.movementInput.moveStrafe = 0;
            mc.player.setSneaking(false);
        }
    }

    @Override
    public void onTick() {
        if (mc.world == null || mc.player == null) {
            return;
        }

        tickCounter++;
        
        // Check if it's time to perform anti-kick action
        if (tickCounter >= currentInterval) {
            tickCounter = 0;
            updateInterval();
            performAntiKickActions();
        }

        // Continuous actions
        performContinuousActions();
    }

    private void updateInterval() {
        currentInterval = ModuleConfigs.antiKick.interval;
        if (ModuleConfigs.antiKick.randomInterval && ModuleConfigs.antiKick.maxRandomVariation > 0) {
            int variation = random.nextInt(ModuleConfigs.antiKick.maxRandomVariation + 1);
            if (random.nextBoolean()) {
                currentInterval += variation;
            } else {
                currentInterval = Math.max(10, currentInterval - variation);
            }
        }
    }

    private void performAntiKickActions() {
        // Jump action
        if (ModuleConfigs.antiKick.jump && mc.player.onGround) {
            if (random.nextInt(100) < 20) { // 20% chance to jump
                mc.player.jump();
            }
        }

        // Swing action
        if (ModuleConfigs.antiKick.swing && random.nextInt(100) < 10) { // 10% chance to swing
            mc.player.swingArm(EnumHand.MAIN_HAND);
        }
    }

    private void performContinuousActions() {
        // Rotate action (continuous)
        if (ModuleConfigs.antiKick.rotate) {
            lastYaw += ModuleConfigs.antiKick.rotateSpeed;
            mc.player.rotationYaw = lastYaw;
        }

        // Sneak action
        if (ModuleConfigs.antiKick.sneak) {
            if (isSneaking) {
                sneakTimer++;
                if (sneakTimer >= ModuleConfigs.antiKick.sneakTime) {
                    mc.player.setSneaking(false);
                    isSneaking = false;
                    sneakTimer = 0;
                }
            } else {
                if (random.nextInt(200) < 5) { // Small chance to start sneaking
                    mc.player.setSneaking(true);
                    isSneaking = true;
                }
            }
        }

        // Move action
        if (ModuleConfigs.antiKick.move) {
            double moveDistance = ModuleConfigs.antiKick.moveDistance;
            
            // Randomly move in different directions
            if (random.nextInt(100) < 10) { // 10% chance to move
                int direction = random.nextInt(4);
                switch (direction) {
                    case 0: // Forward
                        mc.player.movementInput.moveForward = (float) moveDistance;
                        mc.player.movementInput.moveStrafe = 0;
                        break;
                    case 1: // Backward
                        mc.player.movementInput.moveForward = (float) -moveDistance;
                        mc.player.movementInput.moveStrafe = 0;
                        break;
                    case 2: // Left
                        mc.player.movementInput.moveForward = 0;
                        mc.player.movementInput.moveStrafe = (float) moveDistance;
                        break;
                    case 3: // Right
                        mc.player.movementInput.moveForward = 0;
                        mc.player.movementInput.moveStrafe = (float) -moveDistance;
                        break;
                }
            } else {
                // Reset movement
                mc.player.movementInput.moveForward = 0;
                mc.player.movementInput.moveStrafe = 0;
            }
        }
    }
}