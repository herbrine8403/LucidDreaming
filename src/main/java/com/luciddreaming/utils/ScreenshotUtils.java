package com.luciddreaming.utils;

import com.luciddreaming.LucidDreaming;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Framebuffer;
import net.minecraft.util.ScreenShotHelper;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ScreenshotUtils {
    private static Minecraft mc = Minecraft.getMinecraft();

    public static class ScreenshotResult {
        public final byte[] imageData;
        public final BufferedImage image;

        public ScreenshotResult(byte[] imageData, BufferedImage image) {
            this.imageData = imageData;
            this.image = image;
        }
    }

    public static byte[] takeScreenshot(float quality) throws IOException {
        ScreenshotResult result = takeScreenshotWithImage(quality);
        return result != null ? result.imageData : null;
    }

    public static ScreenshotResult takeScreenshotWithImage(float quality) throws IOException {
        if (mc == null || mc.displayWidth <= 0 || mc.displayHeight <= 0) {
            LucidDreaming.LOGGER.warn("Cannot take screenshot: Invalid Minecraft state or dimensions");
            return null;
        }

        try {
            // Use reliable main thread executor to ensure OpenGL context is available
            return DirectMainThreadExecutor.executeOnMainThread(() -> {
                // Save current OpenGL state
                GlStateManager.pushMatrix();
                GlStateManager.pushAttrib();
                
                try {
                    // Get Minecraft's framebuffer
                    Framebuffer framebuffer = mc.getFramebuffer();
                    if (framebuffer == null) {
                        LucidDreaming.LOGGER.warn("Cannot take screenshot: Framebuffer is null");
                        return null;
                    }

                    // Verify we're on the main thread with active OpenGL context
                    if (!DirectMainThreadExecutor.isMainThread()) {
                        LucidDreaming.LOGGER.error("CRITICAL: Screenshot taken on wrong thread: {}", Thread.currentThread().getName());
                        return null;
                    }

                    // Create screenshot using Minecraft's built-in ScreenShotHelper
                    // This method handles all OpenGL context operations correctly
                    BufferedImage image = ScreenShotHelper.createScreenshot(
                        mc.displayWidth, 
                        mc.displayHeight, 
                        framebuffer
                    );
                    
                    if (image == null) {
                        LucidDreaming.LOGGER.warn("ScreenShotHelper returned null image");
                        return null;
                    }

                    // Convert image to byte array
                    ByteArrayOutputStream baos = new ByteArrayOutputStream();
                    String format = quality >= 1.0f ? "png" : "jpg";

                    if (format.equals("jpg")) {
                        // For JPEG, convert to RGB format first
                        java.awt.image.BufferedImage jpgImage = new java.awt.image.BufferedImage(
                            image.getWidth(), image.getHeight(), BufferedImage.TYPE_INT_RGB);
                        jpgImage.getGraphics().drawImage(image, 0, 0, null);
                        
                        javax.imageio.ImageWriter writer = ImageIO.getImageWritersByFormatName("jpg").next();
                        javax.imageio.ImageWriteParam param = writer.getDefaultWriteParam();
                        param.setCompressionMode(javax.imageio.ImageWriteParam.MODE_EXPLICIT);
                        param.setCompressionQuality(quality);
                        
                        try (javax.imageio.stream.ImageOutputStream ios = ImageIO.createImageOutputStream(baos)) {
                            writer.setOutput(ios);
                            writer.write(null, new javax.imageio.IIOImage(jpgImage, null, null), param);
                        }
                    } else {
                        // For PNG, write directly
                        ImageIO.write(image, "png", baos);
                    }

                    LucidDreaming.LOGGER.info("Screenshot taken successfully ({} bytes)", baos.size());
                    return new ScreenshotResult(baos.toByteArray(), image);
                } finally {
                    // Restore OpenGL state
                    GlStateManager.popAttrib();
                    GlStateManager.popMatrix();
                }
            });
        } catch (Exception e) {
            LucidDreaming.LOGGER.error("Failed to take screenshot", e);
            throw new IOException("Failed to take screenshot: " + e.getMessage(), e);
        }
    }

    public static byte[] takeScreenshot() throws IOException {
        return takeScreenshot(0.8f);
    }

    public static String saveScreenshotToFile(BufferedImage image, float quality, String savePath) {
        try {
            // Create save directory if it doesn't exist
            File saveDir = new File(savePath);
            if (!saveDir.exists()) {
                saveDir.mkdirs();
            }

            // Generate filename with timestamp
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd-HHmmss");
            String timestamp = dateFormat.format(new Date());
            String format = quality >= 1.0f ? "png" : "jpg";
            String filename = String.format("screenshot-%s.%s", timestamp, format);
            File outputFile = new File(saveDir, filename);

            // Save image
            if (format.equals("jpg")) {
                // For JPEG, use ImageIO with quality
                java.awt.image.BufferedImage jpgImage = new java.awt.image.BufferedImage(
                    image.getWidth(), image.getHeight(), BufferedImage.TYPE_INT_RGB);
                jpgImage.getGraphics().drawImage(image, 0, 0, null);
                
                javax.imageio.ImageWriter writer = ImageIO.getImageWritersByFormatName("jpg").next();
                javax.imageio.ImageWriteParam param = writer.getDefaultWriteParam();
                param.setCompressionMode(javax.imageio.ImageWriteParam.MODE_EXPLICIT);
                param.setCompressionQuality(quality);
                
                try (javax.imageio.stream.ImageOutputStream ios = ImageIO.createImageOutputStream(outputFile)) {
                    writer.setOutput(ios);
                    writer.write(null, new javax.imageio.IIOImage(jpgImage, null, null), param);
                }
            } else {
                // For PNG, use ImageIO directly
                ImageIO.write(image, "png", outputFile);
            }

            LucidDreaming.LOGGER.info("Screenshot saved to: {}", outputFile.getAbsolutePath());
            return outputFile.getAbsolutePath();
        } catch (Exception e) {
            LucidDreaming.LOGGER.error("Failed to save screenshot to file", e);
            return null;
        }
    }
}
