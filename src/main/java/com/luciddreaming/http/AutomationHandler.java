package com.luciddreaming.http;

import com.luciddreaming.LucidDreaming;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;

public class AutomationHandler implements HttpHandler {
    @Override
    public void handle(HttpExchange exchange) throws IOException {
        String path = exchange.getRequestURI().getPath();
        String response;

        if (path.equals("/automation/editor")) {
            // 返回自动化编辑器界面
            response = AutomationEditorTemplate.generateHTML();
        } else {
            // 返回自动化管理界面
            response = AutomationTemplate.generateHTML();
        }

        exchange.sendResponseHeaders(200, response.getBytes(StandardCharsets.UTF_8).length);
        try (OutputStream os = exchange.getResponseBody()) {
            os.write(response.getBytes(StandardCharsets.UTF_8));
        }
    }
}