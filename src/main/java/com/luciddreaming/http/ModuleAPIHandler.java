package com.luciddreaming.http;

import com.luciddreaming.LucidDreaming;
import com.luciddreaming.config.ModuleConfigs;
import com.luciddreaming.modules.Module;
import com.luciddreaming.modules.ModuleCategory;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ModuleAPIHandler implements HttpHandler {
    @Override
    public void handle(HttpExchange exchange) throws IOException {
        String method = exchange.getRequestMethod();
        String path = exchange.getRequestURI().getPath();
        
        if ("GET".equals(method) && "/api/modules".equals(path)) {
            handleGetModules(exchange);
        } else if ("POST".equals(method) && path.startsWith("/api/modules/")) {
            handleToggleModule(exchange, path);
        } else {
            sendResponse(exchange, 404, "{\"error\":\"Not found\"}", "application/json");
        }
    }

    private void handleGetModules(HttpExchange exchange) throws IOException {
        if (LucidDreaming.moduleManager == null) {
            sendResponse(exchange, 500, "{\"error\":\"Module manager not initialized\"}", "application/json");
            return;
        }

        StringBuilder response = new StringBuilder();
        response.append("{");
        response.append("\"modules\":[");

        List<Module> modules = LucidDreaming.moduleManager.getModules();
        for (int i = 0; i < modules.size(); i++) {
            Module module = modules.get(i);
            response.append("{");
            response.append("\"name\":\"").append(escapeJson(module.getName())).append("\",");
            response.append("\"localizedName\":\"").append(escapeJson(module.getLocalizedName())).append("\",");
            response.append("\"description\":\"").append(escapeJson(module.getDescription())).append("\",");
            response.append("\"localizedDescription\":\"").append(escapeJson(module.getLocalizedDescription())).append("\",");
            response.append("\"category\":\"").append(escapeJson(module.getCategory().getName())).append("\",");
            response.append("\"localizedCategory\":\"").append(escapeJson(module.getCategory().getLocalizedName())).append("\",");
            response.append("\"enabled\":").append(module.isEnabled()).append(",");
            response.append("\"keybind\":\"").append(escapeJson(module.getKeybind().getKeyName())).append("\"");
            response.append("}");
            
            if (i < modules.size() - 1) {
                response.append(",");
            }
        }

        response.append("],");
        response.append("\"categories\":[");
        
        ModuleCategory[] categories = ModuleCategory.values();
        for (int i = 0; i < categories.length; i++) {
            response.append("\"").append(escapeJson(categories[i].getName())).append("\"");
            if (i < categories.length - 1) {
                response.append(",");
            }
        }

        response.append("]");
        response.append("}");

        sendResponse(exchange, 200, response.toString(), "application/json");
    }

    private void handleToggleModule(HttpExchange exchange, String path) throws IOException {
        String moduleName = path.substring("/api/modules/".length());
        
        if (LucidDreaming.moduleManager == null) {
            sendResponse(exchange, 500, "{\"error\":\"Module manager not initialized\"}", "application/json");
            return;
        }

        Module module = LucidDreaming.moduleManager.getModule(moduleName);
        if (module == null) {
            sendResponse(exchange, 404, "{\"error\":\"Module not found\"}", "application/json");
            return;
        }

        // Read request body to get action (enable/disable/toggle)
        String body = readRequestBody(exchange);
        String action = "toggle";
        
        if (body != null && !body.isEmpty()) {
            try {
                Map<String, Object> data = parseJson(body);
                if (data.containsKey("action")) {
                    action = (String) data.get("action");
                }
            } catch (Exception e) {
                // Ignore parse errors, use default
            }
        }

        boolean newState = module.isEnabled();
        
        switch (action.toLowerCase()) {
            case "enable":
                module.setEnabled(true);
                newState = true;
                break;
            case "disable":
                module.setEnabled(false);
                newState = false;
                break;
            case "toggle":
            default:
                module.toggle();
                newState = module.isEnabled();
                break;
        }

        String response = String.format(
            "{\"name\":\"%s\",\"localizedName\":\"%s\",\"enabled\":%s,\"description\":\"%s\",\"localizedDescription\":\"%s\",\"category\":\"%s\",\"localizedCategory\":\"%s\"}",
            escapeJson(module.getName()),
            escapeJson(module.getLocalizedName()),
            newState,
            escapeJson(module.getDescription()),
            escapeJson(module.getLocalizedDescription()),
            escapeJson(module.getCategory().getName()),
            escapeJson(module.getCategory().getLocalizedName())
        );

        sendResponse(exchange, 200, response, "application/json");
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

    private Map<String, Object> parseJson(String json) {
        // Simple JSON parser for module control
        Map<String, Object> result = new HashMap<>();
        
        // Remove whitespace
        json = json.trim();
        if (!json.startsWith("{") || !json.endsWith("}")) {
            return result;
        }
        
        json = json.substring(1, json.length() - 1);
        
        String[] pairs = json.split(",");
        for (String pair : pairs) {
            String[] keyValue = pair.split(":", 2);
            if (keyValue.length == 2) {
                String key = keyValue[0].trim().replaceAll("\"", "");
                String value = keyValue[1].trim().replaceAll("\"", "").replaceAll("'", "");
                
                if (value.equals("true") || value.equals("false")) {
                    result.put(key, Boolean.parseBoolean(value));
                } else if (value.matches("-?\\d+")) {
                    result.put(key, Integer.parseInt(value));
                } else if (value.matches("-?\\d+\\.\\d+")) {
                    result.put(key, Double.parseDouble(value));
                } else {
                    result.put(key, value);
                }
            }
        }
        
        return result;
    }

    private String escapeJson(String text) {
        if (text == null) {
            return "";
        }
        return text.replace("\\", "\\\\")
                   .replace("\"", "\\\"")
                   .replace("\n", "\\n")
                   .replace("\r", "\\r")
                   .replace("\t", "\\t");
    }

    private void sendResponse(HttpExchange exchange, int statusCode, String response, String contentType) throws IOException {
        exchange.getResponseHeaders().set("Content-Type", contentType);
        exchange.sendResponseHeaders(statusCode, response.getBytes(StandardCharsets.UTF_8).length);
        try (OutputStream os = exchange.getResponseBody()) {
            os.write(response.getBytes(StandardCharsets.UTF_8));
        }
    }
}