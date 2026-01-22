package com.luciddreaming.pathfinding;

import com.luciddreaming.LucidDreaming;
import com.luciddreaming.config.ModuleConfigs;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;

public class MovementHelper {
    private static final Minecraft mc = Minecraft.getMinecraft();
    
    public static boolean canWalkOn(BlockPos pos) {
        if (mc.world == null) return false;
        
        IBlockState state = mc.world.getBlockState(pos);
        Block block = state.getBlock();
        
        // Can walk on solid blocks
        return state.isFullBlock() || state.isFullCube() || 
               block == Blocks.STONE || block == Blocks.DIRT || block == Blocks.GRASS ||
               block == Blocks.COBBLESTONE || block == Blocks.PLANKS ||
               block == Blocks.SAND || block == Blocks.GRAVEL;
    }
    
    public static boolean canWalkThrough(BlockPos pos) {
        if (mc.world == null) return false;
        
        IBlockState state = mc.world.getBlockState(pos);
        Block block = state.getBlock();
        Material material = state.getMaterial();
        
        // Can walk through air, water, plants, etc.
        return material == Material.AIR || material == Material.WATER || material == Material.PLANTS ||
               material == Material.VINE || material == Material.LEAVES ||
               block == Blocks.TALLGRASS || block == Blocks.FIRE ||
               state.getBlock().isPassable(mc.world, pos);
    }
    
    public static boolean canMoveTo(BlockPos pos) {
        // Check if we can stand on the block below
        if (!canWalkOn(pos.down())) {
            return false;
        }
        
        // Check if we can fit in the space
        if (!canWalkThrough(pos)) {
            // If we can't walk through, check if we can break it
            if (!ModuleConfigs.autoWalk.allowBreak) {
                return false;
            }
            
            // Check if the block is breakable
            IBlockState state = mc.world.getBlockState(pos);
            if (!canBreak(state)) {
                return false;
            }
        }
        
        // Check if there's headroom
        if (!canWalkThrough(pos.up())) {
            // If we can't walk through, check if we can break it
            if (!ModuleConfigs.autoWalk.allowBreak) {
                return false;
            }
            
            IBlockState state = mc.world.getBlockState(pos.up());
            if (!canBreak(state)) {
                return false;
            }
        }
        
        return true;
    }
    
    public static boolean canMoveDiagonal(BlockPos from, BlockPos to) {
        // Check if both adjacent blocks are walkable
        BlockPos adj1 = new BlockPos(from.getX(), to.getY(), from.getZ());
        BlockPos adj2 = new BlockPos(to.getX(), to.getY(), from.getZ());
        
        if (!canWalkThrough(adj1) || !canWalkThrough(adj2)) {
            return false;
        }
        
        return canMoveTo(to);
    }
    
    public static boolean isSafe(BlockPos pos) {
        if (mc.world == null) return false;
        
        // Check for dangerous blocks (lava, fire, cactus)
        IBlockState state = mc.world.getBlockState(pos);
        Block block = state.getBlock();
        
        if (block == Blocks.LAVA || block == Blocks.FLOWING_LAVA ||
            block == Blocks.FIRE || block == Blocks.CACTUS) {
            return false;
        }
        
        // Check if block below is dangerous
        IBlockState belowState = mc.world.getBlockState(pos.down());
        Block belowBlock = belowState.getBlock();
        
        if (belowBlock == Blocks.LAVA || belowBlock == Blocks.FLOWING_LAVA ||
            belowBlock == Blocks.MAGMA) {
            return false;
        }
        
        return true;
    }
    
    public static boolean canBreak(IBlockState state) {
        Block block = state.getBlock();
        
        // Cannot break bedrock, obsidian, etc.
        if (block == Blocks.BEDROCK || block == Blocks.OBSIDIAN || 
            block == Blocks.END_PORTAL || block == Blocks.END_PORTAL_FRAME ||
            block == Blocks.COMMAND_BLOCK || block == Blocks.BARRIER) {
            return false;
        }
        
        // Cannot break unbreakable blocks
        float hardness = state.getBlockHardness(mc.world, BlockPos.ORIGIN);
        return hardness >= 0 && hardness < 50.0f;
    }
    
    public static double getBreakCost(IBlockState state) {
        if (!canBreak(state)) {
            return Double.MAX_VALUE;
        }
        
        // Base cost for breaking
        double cost = ModuleConfigs.autoWalk.breakBlockCost;
        
        // Add cost based on block hardness
        float hardness = state.getBlockHardness(mc.world, BlockPos.ORIGIN);
        cost += hardness * 2.0;
        
        return cost;
    }
    
    public static double getPlaceCost(BlockPos pos) {
        if (!ModuleConfigs.autoWalk.allowPlace) {
            return Double.MAX_VALUE;
        }
        
        return ModuleConfigs.autoWalk.placeBlockCost;
    }
}