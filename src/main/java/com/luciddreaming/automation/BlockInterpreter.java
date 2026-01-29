package com.luciddreaming.automation;

import com.luciddreaming.LucidDreaming;
import com.luciddreaming.modules.AutoWalk;
import com.luciddreaming.modules.ModuleManager;
import com.luciddreaming.utils.DirectMainThreadExecutor;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.scoreboard.Score;
import net.minecraft.scoreboard.ScoreObjective;
import net.minecraft.scoreboard.Scoreboard;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;

import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * 积木解释器
 * 负责执行积木脚本
 */
public class BlockInterpreter {
    private final AutomationTask task;
    private final Minecraft mc;
    private final ModuleManager moduleManager;

    public BlockInterpreter(AutomationTask task) {
        this.task = task;
        this.mc = Minecraft.getMinecraft();
        this.moduleManager = LucidDreaming.moduleManager;
    }

    /**
     * 执行任务
     */
    public void execute() {
        if (mc.world == null || mc.player == null) {
            LucidDreaming.LOGGER.warn("Cannot execute automation: world or player is null");
            return;
        }

        try {
            // 执行触发器积木（如果有）
            if (task.getTrigger() != null) {
                executeBlock(task.getTrigger());
            }

            // 执行主积木链
            Block current = task.getBlocks().isEmpty() ? null : task.getBlocks().get(0);
            while (current != null && !task.isStopped()) {
                executeBlock(current);
                current = current.getNextBlock();
            }
        } catch (InterruptedException e) {
            LucidDreaming.LOGGER.info("Task execution interrupted: {}", task.getName());
            Thread.currentThread().interrupt();
        } catch (Exception e) {
            LucidDreaming.LOGGER.error("Error executing task: {}", task.getName(), e);
        }
    }

    /**
     * 执行单个积木
     */
    private void executeBlock(Block block) throws InterruptedException {
        if (task.isStopped()) {
            throw new InterruptedException("Task stopped");
        }

        if (block == null || block.getType() == null) {
            return;
        }

        switch (block.getType()) {
            // 事件积木（不执行，仅作为触发器）
            case EVENT_SCOREBOARD_CHANGED:
            case EVENT_SCOREBOARD_CHANGED_FROM_TO:
            case EVENT_HEALTH_CHANGED:
            case EVENT_HEALTH_CHANGED_FROM_TO:
            case EVENT_ENTITY_NEARBY:
                // 事件积木不执行，仅用于触发
                break;

            // 运动积木
            case MOTION_FACE_DIRECTION:
                executeFaceDirection(block);
                break;
            case MOTION_TURN_LEFT:
            case MOTION_TURN_RIGHT:
                executeTurn(block);
                break;
            case MOTION_MOVE_FORWARD:
            case MOTION_MOVE_BACKWARD:
            case MOTION_MOVE_LEFT:
            case MOTION_MOVE_RIGHT:
                executeMove(block);
                break;
            case MOTION_MOVE_TO:
                executeMoveTo(block);
                break;
            case MOTION_INTERACT:
            case MOTION_INTERACT_NEAREST:
                executeInteract(block);
                break;

            // 侦测积木（仅用于条件判断，不执行）
            case SENSING_SCOREBOARD_CHANGED:
            case SENSING_SCOREBOARD_CHANGED_FROM_TO:
            case SENSING_HEALTH_CHANGED:
            case SENSING_HEALTH_CHANGED_FROM_TO:
            case SENSING_ENTITY_NEARBY:
            case SENSING_GET_HEALTH:
            case SENSING_GET_SCOREBOARD_VALUE:
            case SENSING_GET_PLAYER_POS:
            case SENSING_GET_PLAYER_YAW:
            case SENSING_GET_PLAYER_PITCH:
                // 侦测积木不执行，仅用于条件判断
                break;

            // 控制积木
            case CONTROL_WAIT:
                executeWait(block);
                break;
            case CONTROL_IF:
                executeIf(block);
                break;
            case CONTROL_IF_ELSE:
                executeIfElse(block);
                break;
            case CONTROL_REPEAT:
                executeRepeat(block);
                break;
            case CONTROL_FOREVER:
                executeForever(block);
                break;
            case CONTROL_STOP:
                task.stop();
                break;
            case CONTROL_COMMENT:
                // 注释积木，不执行
                break;

            default:
                LucidDreaming.LOGGER.warn("Unknown block type: {}", block.getType());
        }
    }

    /**
     * 执行面向方向
     */
    private void executeFaceDirection(Block block) {
        Object yawObj = block.getParameter("yaw");
        if (yawObj instanceof Number) {
            float yaw = ((Number) yawObj).floatValue();
            final float finalYaw = yaw;
            DirectMainThreadExecutor.executeAndWait(() -> {
                if (mc.player != null) {
                    mc.player.rotationYaw = finalYaw;
                }
            });
        }
    }

    /**
     * 执行旋转
     */
    private void executeTurn(Block block) {
        Object degreesObj = block.getParameter("degrees");
        if (degreesObj instanceof Number) {
            float degrees = ((Number) degreesObj).floatValue();
            final float finalDegrees = block.getType() == BlockType.MOTION_TURN_LEFT ? -degrees : degrees;
            DirectMainThreadExecutor.executeAndWait(() -> {
                if (mc.player != null) {
                    mc.player.rotationYaw += finalDegrees;
                }
            });
        }
    }

    /**
     * 执行移动
     */
    private void executeMove(Block block) {
        Object blocksObj = block.getParameter("blocks");
        if (blocksObj instanceof Number) {
            int blocks = ((Number) blocksObj).intValue();

            for (int i = 0; i < blocks && !task.isStopped(); i++) {
                final int direction;
                switch (block.getType()) {
                    case MOTION_MOVE_FORWARD:
                        direction = 0;
                        break;
                    case MOTION_MOVE_BACKWARD:
                        direction = 180;
                        break;
                    case MOTION_MOVE_LEFT:
                        direction = -90;
                        break;
                    case MOTION_MOVE_RIGHT:
                        direction = 90;
                        break;
                    default:
                        direction = 0;
                }

                final int finalDirection = direction;
                DirectMainThreadExecutor.executeAndWait(() -> {
                    if (mc.player != null) {
                        float yaw = mc.player.rotationYaw;
                        float rad = (float) Math.toRadians(yaw + finalDirection);
                        double dx = -Math.sin(rad);
                        double dz = Math.cos(rad);

                        BlockPos newPos = new BlockPos(
                            mc.player.posX + dx,
                            mc.player.posY,
                            mc.player.posZ + dz
                        );

                        AutoWalk autoWalk = (AutoWalk) moduleManager.getModule("AutoWalk");
                        if (autoWalk != null) {
                            autoWalk.setTarget(newPos.getX(), newPos.getY(), newPos.getZ());
                        }
                    }
                });

                try {
                    TimeUnit.MILLISECONDS.sleep(500);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    return;
                }
            }
        }
    }

    /**
     * 执行移动到指定位置
     */
    private void executeMoveTo(Block block) {
        Object xObj = block.getParameter("x");
        Object yObj = block.getParameter("y");
        Object zObj = block.getParameter("z");

        if (xObj instanceof Number && yObj instanceof Number && zObj instanceof Number) {
            int x = ((Number) xObj).intValue();
            int y = ((Number) yObj).intValue();
            int z = ((Number) zObj).intValue();

            final int finalX = x;
            final int finalY = y;
            final int finalZ = z;

            DirectMainThreadExecutor.executeAndWait(() -> {
                AutoWalk autoWalk = (AutoWalk) moduleManager.getModule("AutoWalk");
                if (autoWalk != null) {
                    autoWalk.setTarget(finalX, finalY, finalZ);
                }
            });

            // 等待移动完成
            AutoWalk autoWalk = (AutoWalk) moduleManager.getModule("AutoWalk");
            if (autoWalk != null) {
                while (autoWalk.isPathfinding() && !task.isStopped()) {
                    try {
                        TimeUnit.MILLISECONDS.sleep(500);
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                        return;
                    }
                }
            }
        }
    }

    /**
     * 执行交互
     */
    private void executeInteract(Block block) {
        Object nearestObj = block.getParameter("nearest");
        boolean nearest = nearestObj instanceof Boolean && (Boolean) nearestObj;

        DirectMainThreadExecutor.executeAndWait(() -> {
            if (mc.player != null && mc.world != null) {
                Entity target = null;

                if (nearest) {
                    // 查找最近的实体
                    double minDistance = Double.MAX_VALUE;
                    for (Entity entity : mc.world.loadedEntityList) {
                        if (entity != mc.player && entity instanceof EntityPlayer) {
                            double distance = mc.player.getDistance(entity);
                            if (distance < minDistance) {
                                minDistance = distance;
                                target = entity;
                            }
                        }
                    }
                } else {
                    // 与面前的实体交互
                    target = mc.objectMouseOver != null ? mc.objectMouseOver.entityHit : null;
                }

                if (target != null) {
                    mc.playerController.interactWithEntity(mc.player, target, EnumHand.MAIN_HAND);
                }
            }
        });
    }

    /**
     * 执行等待
     */
    private void executeWait(Block block) throws InterruptedException {
        Object secondsObj = block.getParameter("seconds");
        if (secondsObj instanceof Number) {
            long milliseconds = (long) (((Number) secondsObj).doubleValue() * 1000);
            long elapsed = 0;
            while (elapsed < milliseconds && !task.isStopped()) {
                long sleep = Math.min(100, milliseconds - elapsed);
                TimeUnit.MILLISECONDS.sleep(sleep);
                elapsed += sleep;
            }
        }
    }

    /**
     * 执行如果
     */
    private void executeIf(Block block) throws InterruptedException {
        if (evaluateCondition(block)) {
            Block firstChild = block.getChildren().isEmpty() ? null : block.getChildren().get(0);
            Block current = firstChild;
            while (current != null && !task.isStopped()) {
                executeBlock(current);
                current = current.getNextBlock();
            }
        }
    }

    /**
     * 执行如果否则
     */
    private void executeIfElse(Block block) throws InterruptedException {
        if (evaluateCondition(block)) {
            // 执行第一个分支
            Block firstChild = block.getChildren().isEmpty() ? null : block.getChildren().get(0);
            Block current = firstChild;
            while (current != null && !task.isStopped()) {
                executeBlock(current);
                current = current.getNextBlock();
            }
        } else {
            // 执行第二个分支
            if (block.getChildren().size() > 1) {
                Block elseChild = block.getChildren().get(1);
                Block current = elseChild;
                while (current != null && !task.isStopped()) {
                    executeBlock(current);
                    current = current.getNextBlock();
                }
            }
        }
    }

    /**
     * 执行重复
     */
    private void executeRepeat(Block block) throws InterruptedException {
        Object timesObj = block.getParameter("times");
        if (timesObj instanceof Number) {
            int times = ((Number) timesObj).intValue();

            for (int i = 0; i < times && !task.isStopped(); i++) {
                Block firstChild = block.getChildren().isEmpty() ? null : block.getChildren().get(0);
                Block current = firstChild;
                while (current != null && !task.isStopped()) {
                    executeBlock(current);
                    current = current.getNextBlock();
                }
            }
        }
    }

    /**
     * 执行永远循环
     */
    private void executeForever(Block block) throws InterruptedException {
        while (!task.isStopped()) {
            Block firstChild = block.getChildren().isEmpty() ? null : block.getChildren().get(0);
            Block current = firstChild;
            while (current != null && !task.isStopped()) {
                executeBlock(current);
                current = current.getNextBlock();
            }
        }
    }

    /**
     * 评估条件
     */
    private boolean evaluateCondition(Block block) {
        Object conditionObj = block.getParameter("condition");
        if (conditionObj instanceof Boolean) {
            return (Boolean) conditionObj;
        }
        return false;
    }

    /**
     * 获取玩家血量
     */
    public float getPlayerHealth() {
        if (mc.player != null) {
            return mc.player.getHealth();
        }
        return 0;
    }

    /**
     * 获取计分板值
     */
    public String getScoreboardValue(String objectiveName) {
        if (mc.world != null) {
            Scoreboard scoreboard = mc.world.getScoreboard();
            if (scoreboard != null) {
                ScoreObjective objective = scoreboard.getObjective(objectiveName);
                if (objective != null) {
                    Score score = scoreboard.getOrCreateScore(mc.player.getName(), objective);
                    return String.valueOf(score.getScorePoints());
                }
            }
        }
        return "0";
    }

    /**
     * 检测实体附近
     */
    public boolean isEntityNearby(double radius) {
        if (mc.player != null && mc.world != null) {
            for (Entity entity : mc.world.loadedEntityList) {
                if (entity != mc.player && mc.player.getDistance(entity) <= radius) {
                    return true;
                }
            }
        }
        return false;
    }
}