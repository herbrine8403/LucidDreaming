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
            server.createContext("/", new RootHandler());
            server.createContext("/api/info", new APIInfoHandler());
            server.createContext("/api/json", new APIJSONHandler());
            server.createContext("/api/screenshot", new APIScreenshotHandler());

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
                byte[] screenshot = ScreenshotUtils.takeScreenshot((float) ModConfig.webInterface.screenshotQuality);

                if (screenshot == null) {
                    String response = "{\"error\":\"Failed to take screenshot\"}";
                    exchange.getResponseHeaders().set("Content-Type", "application/json");
                    exchange.sendResponseHeaders(500, response.getBytes(StandardCharsets.UTF_8).length);
                    try (OutputStream os = exchange.getResponseBody()) {
                        os.write(response.getBytes(StandardCharsets.UTF_8));
                    }
                    return;
                }

                String format = ModConfig.webInterface.screenshotQuality >= 1.0f ? "image/png" : "image/jpeg";
                exchange.getResponseHeaders().set("Content-Type", format);
                exchange.sendResponseHeaders(200, screenshot.length);
                try (OutputStream os = exchange.getResponseBody()) {
                    os.write(screenshot);
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
}