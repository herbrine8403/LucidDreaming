package com.luciddreaming.modules;

import com.luciddreaming.LucidDreaming;
import com.luciddreaming.config.ModuleConfigs;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.projectile.EntityFishHook;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumHand;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;

public class AutoFish extends Module {
    private final Minecraft mc = Minecraft.getMinecraft();
    private long lastRecastTime = 0;
    private boolean waitingForRecast = false;
    private int previousTicksExisted = 0;

    public AutoFish() {
        super("AutoFish", "Automatically catch fish", ModuleCategory.PLAYER);
    }

    @Override
    protected void onEnable() {
        LucidDreaming.LOGGER.info("AutoFish enabled");
        lastRecastTime = 0;
        waitingForRecast = false;
        previousTicksExisted = 0;
    }

    @Override
    protected void onDisable() {
        LucidDreaming.LOGGER.info("AutoFish disabled");
    }

    @Override
    public void onTick() {
        if (mc.world == null || mc.player == null) {
            return;
        }

        // Check if we need to recast
        if (waitingForRecast) {
            long currentTime = System.currentTimeMillis();
            if (currentTime - lastRecastTime >= ModuleConfigs.autoFish.recastDelay) {
                recast();
                waitingForRecast = false;
            }
            return;
        }

        // Find fish hook entity
        EntityFishHook fishHook = findFishHook();
        if (fishHook == null) {
            // No fish hook, try to cast
            castRod();
            return;
        }

        // Check if fish is caught
        if (isFishCaught(fishHook)) {
            catchFish();
        }
    }

    private EntityFishHook findFishHook() {
        for (EntityFishHook hook : mc.world.getEntities(EntityFishHook.class, entity -> true)) {
            if (hook.getAngler() == mc.player) {
                return hook;
            }
        }
        return null;
    }

    private boolean isFishCaught(EntityFishHook fishHook) {
        // In Forge 1.12.2, we can check if the hook is in water and moving
        // A caught fish will cause the hook to bob and move erratically
        
        // Check if hook is in water
        if (!fishHook.isInWater()) {
            previousTicksExisted = fishHook.ticksExisted;
            return false;
        }

        // Check hook motion - caught fish causes erratic movement
        double motionX = Math.abs(fishHook.motionX);
        double motionY = Math.abs(fishHook.motionY);
        double motionZ = Math.abs(fishHook.motionZ);

        // If there's significant vertical motion, fish might be caught
        if (motionY > 0.1) {
            return true;
        }

        // Check if hook is being pulled (caught fish pulls the hook)
        // Fish is caught when the hook moves suddenly after being still
        if (fishHook.ticksExisted > 100) {
            int ticksSinceExisted = fishHook.ticksExisted - previousTicksExisted;
            if (ticksSinceExisted > 0 && (motionX > 0.05 || motionZ > 0.05)) {
                return true;
            }
        }

        previousTicksExisted = fishHook.ticksExisted;
        return false;
    }

    private void catchFish() {
        if (mc.player == null) {
            return;
        }

        // Check rod durability if no break is enabled
        if (ModuleConfigs.autoFish.noBreak) {
            ItemStack heldItem = mc.player.getHeldItem(EnumHand.MAIN_HAND);
            if (heldItem.getItem() == Items.FISHING_ROD) {
                if (heldItem.getItemDamage() >= heldItem.getMaxDamage() - 5) {
                    LucidDreaming.LOGGER.info("Fishing rod is about to break, stopping");
                    setEnabled(false);
                    return;
                }
            }
        }

        // Reel in the fish
        mc.playerController.processRightClick(mc.player, mc.world, EnumHand.MAIN_HAND);
        
        // Schedule recast
        lastRecastTime = System.currentTimeMillis();
        waitingForRecast = true;

        LucidDreaming.LOGGER.info("Fish caught, recasting in {}ms", ModuleConfigs.autoFish.recastDelay);
    }

    private void castRod() {
        if (mc.player == null) {
            return;
        }

        // Check if holding fishing rod
        ItemStack heldItem = mc.player.getHeldItem(EnumHand.MAIN_HAND);
        if (heldItem.getItem() != Items.FISHING_ROD) {
            LucidDreaming.LOGGER.info("Not holding a fishing rod");
            return;
        }

        // Cast the rod
        mc.playerController.processRightClick(mc.player, mc.world, EnumHand.MAIN_HAND);
        LucidDreaming.LOGGER.info("Fishing rod cast");
    }

    private void recast() {
        castRod();
    }
}