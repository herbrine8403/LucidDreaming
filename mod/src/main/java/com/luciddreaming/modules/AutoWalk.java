package com.luciddreaming.modules;

import com.luciddreaming.LucidDreaming;
import com.luciddreaming.config.ModuleConfigs;
import com.luciddreaming.pathfinding.*;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;

import java.lang.reflect.Field;

public class AutoWalk extends Module {
    private final Minecraft mc = Minecraft.getMinecraft();
    
    private BlockPos targetPos = null;
    private IGoal goal = null;
    private Path currentPath = null;
    private int pathIndex = 0;
    private boolean hasTarget = false;
    private boolean isPathfinding = false;
    
    private BlockPos lastBreakPos = null;
    private int breakCooldown = 0;
    private BlockPos lastPlacePos = null;
    private int placeCooldown = 0;
    
    private static Field pressedField;
    
    static {
        try {
            pressedField = KeyBinding.class.getDeclaredField("pressed");
            pressedField.setAccessible(true);
        } catch (NoSuchFieldException e) {
            LucidDreaming.LOGGER.error("Failed to access KeyBinding.pressed field", e);
        }
    }

    public AutoWalk() {
        super("AutoWalk", "Automatically walk to a target coordinate using pathfinding", ModuleCategory.MOVEMENT);
    }

    @Override
    protected void onEnable() {
        LucidDreaming.LOGGER.info("AutoWalk enabled");
    }

    @Override
    protected void onDisable() {
        LucidDreaming.LOGGER.info("AutoWalk disabled");
        resetMovement();
        clearPath();
    }

    @Override
    public void onTick() {
        if (mc.world == null || mc.player == null) {
            return;
        }

        // Don't move if in a GUI
        if (mc.currentScreen != null) {
            resetMovement();
            return;
        }

        // Update cooldowns
        if (breakCooldown > 0) breakCooldown--;
        if (placeCooldown > 0) placeCooldown--;

        // Check if we have a target
        if (!hasTarget || goal == null) {
            resetMovement();
            return;
        }

        // Check if we're currently pathfinding
        if (isPathfinding) {
            return;
        }

        // Check if we have a path
        if (currentPath == null) {
            // Start pathfinding
            startPathfinding();
            return;
        }

        // Check if we reached the goal
        BlockPos playerPos = mc.player.getPosition();
        if (goal.isInGoal(playerPos.getX(), playerPos.getY(), playerPos.getZ())) {
            LucidDreaming.LOGGER.info("Reached target at {}", targetPos);
            clearTarget();
            return;
        }

        // Follow the path
        followPath();
    }

    private void startPathfinding() {
        isPathfinding = true;
        LucidDreaming.LOGGER.info("Starting pathfinding to {}", targetPos);
        LucidDreaming.LOGGER.info("Allow Break: {}, Allow Place: {}", 
            ModuleConfigs.autoWalk.allowBreak, ModuleConfigs.autoWalk.allowPlace);

        // Run pathfinding in a separate thread
        new Thread(() -> {
            try {
                BlockPos start = mc.player.getPosition();
                AStarPathFinder pathFinder = new AStarPathFinder(start, goal);
                currentPath = pathFinder.findPath();
                
                if (currentPath != null) {
                    pathIndex = 0;
                    LucidDreaming.LOGGER.info("Path found with {} nodes, total cost: {}", 
                        currentPath.getLength(), currentPath.getTotalCost());
                } else {
                    LucidDreaming.LOGGER.warn("Failed to find path");
                }
            } catch (Exception e) {
                LucidDreaming.LOGGER.error("Error during pathfinding", e);
            } finally {
                isPathfinding = false;
            }
        }).start();
    }

    private void followPath() {
        if (currentPath == null || pathIndex >= currentPath.getLength()) {
            return;
        }

        BlockPos target = currentPath.getCurrentPosition(pathIndex);
        BlockPos playerPos = mc.player.getPosition();

        // Check if we need to break or place blocks
        handleBlockActions(target);

        // Check if we reached the current path node
        double distance = playerPos.distanceSq(target);
        
        if (distance < 1.0) { // Within 1 block
            pathIndex++;
            if (pathIndex >= currentPath.getLength()) {
                LucidDreaming.LOGGER.info("Path completed");
                clearTarget();
                return;
            }
            target = currentPath.getCurrentPosition(pathIndex);
        }

        // Move towards current path node
        moveTowardsPosition(target);
    }

    private void handleBlockActions(BlockPos target) {
        BlockPos playerPos = mc.player.getPosition();
        
        // Check if we need to break a block at the target position
        if (ModuleConfigs.autoWalk.allowBreak && breakCooldown == 0) {
            IBlockState targetState = mc.world.getBlockState(target);
            if (!MovementHelper.canWalkThrough(target) && MovementHelper.canBreak(targetState)) {
                // Break the block
                if (tryBreakBlock(target)) {
                    breakCooldown = 10; // Cooldown between breaks
                    lastBreakPos = target;
                }
            }
            
            // Check block above
            IBlockState aboveState = mc.world.getBlockState(target.up());
            if (!MovementHelper.canWalkThrough(target.up()) && MovementHelper.canBreak(aboveState)) {
                if (tryBreakBlock(target.up())) {
                    breakCooldown = 10;
                    lastBreakPos = target.up();
                }
            }
        }
        
        // Check if we need to place a block
        if (ModuleConfigs.autoWalk.allowPlace && placeCooldown == 0) {
            IBlockState belowState = mc.world.getBlockState(target.down());
            if (!MovementHelper.canWalkOn(target.down()) && hasPlaceableBlock()) {
                if (tryPlaceBlock(target.down())) {
                    placeCooldown = 10;
                    lastPlacePos = target.down();
                }
            }
        }
    }

    private boolean tryBreakBlock(BlockPos pos) {
        if (mc.player.capabilities.isCreativeMode) {
            mc.world.setBlockToAir(pos);
            return true;
        }
        
        // Check if player has a tool
        ItemStack heldItem = mc.player.getHeldItemMainhand();
        if (heldItem.isEmpty()) {
            return false;
        }
        
        // Try to break the block
        mc.playerController.clickBlock(pos, mc.objectMouseOver.sideHit);
        mc.player.swingArm(net.minecraft.util.EnumHand.MAIN_HAND);
        
        return true;
    }

    private boolean tryPlaceBlock(BlockPos pos) {
        if (mc.player.capabilities.isCreativeMode) {
            mc.world.setBlockState(pos, Blocks.DIRT.getDefaultState());
            return true;
        }
        
        // Check if player has placeable blocks
        ItemStack heldItem = mc.player.getHeldItemMainhand();
        if (heldItem.isEmpty()) {
            return false;
        }
        
        // Try to place the block
        mc.playerController.processRightClickBlock(mc.player, mc.world, 
            mc.objectMouseOver.getBlockPos(), mc.objectMouseOver.sideHit, 
            mc.objectMouseOver.hitVec, net.minecraft.util.EnumHand.MAIN_HAND);
        
        return true;
    }

    private boolean hasPlaceableBlock() {
        // Check if player has placeable blocks in inventory
        for (int i = 0; i < mc.player.inventory.mainInventory.size(); i++) {
            ItemStack stack = mc.player.inventory.mainInventory.get(i);
            if (!stack.isEmpty()) {
                return true;
            }
        }
        return false;
    }

    private void moveTowardsPosition(BlockPos target) {
        if (mc.player == null || target == null) {
            return;
        }

        // Calculate direction to target
        double dx = target.getX() - mc.player.posX;
        double dz = target.getZ() - mc.player.posZ;
        double distance = Math.sqrt(dx * dx + dz * dz);

        if (distance < 0.1) {
            return;
        }

        // Normalize direction
        double moveX = dx / distance;
        double moveZ = dz / distance;

        // Apply speed multiplier
        double speed = Math.min(ModuleConfigs.autoWalk.speed, 1.0); // Cap at 1.0 for pathfinding
        
        // Set player movement
        mc.player.moveForward = (float) speed;
        
        // Rotate player towards target
        float yaw = (float) (Math.atan2(dz, dx) * 180.0 / Math.PI) - 90.0f;
        mc.player.rotationYaw = yaw;
        
        // Handle jumping for height differences
        int dy = target.getY() - mc.player.getPosition().getY();
        if (dy > 0 && mc.player.onGround) {
            mc.player.jump();
        }
        
        // Set movement keys using reflection
        try {
            KeyBinding moveForward = mc.gameSettings.keyBindForward;
            KeyBinding moveLeft = mc.gameSettings.keyBindLeft;
            KeyBinding moveRight = mc.gameSettings.keyBindRight;
            KeyBinding moveBack = mc.gameSettings.keyBindBack;
            
            // Reset all movement keys
            pressedField.setBoolean(moveForward, false);
            pressedField.setBoolean(moveBack, false);
            pressedField.setBoolean(moveLeft, false);
            pressedField.setBoolean(moveRight, false);
            
            // Set forward movement
            pressedField.setBoolean(moveForward, true);
        } catch (Exception e) {
            LucidDreaming.LOGGER.error("Failed to set movement keys", e);
        }
    }

    private void resetMovement() {
        if (mc.player == null) {
            return;
        }

        try {
            mc.player.moveForward = 0;
            
            KeyBinding moveForward = mc.gameSettings.keyBindForward;
            KeyBinding moveLeft = mc.gameSettings.keyBindLeft;
            KeyBinding moveRight = mc.gameSettings.keyBindRight;
            KeyBinding moveBack = mc.gameSettings.keyBindBack;
            
            pressedField.setBoolean(moveForward, false);
            pressedField.setBoolean(moveBack, false);
            pressedField.setBoolean(moveLeft, false);
            pressedField.setBoolean(moveRight, false);
        } catch (Exception e) {
            LucidDreaming.LOGGER.error("Failed to reset movement keys", e);
        }
    }

    private void clearPath() {
        currentPath = null;
        pathIndex = 0;
    }

    public void setTarget(BlockPos pos) {
        this.targetPos = pos;
        this.goal = new GoalBlock(pos);
        this.hasTarget = true;
        this.currentPath = null;
        this.pathIndex = 0;
        LucidDreaming.LOGGER.info("AutoWalk target set to {}", pos);
    }

    public void setTarget(int x, int y, int z) {
        setTarget(new BlockPos(x, y, z));
    }

    public void clearTarget() {
        this.targetPos = null;
        this.goal = null;
        this.hasTarget = false;
        clearPath();
        resetMovement();
        LucidDreaming.LOGGER.info("AutoWalk target cleared");
    }

    public BlockPos getTarget() {
        return targetPos;
    }

    public boolean hasTarget() {
        return hasTarget;
    }
    
    public boolean isPathfinding() {
        return isPathfinding;
    }
    
    public Path getCurrentPath() {
        return currentPath;
    }
}