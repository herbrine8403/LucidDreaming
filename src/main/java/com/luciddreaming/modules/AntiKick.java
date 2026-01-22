package com.luciddreaming.modules;

import com.luciddreaming.LucidDreaming;
import com.luciddreaming.config.ModuleConfigs;
import net.minecraft.client.Minecraft;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;

import java.util.Random;

public class AntiKick extends Module {
    private final Minecraft mc = Minecraft.getMinecraft();
    private final Random random = new Random();
    
    private int tickCounter = 0;
    private boolean shouldPerformAction = false;

    public AntiKick() {
        super("AntiKick", "Prevent being kicked for being AFK", ModuleCategory.MISC, 0);
    }

    @Override
    protected void onEnable() {
        LucidDreaming.LOGGER.info("AntiKick enabled");
        tickCounter = 0;
    }

    @Override
    protected void onDisable() {
        LucidDreaming.LOGGER.info("AntiKick disabled");
    }

    @Override
    public void onTick() {
        if (mc.world == null || mc.player == null) {
            return;
        }

        tickCounter++;
        
        // Check if it's time to perform anti-kick action
        int interval = ModuleConfigs.antiKick.interval;
        if (tickCounter >= interval) {
            tickCounter = 0;
            shouldPerformAction = true;
        }

        if (shouldPerformAction) {
            performAntiKickAction();
            shouldPerformAction = false;
        }
    }

    private void performAntiKickAction() {
        int mode = ModuleConfigs.antiKick.mode;
        
        switch (mode) {
            case 0:
                jumpAction();
                break;
            case 1:
                rotateAction();
                break;
            case 2:
                moveAction();
                break;
            default:
                jumpAction();
        }
    }

    private void jumpAction() {
        // Jump occasionally to prevent AFK kick
        if (mc.player.onGround) {
            mc.player.jump();
        }
    }

    private void rotateAction() {
        // Rotate player occasionally
        float yawChange = (random.nextFloat() - 0.5f) * 180.0f;
        float pitchChange = (random.nextFloat() - 0.5f) * 90.0f;
        
        mc.player.rotationYaw += yawChange;
        mc.player.rotationPitch += pitchChange;
        
        // Clamp pitch to valid range
        mc.player.rotationPitch = Math.max(-90.0f, Math.min(90.0f, mc.player.rotationPitch));
    }

    private void moveAction() {
        // Move slightly to prevent AFK kick
        boolean shouldMoveForward = random.nextBoolean();
        boolean shouldMoveBackward = !shouldMoveForward && random.nextBoolean();
        boolean shouldMoveLeft = random.nextBoolean();
        boolean shouldMoveRight = !shouldMoveLeft && random.nextBoolean();
        
        mc.player.movementInput.moveForward = shouldMoveForward ? 0.1f : (shouldMoveBackward ? -0.1f : 0.0f);
        mc.player.movementInput.moveStrafe = shouldMoveLeft ? 0.1f : (shouldMoveRight ? -0.1f : 0.0f);
    }
}