package com.luciddreaming.http;

import com.luciddreaming.LucidDreaming;
import com.luciddreaming.config.ModConfig;
import com.luciddreaming.info.GameInfoCollector;
import com.luciddreaming.utils.ScreenshotUtils;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.nio.charset.StandardCharsets;

public class HTTPServer {
    private HttpServer server;
    private int port;
    private String bindAddress;

    public HTTPServer(int port, String bindAddress) {
        this.port = port;
        this.bindAddress = bindAddress;
    }

    public void start() {
        try {
            InetAddress address;
            if (bindAddress.equals("0.0.0.0") || bindAddress.isEmpty()) {
                address = new InetSocketAddress(port).getAddress();
            } else {
                address = InetAddress.getByName(bindAddress);
            }

            server = HttpServer.create(new InetSocketAddress(address, port), 0);

            // Create context handlers
            LucidDreaming.LOGGER.info("Creating RootHandler for '/'");
            server.createContext("/", new RootHandler());
            LucidDreaming.LOGGER.info("Creating APIInfoHandler for '/api/info'");
            server.createContext("/api/info", new APIInfoHandler());
            server.createContext("/api/json", new APIJSONHandler());
            server.createContext("/api/screenshot", new APIScreenshotHandler());
            server.createContext("/api/modules", new ModuleAPIHandler());
            server.createContext("/api/config", new ModuleConfigAPIHandler());
            server.createContext("/api/autowalk", new APIAutoWalkHandler());
            LucidDreaming.LOGGER.info("Creating AutomationAPIHandler for '/api/automation'");
            server.createContext("/api/automation", new AutomationAPIHandler());
            LucidDreaming.LOGGER.info("Creating ConfigHandler for '/config'");
            server.createContext("/config", new ConfigHandler());
            server.createContext("/automation", new AutomationHandler());
            server.createContext("/automation/editor", new AutomationEditorHandler());

            server.setExecutor(null); // creates a default executor
            server.start();

            LucidDreaming.LOGGER.info("HTTP Server is listening on {}:{}", bindAddress.equals("0.0.0.0") ? "0.0.0.0 (all interfaces)" : bindAddress, port);

        } catch (IOException e) {
            LucidDreaming.LOGGER.error("Failed to start HTTP server on port {}: {}", port, e.getMessage());
            LucidDreaming.LOGGER.error("The port might be in use. Please check your configuration.");
        }
    }

    public void stop() {
        if (server != null) {
            server.stop(0);
            LucidDreaming.LOGGER.info("HTTP Server stopped");
        }
    }

    static class RootHandler implements HttpHandler {
        @Override
        public void handle(HttpExchange exchange) throws IOException {
            LucidDreaming.LOGGER.info("RootHandler: Received request for {}", exchange.getRequestURI().getPath());
            String response = WebTemplate.generateHTML();
            exchange.sendResponseHeaders(200, response.getBytes(StandardCharsets.UTF_8).length);
            try (OutputStream os = exchange.getResponseBody()) {
                os.write(response.getBytes(StandardCharsets.UTF_8));
            }
        }
    }

    static class APIInfoHandler implements HttpHandler {
        @Override
        public void handle(HttpExchange exchange) throws IOException {
            GameInfoCollector.GameInfo info = GameInfoCollector.collect();
            String response = info.toPlainText();
            exchange.sendResponseHeaders(200, response.getBytes(StandardCharsets.UTF_8).length);
            try (OutputStream os = exchange.getResponseBody()) {
                os.write(response.getBytes(StandardCharsets.UTF_8));
            }
        }
    }

    static class APIJSONHandler implements HttpHandler {
        @Override
        public void handle(HttpExchange exchange) throws IOException {
            GameInfoCollector.GameInfo info = GameInfoCollector.collect();
            exchange.getResponseHeaders().set("Content-Type", "application/json");
            String response = info.toJSON();
            exchange.sendResponseHeaders(200, response.getBytes(StandardCharsets.UTF_8).length);
            try (OutputStream os = exchange.getResponseBody()) {
                os.write(response.getBytes(StandardCharsets.UTF_8));
            }
        }
    }

    static class APIScreenshotHandler implements HttpHandler {
        @Override
        public void handle(HttpExchange exchange) throws IOException {
            if (!ModConfig.webInterface.enableScreenshot) {
                String response = "{\"error\":\"Screenshot is disabled in configuration\"}";
                exchange.getResponseHeaders().set("Content-Type", "application/json");
                exchange.sendResponseHeaders(403, response.getBytes(StandardCharsets.UTF_8).length);
                try (OutputStream os = exchange.getResponseBody()) {
                    os.write(response.getBytes(StandardCharsets.UTF_8));
                }
                return;
            }

            try {
                float quality = (float) ModConfig.webInterface.screenshotQuality;
                ScreenshotUtils.ScreenshotResult result = ScreenshotUtils.takeScreenshotWithImage(quality);

                if (result == null || result.imageData == null) {
                    String response = "{\"error\":\"Failed to take screenshot\"}";
                    exchange.getResponseHeaders().set("Content-Type", "application/json");
                    exchange.sendResponseHeaders(500, response.getBytes(StandardCharsets.UTF_8).length);
                    try (OutputStream os = exchange.getResponseBody()) {
                        os.write(response.getBytes(StandardCharsets.UTF_8));
                    }
                    return;
                }

                // Auto-save to file if enabled
                if (ModConfig.webInterface.autoSaveScreenshots && result.image != null) {
                    String savedPath = ScreenshotUtils.saveScreenshotToFile(
                        result.image, 
                        quality, 
                        ModConfig.webInterface.screenshotSavePath
                    );
                    if (savedPath != null) {
                        LucidDreaming.LOGGER.info("Screenshot auto-saved to: {}", savedPath);
                    }
                }

                String format = quality >= 1.0f ? "image/png" : "image/jpeg";
                exchange.getResponseHeaders().set("Content-Type", format);
                exchange.sendResponseHeaders(200, result.imageData.length);
                try (OutputStream os = exchange.getResponseBody()) {
                    os.write(result.imageData);
                }
            } catch (Exception e) {
                LucidDreaming.LOGGER.error("Error taking screenshot", e);
                String response = "{\"error\":\"" + e.getMessage() + "\"}";
                exchange.getResponseHeaders().set("Content-Type", "application/json");
                exchange.sendResponseHeaders(500, response.getBytes(StandardCharsets.UTF_8).length);
                try (OutputStream os = exchange.getResponseBody()) {
                    os.write(response.getBytes(StandardCharsets.UTF_8));
                }
            }
        }
    }

    static class APIAutoWalkHandler implements HttpHandler {
        @Override
        public void handle(HttpExchange exchange) throws IOException {
            String method = exchange.getRequestMethod();
            
            if (!"GET".equals(method) && !"POST".equals(method)) {
                String response = "{\"error\":\"Method not allowed\"}";
                exchange.getResponseHeaders().set("Content-Type", "application/json");
                exchange.sendResponseHeaders(405, response.getBytes(StandardCharsets.UTF_8).length);
                try (OutputStream os = exchange.getResponseBody()) {
                    os.write(response.getBytes(StandardCharsets.UTF_8));
                }
                return;
            }

            if ("GET".equals(method)) {
                // Get current AutoWalk status
                com.luciddreaming.modules.AutoWalk autoWalk = (com.luciddreaming.modules.AutoWalk) 
                    LucidDreaming.moduleManager.getModule("AutoWalk");
                
                if (autoWalk == null) {
                    String response = "{\"error\":\"AutoWalk module not found\"}";
                    exchange.getResponseHeaders().set("Content-Type", "application/json");
                    exchange.sendResponseHeaders(404, response.getBytes(StandardCharsets.UTF_8).length);
                    try (OutputStream os = exchange.getResponseBody()) {
                        os.write(response.getBytes(StandardCharsets.UTF_8));
                    }
                    return;
                }

                StringBuilder response = new StringBuilder();
                                    response.append("{");
                                    response.append("\"enabled\":").append(autoWalk.isEnabled()).append(",");
                                    response.append("\"hasTarget\":").append(autoWalk.hasTarget()).append(",");
                                    response.append("\"pathfinding\":").append(autoWalk.isPathfinding());
                                    
                                    if (autoWalk.hasTarget()) {
                                        net.minecraft.util.math.BlockPos target = autoWalk.getTarget();
                                        if (target != null) {
                                            response.append(",\"target\":{");
                                            response.append("\"x\":").append(target.getX()).append(",");
                                            response.append("\"y\":").append(target.getY()).append(",");
                                            response.append("\"z\":").append(target.getZ());
                                            response.append("}");
                                        }
                                        
                                        com.luciddreaming.pathfinding.Path path = autoWalk.getCurrentPath();
                                        if (path != null) {
                                            response.append(",\"hasPath\":true");
                                            response.append(",\"pathLength\":").append(path.getLength());
                                        } else {
                                            response.append(",\"hasPath\":false");
                                        }
                                    }
                                    
                                    response.append("}");                
                exchange.getResponseHeaders().set("Content-Type", "application/json");
                exchange.sendResponseHeaders(200, response.length());
                try (OutputStream os = exchange.getResponseBody()) {
                    os.write(response.toString().getBytes(StandardCharsets.UTF_8));
                }
            } else if ("POST".equals(method)) {
                // Set AutoWalk target
                com.luciddreaming.modules.AutoWalk autoWalk = (com.luciddreaming.modules.AutoWalk) 
                    LucidDreaming.moduleManager.getModule("AutoWalk");
                
                if (autoWalk == null) {
                    String response = "{\"error\":\"AutoWalk module not found\"}";
                    exchange.getResponseHeaders().set("Content-Type", "application/json");
                    exchange.sendResponseHeaders(404, response.getBytes(StandardCharsets.UTF_8).length);
                    try (OutputStream os = exchange.getResponseBody()) {
                        os.write(response.getBytes(StandardCharsets.UTF_8));
                    }
                    return;
                }

                // Parse query parameters
                String query = exchange.getRequestURI().getQuery();
                if (query == null) {
                    String response = "{\"error\":\"Missing parameters\"}";
                    exchange.getResponseHeaders().set("Content-Type", "application/json");
                    exchange.sendResponseHeaders(400, response.getBytes(StandardCharsets.UTF_8).length);
                    try (OutputStream os = exchange.getResponseBody()) {
                        os.write(response.getBytes(StandardCharsets.UTF_8));
                    }
                    return;
                }

                String[] params = query.split("&");
                int x = 0, y = 0, z = 0;
                boolean hasX = false, hasY = false, hasZ = false;
                String action = null;

                for (String param : params) {
                    String[] keyValue = param.split("=");
                    if (keyValue.length == 2) {
                        String key = keyValue[0];
                        String value = keyValue[1];
                        
                        if ("x".equals(key)) {
                            x = Integer.parseInt(value);
                            hasX = true;
                        } else if ("y".equals(key)) {
                            y = Integer.parseInt(value);
                            hasY = true;
                        } else if ("z".equals(key)) {
                            z = Integer.parseInt(value);
                            hasZ = true;
                        } else if ("action".equals(key)) {
                            action = value;
                        }
                    }
                }

                if ("clear".equals(action)) {
                    autoWalk.clearTarget();
                    String response = "{\"success\":true,\"message\":\"Target cleared\"}";
                    exchange.getResponseHeaders().set("Content-Type", "application/json");
                    exchange.sendResponseHeaders(200, response.getBytes(StandardCharsets.UTF_8).length);
                    try (OutputStream os = exchange.getResponseBody()) {
                        os.write(response.getBytes(StandardCharsets.UTF_8));
                    }
                    return;
                }

                if (hasX && hasY && hasZ) {
                    autoWalk.setTarget(x, y, z);
                    String response = "{\"success\":true,\"message\":\"Target set to " + x + ", " + y + ", " + z + "\"}";
                    exchange.getResponseHeaders().set("Content-Type", "application/json");
                    exchange.sendResponseHeaders(200, response.getBytes(StandardCharsets.UTF_8).length);
                    try (OutputStream os = exchange.getResponseBody()) {
                        os.write(response.getBytes(StandardCharsets.UTF_8));
                    }
                } else {
                    String response = "{\"error\":\"Missing x, y, or z parameter\"}";
                    exchange.getResponseHeaders().set("Content-Type", "application/json");
                    exchange.sendResponseHeaders(400, response.getBytes(StandardCharsets.UTF_8).length);
                    try (OutputStream os = exchange.getResponseBody()) {
                        os.write(response.getBytes(StandardCharsets.UTF_8));
                    }
                }
            }
        }
    }

    static class ConfigHandler implements HttpHandler {
        @Override
        public void handle(HttpExchange exchange) throws IOException {
            String response = ConfigTemplate.generateHTML();
            exchange.sendResponseHeaders(200, response.getBytes(StandardCharsets.UTF_8).length);
            try (OutputStream os = exchange.getResponseBody()) {
                os.write(response.getBytes(StandardCharsets.UTF_8));
            }
        }
    }

    static class AutomationHandler implements HttpHandler {
        @Override
        public void handle(HttpExchange exchange) throws IOException {
            String response = AutomationTemplate.generateHTML();
            exchange.sendResponseHeaders(200, response.getBytes(StandardCharsets.UTF_8).length);
            try (OutputStream os = exchange.getResponseBody()) {
                os.write(response.getBytes(StandardCharsets.UTF_8));
            }
        }
    }

    static class AutomationEditorHandler implements HttpHandler {
        @Override
        public void handle(HttpExchange exchange) throws IOException {
            String response = AutomationEditorTemplate.generateHTML();
            exchange.sendResponseHeaders(200, response.getBytes(StandardCharsets.UTF_8).length);
            try (OutputStream os = exchange.getResponseBody()) {
                os.write(response.getBytes(StandardCharsets.UTF_8));
            }
        }
    }
}