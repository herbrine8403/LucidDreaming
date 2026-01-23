package com.luciddreaming.http;

import com.luciddreaming.LucidDreaming;
import com.luciddreaming.config.ModuleConfigs;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.lang.reflect.Field;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

public class ModuleConfigAPIHandler implements HttpHandler {
    private static final Gson gson = new GsonBuilder().setPrettyPrinting().create();

    @Override
    public void handle(HttpExchange exchange) throws IOException {
        String method = exchange.getRequestMethod();
        String path = exchange.getRequestURI().getPath();

        if ("GET".equals(method) && path.startsWith("/api/config/")) {
            handleGetConfig(exchange, path);
        } else if ("POST".equals(method) && path.startsWith("/api/config/")) {
            handleUpdateConfig(exchange, path);
        } else {
            sendResponse(exchange, 404, "{\"error\":\"Not found\"}", "application/json");
        }
    }

    private void handleGetConfig(HttpExchange exchange, String path) throws IOException {
        String moduleName = path.substring("/api/config/".length());
        
        if (moduleName.isEmpty()) {
            // Return all module configs
            Map<String, Object> allConfigs = new HashMap<>();
            allConfigs.put("AutoFish", getConfigObject(ModuleConfigs.autoFish));
            allConfigs.put("AutoClicker", getConfigObject(ModuleConfigs.autoClicker));
            allConfigs.put("AutoKill", getConfigObject(ModuleConfigs.autoKill));
            allConfigs.put("AntiKick", getConfigObject(ModuleConfigs.antiKick));
            allConfigs.put("AutoWalk", getConfigObject(ModuleConfigs.autoWalk));
            allConfigs.put("NoRender", getConfigObject(ModuleConfigs.noRender));
            allConfigs.put("FakeBlackScreen", getConfigObject(ModuleConfigs.fakeBlackScreen));
            
            sendResponse(exchange, 200, gson.toJson(allConfigs), "application/json");
            return;
        }

        // Return specific module config
        Object config = getModuleConfig(moduleName);
        if (config == null) {
            sendResponse(exchange, 404, "{\"error\":\"Module not found\"}", "application/json");
            return;
        }

        sendResponse(exchange, 200, gson.toJson(getConfigObject(config)), "application/json");
    }

    private void handleUpdateConfig(HttpExchange exchange, String path) throws IOException {
        String moduleName = path.substring("/api/config/".length());
        
        Object config = getModuleConfig(moduleName);
        if (config == null) {
            sendResponse(exchange, 404, "{\"error\":\"Module not found\"}", "application/json");
            return;
        }

        // Read request body
        String body = readRequestBody(exchange);
        if (body == null || body.isEmpty()) {
            sendResponse(exchange, 400, "{\"error\":\"Empty request body\"}", "application/json");
            return;
        }

        try {
            JsonObject json = gson.fromJson(body, JsonObject.class);
            updateConfig(config, json);
            
            // Sync configuration
            net.minecraftforge.common.config.ConfigManager.sync(LucidDreaming.MODID, net.minecraftforge.common.config.Config.Type.INSTANCE);
            
            LucidDreaming.LOGGER.info("Updated configuration for module: {}", moduleName);
            sendResponse(exchange, 200, "{\"success\":true}", "application/json");
        } catch (Exception e) {
            LucidDreaming.LOGGER.error("Failed to update configuration for module: {}", moduleName, e);
            sendResponse(exchange, 500, "{\"error\":\"Failed to update configuration: " + e.getMessage() + "\"}", "application/json");
        }
    }

    private Object getModuleConfig(String moduleName) {
        switch (moduleName.toLowerCase()) {
            case "autofish":
                return ModuleConfigs.autoFish;
            case "autoclicker":
                return ModuleConfigs.autoClicker;
            case "autokill":
                return ModuleConfigs.autoKill;
            case "antikick":
                return ModuleConfigs.antiKick;
            case "autowalk":
                return ModuleConfigs.autoWalk;
            case "norender":
                return ModuleConfigs.noRender;
            case "fakeblackscreen":
                return ModuleConfigs.fakeBlackScreen;
            default:
                return null;
        }
    }

    private Map<String, Object> getConfigObject(Object config) {
        Map<String, Object> configMap = new HashMap<>();
        try {
            Field[] fields = config.getClass().getDeclaredFields();
            for (Field field : fields) {
                field.setAccessible(true);
                configMap.put(field.getName(), field.get(config));
            }
        } catch (Exception e) {
            LucidDreaming.LOGGER.error("Failed to read configuration", e);
        }
        return configMap;
    }

    private void updateConfig(Object config, JsonObject json) throws Exception {
        for (Map.Entry<String, com.google.gson.JsonElement> entry : json.entrySet()) {
            String fieldName = entry.getKey();
            com.google.gson.JsonElement value = entry.getValue();

            try {
                Field field = config.getClass().getDeclaredField(fieldName);
                field.setAccessible(true);

                if (value.isJsonPrimitive()) {
                    com.google.gson.JsonPrimitive primitive = value.getAsJsonPrimitive();
                    if (primitive.isBoolean()) {
                        field.set(config, primitive.getAsBoolean());
                    } else if (primitive.isNumber()) {
                        Class<?> fieldType = field.getType();
                        if (fieldType == int.class || fieldType == Integer.class) {
                            field.set(config, primitive.getAsInt());
                        } else if (fieldType == double.class || fieldType == Double.class) {
                            field.set(config, primitive.getAsDouble());
                        } else if (fieldType == float.class || fieldType == Float.class) {
                            field.set(config, primitive.getAsFloat());
                        } else if (fieldType == long.class || fieldType == Long.class) {
                            field.set(config, primitive.getAsLong());
                        }
                    } else if (primitive.isString()) {
                        field.set(config, primitive.getAsString());
                    }
                }
            } catch (NoSuchFieldException e) {
                LucidDreaming.LOGGER.warn("Field '{}' not found in config, skipping", fieldName);
            }
        }
    }

    private String readRequestBody(HttpExchange exchange) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(exchange.getRequestBody()));
        StringBuilder body = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            body.append(line);
        }
        return body.toString();
    }

    private void sendResponse(HttpExchange exchange, int statusCode, String response, String contentType) throws IOException {
        exchange.getResponseHeaders().set("Content-Type", contentType);
        exchange.sendResponseHeaders(statusCode, response.getBytes(StandardCharsets.UTF_8).length);
        try (OutputStream os = exchange.getResponseBody()) {
            os.write(response.getBytes(StandardCharsets.UTF_8));
        }
    }
}