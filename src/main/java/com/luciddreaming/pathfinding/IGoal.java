package com.luciddreaming.pathfinding;

import net.minecraft.util.math.BlockPos;

public interface IGoal {
    boolean isInGoal(int x, int y, int z);
    
    double heuristic(int x, int y, int z);
    
    double heuristic();
    
    default BlockPos getGoalPos() {
        return new BlockPos(0, 0, 0);
    }
}