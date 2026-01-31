package com.luciddreaming.info;

import com.luciddreaming.LucidDreaming;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiPlayerTabOverlay;
import net.minecraft.client.network.NetworkPlayerInfo;
import net.minecraft.scoreboard.Score;
import net.minecraft.scoreboard.ScoreObjective;
import net.minecraft.scoreboard.Scoreboard;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.text.SimpleDateFormat;
import java.util.*;

public class GameInfoCollector {
    private static final long START_TIME = System.currentTimeMillis();
    
    @SideOnly(Side.CLIENT)
    public static GameInfo collect() {
        GameInfo info = new GameInfo();
        Minecraft mc = Minecraft.getMinecraft();
        
        // Player name
        if (mc.player != null) {
            info.playerName = mc.player.getName();
            info.playerUUID = mc.player.getUniqueID().toString();
            info.playerHealth = Math.round(mc.player.getHealth() * 10) / 10.0;
            info.playerMaxHealth = Math.round(mc.player.getMaxHealth() * 10) / 10.0;
            info.hunger = mc.player.getFoodStats().getFoodLevel();
            info.saturation = Math.round(mc.player.getFoodStats().getSaturationLevel() * 100) / 100.0;
            info.experienceLevel = mc.player.experienceLevel;
            info.experienceProgress = Math.round(mc.player.experience * 100) / 100.0;
            
            // Position
            info.position = String.format("X: %.2f, Y: %.2f, Z: %.2f", 
                mc.player.posX, mc.player.posY, mc.player.posZ);
            
            // Dimension
            int dimension = mc.player.dimension;
            info.dimension = getDimensionName(dimension);
            
            // Game mode
            info.gameMode = mc.playerController.getCurrentGameType().getName();
        }
        
        // Game version
        info.minecraftVersion = net.minecraftforge.common.ForgeVersion.mcVersion;
        info.forgeVersion = net.minecraftforge.common.ForgeVersion.getVersion();
        info.modVersion = LucidDreaming.VERSION;
        
        // Server information
        if (mc.world != null && mc.isSingleplayer()) {
            info.serverAddress = "Singleplayer";
            info.serverType = "Local";
        } else if (mc.getConnection() != null && mc.getConnection().getNetworkManager() != null) {
            info.serverAddress = mc.getCurrentServerData() != null ? 
                mc.getCurrentServerData().serverIP : "Unknown";
            info.serverName = mc.getCurrentServerData() != null ? 
                mc.getCurrentServerData().serverName : "Unknown";
            info.serverType = "Multiplayer";
        }
        
        // Uptime
        info.uptime = formatUptime(System.currentTimeMillis() - START_TIME);
        info.uptimeSeconds = (System.currentTimeMillis() - START_TIME) / 1000;
        
        // Current time
        info.currentTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
        
        // Scoreboard
        info.scoreboard = collectScoreboard(mc);
        
        // FPS
        info.fps = Minecraft.getDebugFPS();
        
        // System info (for Android compatibility)
        info.osName = System.getProperty("os.name");
        info.osVersion = System.getProperty("os.version");
        info.javaVersion = System.getProperty("java.version");
        
        return info;
    }
    
    @SideOnly(Side.CLIENT)
    private static List<String> collectScoreboard(Minecraft mc) {
        List<String> scoreboardLines = new ArrayList<>();
        
        if (mc.world != null) {
            Scoreboard scoreboard = mc.world.getScoreboard();
            ScoreObjective objective = scoreboard.getObjectiveInDisplaySlot(1); // 1 = sidebar
            
            if (objective != null) {
                scoreboardLines.add(TextFormatting.BOLD + objective.getDisplayName());
                
                Collection<Score> scores = scoreboard.getSortedScores(objective);
                List<Score> list = new ArrayList<>(scores);
                
                // Reverse to show in correct order
                Collections.reverse(list);
                
                for (Score score : list) {
                    String line = scoreboard.getPlayersTeam(score.getPlayerName()) != null ? 
                        scoreboard.getPlayersTeam(score.getPlayerName()).getPrefix() + 
                        scoreboard.getPlayersTeam(score.getPlayerName()).getSuffix() + 
                        score.getPlayerName() : score.getPlayerName();
                    
                    String displayLine = line + ": " + score.getScorePoints();
                    if (displayLine.length() <= 40) { // Minecraft scoreboard line limit
                        scoreboardLines.add(displayLine);
                    }
                }
            }
        }
        
        if (scoreboardLines.isEmpty()) {
            scoreboardLines.add("No scoreboard active");
        }
        
        return scoreboardLines;
    }
    
    private static String formatUptime(long millis) {
        long seconds = millis / 1000;
        long minutes = seconds / 60;
        long hours = minutes / 60;
        long days = hours / 24;
        
        seconds %= 60;
        minutes %= 60;
        hours %= 24;
        
        if (days > 0) {
            return String.format("%dd %dh %dm %ds", days, hours, minutes, seconds);
        } else if (hours > 0) {
            return String.format("%dh %dm %ds", hours, minutes, seconds);
        } else if (minutes > 0) {
            return String.format("%dm %ds", minutes, seconds);
        } else {
            return String.format("%ds", seconds);
        }
    }
    
    private static String getDimensionName(int dimension) {
        switch (dimension) {
            case -1: return "Nether";
            case 0: return "Overworld";
            case 1: return "End";
            default: return "Dimension " + dimension;
        }
    }
    
    public static class GameInfo {
        public String playerName = "Not in game";
        public String playerUUID = "N/A";
        public double playerHealth = 0;
        public double playerMaxHealth = 20;
        public int hunger = 0;
        public double saturation = 0;
        public int experienceLevel = 0;
        public double experienceProgress = 0;
        public String position = "N/A";
        public String dimension = "N/A";
        public String gameMode = "N/A";
        public String minecraftVersion = "Unknown";
        public String forgeVersion = "Unknown";
        public String modVersion = LucidDreaming.VERSION;
        public String serverAddress = "Unknown";
        public String serverName = "Unknown";
        public String serverType = "Unknown";
        public String uptime = "0s";
        public long uptimeSeconds = 0;
        public String currentTime = "Unknown";
        public List<String> scoreboard = new ArrayList<>();
        public int fps = 0;
        public String osName = "Unknown";
        public String osVersion = "Unknown";
        public String javaVersion = "Unknown";
        
        public String toPlainText() {
            StringBuilder sb = new StringBuilder();
            sb.append("=== Lucid Dreaming - Game Information ===\n\n");
            sb.append("Player Information:\n");
            sb.append("  Name: ").append(playerName).append("\n");
            sb.append("  UUID: ").append(playerUUID).append("\n");
            sb.append("  Health: ").append(playerHealth).append("/").append(playerMaxHealth).append("\n");
            sb.append("  Hunger: ").append(hunger).append("/20\n");
            sb.append("  Saturation: ").append(saturation).append("\n");
            sb.append("  Experience Level: ").append(experienceLevel).append("\n");
            sb.append("  Position: ").append(position).append("\n");
            sb.append("  Dimension: ").append(dimension).append("\n");
            sb.append("  Game Mode: ").append(gameMode).append("\n\n");
            
            sb.append("Game Information:\n");
            sb.append("  Minecraft Version: ").append(minecraftVersion).append("\n");
            sb.append("  Forge Version: ").append(forgeVersion).append("\n");
            sb.append("  Mod Version: ").append(modVersion).append("\n");
            sb.append("  Uptime: ").append(uptime).append("\n");
            sb.append("  FPS: ").append(fps).append("\n\n");
            
            sb.append("Server Information:\n");
            sb.append("  Type: ").append(serverType).append("\n");
            sb.append("  Address: ").append(serverAddress).append("\n");
            if (!serverName.equals("Unknown")) {
                sb.append("  Name: ").append(serverName).append("\n");
            }
            sb.append("\n");
            
            sb.append("Scoreboard:\n");
            for (String line : scoreboard) {
                sb.append("  ").append(line).append("\n");
            }
            sb.append("\n");
            
            sb.append("System Information:\n");
            sb.append("  OS: ").append(osName).append(" ").append(osVersion).append("\n");
            sb.append("  Java: ").append(javaVersion).append("\n");
            sb.append("  Current Time: ").append(currentTime).append("\n");
            
            return sb.toString();
        }
        
        public String toJSON() {
            StringBuilder sb = new StringBuilder();
            sb.append("{\n");
            sb.append("  \"player\": {\n");
            sb.append("    \"name\": \"").append(escapeJSON(playerName)).append("\",\n");
            sb.append("    \"uuid\": \"").append(playerUUID).append("\",\n");
            sb.append("    \"health\": ").append(playerHealth).append(",\n");
            sb.append("    \"maxHealth\": ").append(playerMaxHealth).append(",\n");
            sb.append("    \"hunger\": ").append(hunger).append(",\n");
            sb.append("    \"saturation\": ").append(saturation).append(",\n");
            sb.append("    \"experienceLevel\": ").append(experienceLevel).append(",\n");
            sb.append("    \"experienceProgress\": ").append(experienceProgress).append(",\n");
            sb.append("    \"position\": \"").append(position).append("\",\n");
            sb.append("    \"dimension\": \"").append(dimension).append("\",\n");
            sb.append("    \"gameMode\": \"").append(gameMode).append("\"\n");
            sb.append("  },\n");
            
            sb.append("  \"game\": {\n");
            sb.append("    \"minecraftVersion\": \"").append(escapeJSON(minecraftVersion)).append("\",\n");
            sb.append("    \"forgeVersion\": \"").append(escapeJSON(forgeVersion)).append("\",\n");
            sb.append("    \"modVersion\": \"").append(modVersion).append("\",\n");
            sb.append("    \"uptime\": \"").append(uptime).append("\",\n");
            sb.append("    \"uptimeSeconds\": ").append(uptimeSeconds).append(",\n");
            sb.append("    \"fps\": ").append(fps).append(",\n");
            sb.append("    \"currentTime\": \"").append(currentTime).append("\"\n");
            sb.append("  },\n");
            
            sb.append("  \"server\": {\n");
            sb.append("    \"type\": \"").append(serverType).append("\",\n");
            sb.append("    \"address\": \"").append(escapeJSON(serverAddress)).append("\",\n");
            sb.append("    \"name\": \"").append(escapeJSON(serverName)).append("\"\n");
            sb.append("  },\n");
            
            sb.append("  \"scoreboard\": [\n");
            for (int i = 0; i < scoreboard.size(); i++) {
                sb.append("    \"").append(escapeJSON(scoreboard.get(i))).append("\"");
                if (i < scoreboard.size() - 1) {
                    sb.append(",");
                }
                sb.append("\n");
            }
            sb.append("  ],\n");
            
            sb.append("  \"system\": {\n");
            sb.append("    \"osName\": \"").append(escapeJSON(osName)).append("\",\n");
            sb.append("    \"osVersion\": \"").append(escapeJSON(osVersion)).append("\",\n");
            sb.append("    \"javaVersion\": \"").append(escapeJSON(javaVersion)).append("\"\n");
            sb.append("  }\n");
            sb.append("}");
            
            return sb.toString();
        }
        
        private String escapeJSON(String s) {
            if (s == null) return "";
            return s.replace("\\", "\\\\")
                    .replace("\"", "\\\"")
                    .replace("\n", "\\n")
                    .replace("\r", "\\r")
                    .replace("\t", "\\t");
        }
    }
}
