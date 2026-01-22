package com.luciddreaming.modules;

import com.luciddreaming.LucidDreaming;
import com.luciddreaming.config.ModuleConfigs;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.monster.IMob;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.EnumHand;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class KillAura extends Module {
    private final Minecraft mc = Minecraft.getMinecraft();
    private final Random random = new Random();
    
    private int hitTimer = 0;
    private double currentAttackSpeed;

    public KillAura() {
        super("KillAura", "Automatically attack nearby entities", ModuleCategory.COMBAT);
    }

    @Override
    protected void onEnable() {
        LucidDreaming.LOGGER.info("KillAura enabled");
        currentAttackSpeed = ModuleConfigs.killAura.attackSpeed;
    }

    @Override
    protected void onDisable() {
        LucidDreaming.LOGGER.info("KillAura disabled");
        hitTimer = 0;
    }

    @Override
    public void onTick() {
        if (mc.world == null || mc.player == null) {
            return;
        }

        // Don't attack if in a GUI
        if (mc.currentScreen != null) {
            return;
        }

        // Update attack speed with fluctuation
        if (ModuleConfigs.killAura.cpsFluctuation) {
            updateAttackSpeedFluctuation();
        }

        // Find targets
        List<Entity> targets = findTargets();
        
        if (targets.isEmpty()) {
            hitTimer = 0;
            return;
        }

        // Check attack cooldown
        int attackDelay = (int) (1000.0 / currentAttackSpeed);
        if (hitTimer < attackDelay) {
            hitTimer++;
            return;
        }

        // Attack targets
        for (Entity target : targets) {
            // Check miss chance
            if (ModuleConfigs.killAura.missChance > 0 && random.nextDouble() < ModuleConfigs.killAura.missChance) {
                continue;
            }

            attackEntity(target);
        }

        hitTimer = 0;
    }

    private void updateAttackSpeedFluctuation() {
        double fluctuation = 2.0; // Default fluctuation amount
        
        // Randomly adjust attack speed within fluctuation range
        if (random.nextDouble() < 0.1) {
            double speedFluctuation = (random.nextDouble() - 0.5) * 2 * fluctuation;
            currentAttackSpeed = Math.max(1, Math.min(20, ModuleConfigs.killAura.attackSpeed + speedFluctuation));
        }
    }

    private List<Entity> findTargets() {
        List<Entity> targets = new ArrayList<>();
        double range = ModuleConfigs.killAura.range;
        
        for (Entity entity : mc.world.loadedEntityList) {
            if (entity == mc.player || entity.isDead) {
                continue;
            }

            // Check distance
            double distance = mc.player.getDistance(entity);
            if (distance > range) {
                continue;
            }

            // Check if target is attackable
            if (!isAttackable(entity)) {
                continue;
            }

            targets.add(entity);
        }

        return targets;
    }

    private boolean isAttackable(Entity entity) {
        // Attack hostile mobs
        if (entity instanceof IMob) {
            return true;
        }

        // Attack other players (in multiplayer)
        if (entity instanceof EntityPlayer && !mc.isSingleplayer()) {
            return true;
        }

        // Optionally attack passive mobs (can be configured)
        // Currently disabled to avoid killing animals
        // if (entity instanceof EntityAnimal) {
        //     return true;
        // }

        return false;
    }

    private void attackEntity(Entity target) {
        if (target == null || !target.isEntityAlive() || mc.player == null || mc.playerController == null) {
            return;
        }

        // Check if we can see the target
        if (!mc.player.canEntityBeSeen(target)) {
            return;
        }

        // Rotate to face target
        rotateToTarget(target);

        // Attack
        mc.playerController.attackEntity(mc.player, target);
        mc.player.swingArm(EnumHand.MAIN_HAND);
    }

    private void rotateToTarget(Entity target) {
        double x = target.posX - mc.player.posX;
        double y = target.posY - mc.player.posY;
        double z = target.posZ - mc.player.posZ;
        
        double distance = Math.sqrt(x * x + z * z);
        
        float yaw = (float) (Math.atan2(z, x) * 180.0 / Math.PI) - 90.0f;
        float pitch = (float) -(Math.atan2(y, distance) * 180.0 / Math.PI);
        
        mc.player.rotationYaw = yaw;
        mc.player.rotationPitch = pitch;
    }
}