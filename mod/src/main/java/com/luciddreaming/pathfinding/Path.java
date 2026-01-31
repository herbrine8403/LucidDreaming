package com.luciddreaming.pathfinding;

import net.minecraft.util.math.BlockPos;

import java.util.ArrayList;
import java.util.List;

public class Path {
    private final PathNode endNode;
    private final List<BlockPos> positions;
    
    public Path(PathNode endNode) {
        this.endNode = endNode;
        this.positions = new ArrayList<>();
        
        // Reconstruct path from end to start
        PathNode current = endNode;
        while (current != null) {
            positions.add(0, current.pos); // Add to front
            current = current.previous;
        }
    }
    
    public List<BlockPos> getPositions() {
        return positions;
    }
    
    public BlockPos getCurrentPosition(int index) {
        if (index >= 0 && index < positions.size()) {
            return positions.get(index);
        }
        return null;
    }
    
    public BlockPos getNextPosition(int currentIndex) {
        return getCurrentPosition(currentIndex + 1);
    }
    
    public BlockPos getStart() {
        return positions.get(0);
    }
    
    public BlockPos getEnd() {
        return positions.get(positions.size() - 1);
    }
    
    public int getLength() {
        return positions.size();
    }
    
    public boolean isFinished(int currentIndex) {
        return currentIndex >= positions.size() - 1;
    }
    
    public double getTotalCost() {
        return endNode != null ? endNode.g : 0;
    }
}