package com.luciddreaming.pathfinding;

import net.minecraft.util.math.BlockPos;

public class GoalBlock implements IGoal {
    private final int x, y, z;
    
    public GoalBlock(int x, int y, int z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }
    
    public GoalBlock(BlockPos pos) {
        this(pos.getX(), pos.getY(), pos.getZ());
    }
    
    @Override
    public boolean isInGoal(int x, int y, int z) {
        return x == this.x && y == this.y && z == this.z;
    }
    
    @Override
    public double heuristic(int x, int y, int z) {
        return Math.sqrt(Math.pow(x - this.x, 2) + Math.pow(y - this.y, 2) + Math.pow(z - this.z, 2));
    }
    
    @Override
    public double heuristic() {
        return 0;
    }
    
    @Override
    public BlockPos getGoalPos() {
        return new BlockPos(x, y, z);
    }
}