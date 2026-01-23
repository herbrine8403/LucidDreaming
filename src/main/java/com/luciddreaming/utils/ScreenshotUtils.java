package com.luciddreaming.utils;

import com.luciddreaming.LucidDreaming;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.shader.Framebuffer;
import net.minecraft.util.ScreenShotHelper;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.nio.ByteBuffer;
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
        if (mc == null || mc.world == null || mc.displayWidth <= 0 || mc.displayHeight <= 0) {
            LucidDreaming.LOGGER.warn("Cannot take screenshot: Invalid Minecraft state or dimensions");
            return null;
        }

        try {
            // Get framebuffer
            Framebuffer framebuffer = mc.getFramebuffer();
            if (framebuffer == null) {
                LucidDreaming.LOGGER.warn("Cannot take screenshot: Framebuffer is null");
                return null;
            }

            // Create screenshot using ScreenShotHelper with correct parameters
            BufferedImage image = ScreenShotHelper.createScreenshot(mc.displayWidth, mc.displayHeight, framebuffer);
            
            if (image == null) {
                // Fallback: Try alternative method
                image = createScreenshotAlternative();
                if (image == null) {
                    LucidDreaming.LOGGER.warn("Screenshot returned null image from both methods");
                    return null;
                }
            }

            // Convert to byte array
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            String format = quality >= 1.0f ? "png" : "jpg";

            if (format.equals("jpg")) {
                // For JPEG, use ImageIO with quality
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
                // For PNG, use ImageIO directly
                ImageIO.write(image, "png", baos);
            }

            LucidDreaming.LOGGER.info("Screenshot taken successfully ({} bytes)", baos.size());
            return new ScreenshotResult(baos.toByteArray(), image);
        } catch (Exception e) {
            LucidDreaming.LOGGER.error("Failed to take screenshot", e);
            return null;
        }
    }
    
    /**
     * Alternative method to create screenshot if ScreenShotHelper fails
     */
    private static BufferedImage createScreenshotAlternative() {
        try {
            Framebuffer framebuffer = mc.getFramebuffer();
            int width = framebuffer.framebufferTextureWidth;
            int height = framebuffer.framebufferTextureHeight;
            
            // Get pixel data from framebuffer using ScreenShotHelper
            return ScreenShotHelper.createScreenshot(width, height, framebuffer);
        } catch (Exception e) {
            LucidDreaming.LOGGER.error("Failed to create screenshot using alternative method", e);
            return null;
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