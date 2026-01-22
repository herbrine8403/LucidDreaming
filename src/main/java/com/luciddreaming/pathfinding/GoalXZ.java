package com.luciddreaming.pathfinding;

import net.minecraft.util.math.BlockPos;

public class GoalXZ implements IGoal {
    private final int x, z;
    
    public GoalXZ(int x, int z) {
        this.x = x;
        this.z = z;
    }
    
    public GoalXZ(BlockPos pos) {
        this(pos.getX(), pos.getZ());
    }
    
    @Override
    public boolean isInGoal(int x, int y, int z) {
        return x == this.x && z == this.z;
    }
    
    @Override
    public double heuristic(int x, int y, int z) {
        return Math.sqrt(Math.pow(x - this.x, 2) + Math.pow(z - this.z, 2));
    }
    
    @Override
    public double heuristic() {
        return 0;
    }
    
    @Override
    public BlockPos getGoalPos() {
        return new BlockPos(x, 0, z);
    }
}