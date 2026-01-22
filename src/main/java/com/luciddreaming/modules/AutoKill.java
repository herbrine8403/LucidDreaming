package com.luciddreaming.modules;

import com.luciddreaming.LucidDreaming;
import com.luciddreaming.config.ModuleConfigs;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.monster.IMob;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.passive.EntityTameable;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumHand;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Random;

public class AutoKill extends Module {
    private final Minecraft mc = Minecraft.getMinecraft();
    private final Random random = new Random();
    
    private int hitTimer = 0;
    private double currentAttackSpeed;
    private float targetYaw, targetPitch;

    public AutoKill() {
        super("AutoKill", "Automatically attack nearby entities", ModuleCategory.COMBAT);
    }

    @Override
    protected void onEnable() {
        LucidDreaming.LOGGER.info("AutoKill enabled");
        currentAttackSpeed = ModuleConfigs.autoKill.attackSpeed;
    }

    @Override
    protected void onDisable() {
        LucidDreaming.LOGGER.info("AutoKill disabled");
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

        // Check if only on click mode
        if (ModuleConfigs.autoKill.onlyOnClick && !mc.gameSettings.keyBindAttack.isKeyDown()) {
            return;
        }

        // Update attack speed with fluctuation
        if (ModuleConfigs.autoKill.cpsFluctuation) {
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
        for (int i = 0; i < Math.min(ModuleConfigs.autoKill.maxTargets, targets.size()); i++) {
            Entity target = targets.get(i);
            
            // Check miss chance
            if (ModuleConfigs.autoKill.missChance > 0 && random.nextDouble() < ModuleConfigs.autoKill.missChance) {
                continue;
            }

            attackEntity(target);
        }

        hitTimer = 0;
    }

    private void updateAttackSpeedFluctuation() {
        double fluctuation = ModuleConfigs.autoKill.fluctuationAmount;
        
        // Randomly adjust attack speed within fluctuation range
        if (random.nextDouble() < 0.1) {
            double speedFluctuation = (random.nextDouble() - 0.5) * 2 * fluctuation;
            currentAttackSpeed = Math.max(1, Math.min(20, ModuleConfigs.autoKill.attackSpeed + speedFluctuation));
        }
    }

    private List<Entity> findTargets() {
        List<Entity> targets = new ArrayList<>();
        double range = ModuleConfigs.autoKill.range;
        
        // Only on look mode - use targeted entity
        if (ModuleConfigs.autoKill.onlyOnLook && mc.objectMouseOver != null && mc.objectMouseOver.entityHit != null) {
            Entity targeted = mc.objectMouseOver.entityHit;
            if (isAttackable(targeted)) {
                targets.add(targeted);
            }
            return targets;
        }
        
        // Normal mode - find all targets
        for (Entity entity : mc.world.loadedEntityList) {
            if (entity == mc.player || entity.isDead) {
                continue;
            }

            // Check distance
            double distance = mc.player.getDistance(entity);
            
            // Check if visible
            boolean canSee = mc.player.canEntityBeSeen(entity);
            double effectiveRange = canSee ? range : ModuleConfigs.autoKill.wallsRange;
            
            if (distance > effectiveRange) {
                continue;
            }

            // Check if target is attackable
            if (!isAttackable(entity)) {
                continue;
            }

            targets.add(entity);
        }

        // Sort targets by priority
        sortTargets(targets);

        return targets;
    }

    private void sortTargets(List<Entity> targets) {
        int priority = ModuleConfigs.autoKill.priority;
        
        switch (priority) {
            case 0: // Closest
                targets.sort(Comparator.comparingDouble(e -> mc.player.getDistance(e)));
                break;
            case 1: // Lowest Health
                targets.sort(Comparator.comparingDouble(e -> e instanceof EntityLivingBase ? ((EntityLivingBase) e).getHealth() : Double.MAX_VALUE));
                break;
            case 2: // Closest Angle
                targets.sort(Comparator.comparingDouble(this::getAngleDifference));
                break;
        }
    }

    private double getAngleDifference(Entity entity) {
        float[] rotations = getRotations(entity);
        float yawDiff = Math.abs(rotations[0] - mc.player.rotationYaw);
        float pitchDiff = Math.abs(rotations[1] - mc.player.rotationPitch);
        return Math.sqrt(yawDiff * yawDiff + pitchDiff * pitchDiff);
    }

    private boolean isAttackable(Entity entity) {
        // Check if entity has custom name and should be ignored
        if (ModuleConfigs.autoKill.ignoreNamed && entity.hasCustomName()) {
            return false;
        }

        // Check if entity is tamed and should be ignored
        if (ModuleConfigs.autoKill.ignoreTamed && entity instanceof EntityTameable) {
            EntityTameable tameable = (EntityTameable) entity;
            if (tameable.getOwner() != null && tameable.getOwner().getUniqueID().equals(mc.player.getUniqueID())) {
                return false;
            }
        }

        // Check mob age filter
        if (entity instanceof EntityAnimal) {
            EntityAnimal animal = (EntityAnimal) entity;
            int ageFilter = ModuleConfigs.autoKill.mobAgeFilter;
            if (ageFilter == 1 && !animal.isChild()) return false; // Only baby
            if (ageFilter == 2 && animal.isChild()) return false; // Only adult
        }

        // Attack players
        if (ModuleConfigs.autoKill.targetPlayers && entity instanceof EntityPlayer) {
            return true;
        }

        // Attack hostile mobs
        if (ModuleConfigs.autoKill.targetHostileMobs && entity instanceof IMob) {
            return true;
        }

        // Attack passive mobs
        if (ModuleConfigs.autoKill.targetPassiveMobs && entity instanceof EntityAnimal) {
            return true;
        }

        return false;
    }

    private void attackEntity(Entity target) {
        if (target == null || !target.isEntityAlive() || mc.player == null || mc.playerController == null) {
            return;
        }

        // Check if weapon is required
        if (ModuleConfigs.autoKill.requireWeapon && !isWeapon(mc.player.getHeldItem(EnumHand.MAIN_HAND))) {
            return;
        }

        // Check if we can see the target
        if (!mc.player.canEntityBeSeen(target) && mc.player.getDistance(target) > ModuleConfigs.autoKill.wallsRange) {
            return;
        }

        // Rotate to face target
        if (ModuleConfigs.autoKill.rotationMode != 2) { // Not None
            float[] rotations = getRotations(target);
            
            if (ModuleConfigs.autoKill.rotationMode == 0) { // Always
                // Smooth rotation
                float yawDiff = rotations[0] - mc.player.rotationYaw;
                float pitchDiff = rotations[1] - mc.player.rotationPitch;
                
                double speed = ModuleConfigs.autoKill.rotationSpeed;
                mc.player.rotationYaw += yawDiff * speed;
                mc.player.rotationPitch += pitchDiff * speed;
            } else if (ModuleConfigs.autoKill.rotationMode == 1) { // On Hit
                mc.player.rotationYaw = rotations[0];
                mc.player.rotationPitch = rotations[1];
            }
        }

        // Attack
        mc.playerController.attackEntity(mc.player, target);
        mc.player.swingArm(EnumHand.MAIN_HAND);
    }

    private float[] getRotations(Entity entity) {
        double x = entity.posX - mc.player.posX;
        double y = entity.posY - mc.player.posY;
        double z = entity.posZ - mc.player.posZ;
        
        double distance = Math.sqrt(x * x + z * z);
        
        float yaw = (float) (Math.atan2(z, x) * 180.0 / Math.PI) - 90.0f;
        float pitch = (float) -(Math.atan2(y, distance) * 180.0 / Math.PI);
        
        return new float[]{yaw, pitch};
    }

    private boolean isWeapon(ItemStack stack) {
        if (stack.isEmpty()) return false;
        String itemClass = stack.getItem().getClass().getSimpleName().toLowerCase();
        return itemClass.contains("sword") || itemClass.contains("axe") || itemClass.contains("bow");
    }
}