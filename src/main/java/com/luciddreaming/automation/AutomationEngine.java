package com.luciddreaming.automation;

import com.luciddreaming.LucidDreaming;
import com.luciddreaming.modules.*;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.multiplayer.WorldClient;
import net.minecraft.entity.Entity;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.Vec3d;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class AutomationEngine {
    private static final Minecraft mc = Minecraft.getMinecraft();
    private final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(2);
    private final Map<String, AutomationTask> activeTasks = new ConcurrentHashMap<>();
    
    public AutomationEngine() {
        // 定期检查自动化任务的触发条件
        scheduler.scheduleAtFixedRate(this::checkTriggers, 0, 50, TimeUnit.MILLISECONDS); // 每50ms检查一次
    }
    
    public void addTask(AutomationTask task) {
        activeTasks.put(task.getId(), task);
        LucidDreaming.LOGGER.info("Added automation task: {}", task.getName());
    }
    
    public void removeTask(String taskId) {
        activeTasks.remove(taskId);
        LucidDreaming.LOGGER.info("Removed automation task: {}", taskId);
    }
    
    public void startTask(String taskId) {
        AutomationTask task = activeTasks.get(taskId);
        if (task != null) {
            task.setEnabled(true);
            LucidDreaming.LOGGER.info("Started automation task: {}", task.getName());
        }
    }
    
    public void stopTask(String taskId) {
        AutomationTask task = activeTasks.get(taskId);
        if (task != null) {
            task.setEnabled(false);
            LucidDreaming.LOGGER.info("Stopped automation task: {}", task.getName());
        }
    }
    
    public boolean isTaskRunning(String taskId) {
        AutomationTask task = activeTasks.get(taskId);
        return task != null && task.isEnabled();
    }
    
    public void executeTask(String taskId) {
        AutomationTask task = activeTasks.get(taskId);
        if (task != null) {
            scheduler.submit(() -> executeTaskInternal(task));
        }
    }
    
    private void executeTaskInternal(AutomationTask task) {
        if (!task.isEnabled()) return;
        
        try {
            // 解析并执行任务中的指令
            for (AutomationInstruction instruction : task.getInstructions()) {
                if (!task.isEnabled()) break; // 如果任务被禁用，则停止执行
                
                executeInstruction(instruction);
                
                // 如果指令需要等待时间
                if (instruction.getDelay() > 0) {
                    Thread.sleep(instruction.getDelay());
                }
            }
        } catch (Exception e) {
            LucidDreaming.LOGGER.error("Error executing automation task: {}", task.getName(), e);
        }
    }
    
    private void executeInstruction(AutomationInstruction instruction) {
        String type = instruction.getType();
        Map<String, Object> params = instruction.getParams();
        
        switch (type) {
            case "move_steps":
                executeMoveSteps(params);
                break;
            case "turn_right":
                executeTurnRight(params);
                break;
            case "turn_left":
                executeTurnLeft(params);
                break;
            case "face_direction":
                executeFaceDirection(params);
                break;
            case "move_to_xyz":
                executeMoveToXYZ(params);
                break;
            case "interact_entity":
                executeInteractEntity(params);
                break;
            case "jump":
                executeJump(params);
                break;
            case "sneak":
                executeSneak(params);
                break;
            case "sprint":
                executeSprint(params);
                break;
            case "enable_module":
            case "enable_autofish":
            case "enable_autoclicker":
            case "enable_autokill":
            case "enable_antikick":
            case "enable_autowalk":
            case "enable_norender":
            case "enable_fakeblack":
                executeEnableModule(type, params);
                break;
            case "disable_module":
                executeDisableModule(params);
                break;
            case "toggle_module":
                executeToggleModule(params);
                break;
            case "wait":
                executeWait(params);
                break;
            default:
                LucidDreaming.LOGGER.warn("Unknown instruction type: {}", type);
        }
    }
    
    private void executeMoveSteps(Map<String, Object> params) {
        double steps = Double.parseDouble(params.getOrDefault("steps", "1").toString());
        // 在游戏中移动指定步数
        EntityPlayerSP player = mc.player;
        if (player != null) {
            // 移动逻辑：让玩家向前移动
            double forward = steps; // 假设向前移动
            double motionX = -Math.sin(Math.toRadians(player.rotationYaw)) * forward;
            double motionZ = Math.cos(Math.toRadians(player.rotationYaw)) * forward;
            
            player.motionX = motionX;
            player.motionZ = motionZ;
        }
    }
    
    private void executeTurnRight(Map<String, Object> params) {
        double degrees = Double.parseDouble(params.getOrDefault("degrees", "90").toString());
        EntityPlayerSP player = mc.player;
        if (player != null) {
            player.rotationYaw += degrees;
            player.onUpdate();
        }
    }
    
    private void executeTurnLeft(Map<String, Object> params) {
        double degrees = Double.parseDouble(params.getOrDefault("degrees", "90").toString());
        EntityPlayerSP player = mc.player;
        if (player != null) {
            player.rotationYaw -= degrees;
            player.onUpdate();
        }
    }
    
    private void executeFaceDirection(Map<String, Object> params) {
        double direction = Double.parseDouble(params.getOrDefault("direction", "0").toString());
        EntityPlayerSP player = mc.player;
        if (player != null) {
            player.rotationYaw = (float) direction;
            player.onUpdate();
        }
    }
    
    private void executeMoveToXYZ(Map<String, Object> params) {
        double x = Double.parseDouble(params.getOrDefault("x", "0").toString());
        double y = Double.parseDouble(params.getOrDefault("y", "0").toString());
        double z = Double.parseDouble(params.getOrDefault("z", "0").toString());
        
        // 使用AutoWalk模块移动到指定位置
        AutoWalk autoWalk = (AutoWalk) LucidDreaming.moduleManager.getModule("AutoWalk");
        if (autoWalk != null) {
            autoWalk.setTarget((int)x, (int)y, (int)z);
            // 启用AutoWalk模块
            autoWalk.setEnabled(true);
        }
    }
    
    private void executeInteractEntity(Map<String, Object> params) {
        String entityType = (String) params.getOrDefault("entityType", "nearest");
        String interactionType = (String) params.getOrDefault("interactionType", "right_click");
        double maxDistance = Double.parseDouble(params.getOrDefault("maxDistance", "3").toString());
        
        EntityPlayerSP player = mc.player;
        WorldClient world = mc.world;
        
        if (player != null && world != null) {
            Entity targetEntity = null;
            
            // 查找最近的实体
            List<Entity> entities = world.loadedEntityList;
            for (Entity entity : entities) {
                if (entity.getDistance(player) <= maxDistance) {
                    if ("player".equals(entityType) && entity instanceof net.minecraft.entity.player.EntityPlayer) {
                        targetEntity = entity;
                        break;
                    } else if ("hostile".equals(entityType) && isHostileEntity(entity)) {
                        targetEntity = entity;
                        break;
                    } else if ("passive".equals(entityType) && isPassiveEntity(entity)) {
                        targetEntity = entity;
                        break;
                    } else if ("any".equals(entityType)) {
                        targetEntity = entity;
                        break;
                    } else if ("nearest".equals(entityType)) {
                        if (targetEntity == null || entity.getDistance(player) < targetEntity.getDistance(player)) {
                            targetEntity = entity;
                        }
                    }
                }
            }
            
            if (targetEntity != null) {
                if ("right_click".equals(interactionType)) {
                    // 模拟右键点击实体
                    playerController().processRightClick(player, world, targetEntity, EnumHand.MAIN_HAND);
                } else if ("left_click".equals(interactionType)) {
                    // 模拟左键攻击实体
                    playerController().attackEntity(player, targetEntity);
                } else if ("look_at".equals(interactionType)) {
                    // 面向实体
                    Vec3d entityPos = new Vec3d(targetEntity.posX, targetEntity.posY, targetEntity.posZ);
                    Vec3d playerPos = new Vec3d(player.posX, player.posY, player.posZ);
                    double dX = entityPos.x - playerPos.x;
                    double dZ = entityPos.z - playerPos.z;
                    float yaw = (float) Math.toDegrees(Math.atan2(dZ, dX)) - 90;
                    player.rotationYaw = yaw;
                }
            }
        }
    }
    
    private boolean isHostileEntity(Entity entity) {
        // 简单判断是否为敌对实体
        return entity instanceof net.minecraft.entity.monster.EntityMob;
    }
    
    private boolean isPassiveEntity(Entity entity) {
        // 简单判断是否为被动实体
        return entity instanceof net.minecraft.entity.passive.EntityAnimal;
    }
    
    private void executeJump(Map<String, Object> params) {
        EntityPlayerSP player = mc.player;
        if (player != null) {
            player.jump();
        }
    }
    
    private void executeSneak(Map<String, Object> params) {
        EntityPlayerSP player = mc.player;
        if (player != null) {
            player.setSneaking(true);
        }
    }
    
    private void executeSprint(Map<String, Object> params) {
        EntityPlayerSP player = mc.player;
        if (player != null) {
            player.setSprinting(true);
        }
    }
    
    private void executeEnableModule(String type, Map<String, Object> params) {
        String moduleName = getModuleNameFromType(type);
        Module module = LucidDreaming.moduleManager.getModule(moduleName);
        if (module != null) {
            // 从参数获取模块名，如果没有则使用默认的
            String targetModule = (String) params.get("moduleName");
            if (targetModule != null) {
                module = LucidDreaming.moduleManager.getModule(targetModule);
            }
            
            if (module != null) {
                module.setEnabled(true);
            }
        }
    }
    
    private void executeDisableModule(Map<String, Object> params) {
        String moduleName = (String) params.get("moduleName");
        if (moduleName != null) {
            Module module = LucidDreaming.moduleManager.getModule(moduleName);
            if (module != null) {
                module.setEnabled(false);
            }
        }
    }
    
    private void executeToggleModule(Map<String, Object> params) {
        String moduleName = (String) params.get("moduleName");
        if (moduleName != null) {
            Module module = LucidDreaming.moduleManager.getModule(moduleName);
            if (module != null) {
                module.setEnabled(!module.isEnabled());
            }
        }
    }
    
    private void executeWait(Map<String, Object> params) {
        double seconds = Double.parseDouble(params.getOrDefault("seconds", "1").toString());
        try {
            Thread.sleep((long) (seconds * 1000));
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
    
    private String getModuleNameFromType(String type) {
        switch (type) {
            case "enable_autofish": return "AutoFish";
            case "enable_autoclicker": return "AutoClicker";
            case "enable_autokill": return "AutoKill";
            case "enable_antikick": return "AntiKick";
            case "enable_autowalk": return "AutoWalk";
            case "enable_norender": return "NoRender";
            case "enable_fakeblack": return "FakeBlackScreen";
            default: return "Module";
        }
    }
    
    private net.minecraft.client.multiplayer.PlayerControllerMP playerController() {
        return mc.playerController;
    }
    
    private void checkTriggers() {
        for (AutomationTask task : activeTasks.values()) {
            if (!task.isEnabled()) continue;
            
            // 检查任务的触发条件
            if (shouldTriggerTask(task)) {
                // 触发任务
                executeTaskInternal(task);
            }
        }
    }
    
    private boolean shouldTriggerTask(AutomationTask task) {
        // 简化的触发逻辑
        // 在实际实现中，这里应该检查具体的触发条件
        // 目前只对"手动"触发类型返回true，其他类型的触发逻辑需要根据具体需求实现
        String trigger = task.getTrigger();
        if (trigger != null) {
            // 如果触发条件是"手动"，则不自动触发，只通过API调用触发
            if ("manual".equals(trigger) || "test".equals(trigger)) {
                return false;
            }
        }
        // 对于其他触发类型，可以实现特定的检查逻辑
        // 例如：检查计分板变化、生命值变化、特定实体出现等
        return true; // 临时返回true，实际中需要实现具体的触发逻辑
    }
    
    public void shutdown() {
        scheduler.shutdown();
        try {
            if (!scheduler.awaitTermination(5, TimeUnit.SECONDS)) {
                scheduler.shutdownNow();
            }
        } catch (InterruptedException e) {
            scheduler.shutdownNow();
            Thread.currentThread().interrupt();
        }
    }
}