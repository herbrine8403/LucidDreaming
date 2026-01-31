package com.luciddreaming.pathfinding;

import net.minecraft.util.math.BlockPos;

public class PathNode implements Comparable<PathNode> {
    public final int x, y, z;
    public final BlockPos pos;
    
    public double g; // cost from start
    public double h; // heuristic to goal
    public double f; // g + h
    
    public PathNode previous;
    public IGoal goal;
    
    public PathNode(int x, int y, int z, IGoal goal) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.pos = new BlockPos(x, y, z);
        this.goal = goal;
        this.g = 0;
        this.h = goal.heuristic(x, y, z);
        this.f = this.g + this.h;
    }
    
    public PathNode(int x, int y, int z, PathNode previous) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.pos = new BlockPos(x, y, z);
        this.previous = previous;
        this.goal = previous.goal;
        
        // Calculate cost from previous node
        double movementCost = calculateMovementCost(previous, this);
        this.g = previous.g + movementCost;
        this.h = goal.heuristic(x, y, z);
        this.f = this.g + this.h;
    }
    
    private double calculateMovementCost(PathNode from, PathNode to) {
        // Base cost for moving
        double cost = 1.0;
        
        // Add cost for height difference
        int dy = to.y - from.y;
        if (dy > 0) {
            // Going up costs more
            cost += dy * 2.0;
        } else if (dy < 0) {
            // Going down costs less
            cost += Math.abs(dy) * 0.5;
        }
        
        // Add cost for diagonal movement
        int dx = Math.abs(to.x - from.x);
        int dz = Math.abs(to.z - from.z);
        if (dx > 0 && dz > 0) {
            cost *= 1.414; // sqrt(2)
        }
        
        return cost;
    }
    
    @Override
    public int compareTo(PathNode other) {
        return Double.compare(this.f, other.f);
    }
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        PathNode that = (PathNode) obj;
        return x == that.x && y == that.y && z == that.z;
    }
    
    @Override
    public int hashCode() {
        return pos.hashCode();
    }
}