package com.luciddreaming.pathfinding;

import com.luciddreaming.LucidDreaming;
import com.luciddreaming.config.ModuleConfigs;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.BlockPos;

import java.util.*;

public class AStarPathFinder {
    private final IGoal goal;
    private final BlockPos start;
    private final int maxIterations;
    private final int maxDistance;
    private final long maxTime;
    
    private static final int[][] DIRECTIONS = {
        {0, 0, 1},  // +Z
        {0, 0, -1}, // -Z
        {0, 1, 0},  // +Y (jump/climb)
        {0, -1, 0}, // -Y (fall)
        {1, 0, 0},  // +X
        {-1, 0, 0}, // -X
        {1, 0, 1},  // diagonal
        {1, 0, -1},
        {-1, 0, 1},
        {-1, 0, -1},
        {1, 1, 0},  // diagonal up
        {1, -1, 0}, // diagonal down
        {-1, 1, 0},
        {-1, -1, 0},
        {0, 1, 1},  // diagonal forward/back
        {0, 1, -1},
        {0, -1, 1},
        {0, -1, -1}
    };
    
    public AStarPathFinder(BlockPos start, IGoal goal) {
        this(start, goal, ModuleConfigs.autoWalk.maxPathfindingDistance, 
             ModuleConfigs.autoWalk.maxPathfindingTime);
    }
    
    public AStarPathFinder(BlockPos start, IGoal goal, int maxDistance, int maxTime) {
        this.start = start;
        this.goal = goal;
        this.maxIterations = 10000;
        this.maxDistance = maxDistance;
        this.maxTime = maxTime;
    }
    
    public Path findPath() {
        LucidDreaming.LOGGER.info("Starting pathfinding from {} to {}", start, goal.getGoalPos());
        
        PriorityQueue<PathNode> openSet = new PriorityQueue<>();
        Set<PathNode> closedSet = new HashSet<>();
        
        PathNode startNode = new PathNode(start.getX(), start.getY(), start.getZ(), goal);
        openSet.add(startNode);
        
        int iterations = 0;
        long startTime = System.currentTimeMillis();
        
        while (!openSet.isEmpty() && iterations < maxIterations) {
            iterations++;
            
            // Check time limit
            if (System.currentTimeMillis() - startTime > maxTime) {
                LucidDreaming.LOGGER.warn("Pathfinding exceeded time limit");
                break;
            }
            
            // Get node with lowest f value
            PathNode current = openSet.poll();
            
            // Check if we reached the goal
            if (goal.isInGoal(current.x, current.y, current.z)) {
                LucidDreaming.LOGGER.info("Path found in {} iterations", iterations);
                return new Path(current);
            }
            
            // Check if we're too far from start
            double distanceFromStart = Math.sqrt(
                Math.pow(current.x - start.getX(), 2) +
                Math.pow(current.y - start.getY(), 2) +
                Math.pow(current.z - start.getZ(), 2)
            );
            
            if (distanceFromStart > maxDistance) {
                LucidDreaming.LOGGER.warn("Pathfinding exceeded max distance");
                continue;
            }
            
            closedSet.add(current);
            
            // Explore neighbors
            for (int[] dir : DIRECTIONS) {
                int newX = current.x + dir[0];
                int newY = current.y + dir[1];
                int newZ = current.z + dir[2];
                
                BlockPos newPos = new BlockPos(newX, newY, newZ);
                
                // Skip if already visited
                PathNode existingNode = findNode(openSet, newX, newY, newZ);
                if (existingNode != null && closedSet.contains(existingNode)) {
                    continue;
                }
                
                // Check if we can move to this position
                MoveResult result = canMoveTo(newPos, current.pos);
                if (!result.canMove) {
                    continue;
                }
                
                // Calculate new node
                PathNode newNode = new PathNode(newX, newY, newZ, current);
                
                // Add additional costs for breaking/placing blocks
                newNode.g += result.additionalCost;
                newNode.f = newNode.g + newNode.h;
                
                // Check if this is a better path
                if (existingNode != null) {
                    if (newNode.g < existingNode.g) {
                        existingNode.g = newNode.g;
                        existingNode.f = existingNode.g + existingNode.h;
                        existingNode.previous = current;
                    }
                } else {
                    openSet.add(newNode);
                }
            }
        }
        
        LucidDreaming.LOGGER.warn("Pathfinding failed after {} iterations", iterations);
        return null;
    }
    
    private MoveResult canMoveTo(BlockPos to, BlockPos from) {
        MoveResult result = new MoveResult();
        result.canMove = false;
        result.additionalCost = 0.0;
        
        // Check if the position is safe
        if (!MovementHelper.isSafe(to)) {
            return result;
        }
        
        // Check if we can stand on the block below
        if (!MovementHelper.canWalkOn(to.down())) {
            // Check if we can place a block
            if (ModuleConfigs.autoWalk.allowPlace) {
                result.additionalCost += MovementHelper.getPlaceCost(to.down());
                result.canMove = true;
            }
            return result;
        }
        
        // Check if we can fit in the space
        if (!MovementHelper.canWalkThrough(to)) {
            // Check if we can break the block
            if (ModuleConfigs.autoWalk.allowBreak) {
                IBlockState state = LucidDreaming.mc.world.getBlockState(to);
                double breakCost = MovementHelper.getBreakCost(state);
                if (breakCost < Double.MAX_VALUE) {
                    result.additionalCost += breakCost;
                    result.canMove = true;
                }
            }
            return result;
        }
        
        // Check if there's headroom
        if (!MovementHelper.canWalkThrough(to.up())) {
            // Check if we can break the block above
            if (ModuleConfigs.autoWalk.allowBreak) {
                IBlockState state = LucidDreaming.mc.world.getBlockState(to.up());
                double breakCost = MovementHelper.getBreakCost(state);
                if (breakCost < Double.MAX_VALUE) {
                    result.additionalCost += breakCost;
                    result.canMove = true;
                }
            }
            return result;
        }
        
        result.canMove = true;
        return result;
    }
    
    private PathNode findNode(PriorityQueue<PathNode> set, int x, int y, int z) {
        for (PathNode node : set) {
            if (node.x == x && node.y == y && node.z == z) {
                return node;
            }
        }
        return null;
    }
    
    private static class MoveResult {
        boolean canMove;
        double additionalCost;
    }
}