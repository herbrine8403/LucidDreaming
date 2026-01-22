package com.luciddreaming.utils;

import com.luciddreaming.LucidDreaming;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.shader.Framebuffer;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;

public class ScreenshotUtils {
    private static Minecraft mc = Minecraft.getMinecraft();

    public static byte[] takeScreenshot(float quality) throws IOException {
        if (mc == null || mc.world == null) {
            LucidDreaming.LOGGER.warn("Cannot take screenshot: Minecraft not initialized");
            return null;
        }

        try {
            // Get framebuffer
            Framebuffer framebuffer = mc.getFramebuffer();
            if (framebuffer == null) {
                LucidDreaming.LOGGER.warn("capture screenshot: framebuffer is null");
                return null;
            }

            int width = mc.displayWidth;
            int height = mc.displayHeight;

            // Create screenshot
            BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
            ByteBuffer pixelBuffer = ByteBuffer.allocateDirect(width * height * 4);
            
            // Bind framebuffer
            framebuffer.bindFramebufferTexture();
            OpenGlHelper.glBindFramebuffer(OpenGlHelper.GL_FRAMEBUFFER, framebuffer.framebufferObject);
            
            // Read pixels using GL11
            org.lwjgl.opengl.GL11.glReadPixels(0, 0, width, height, org.lwjgl.opengl.GL11.GL_RGBA, org.lwjgl.opengl.GL11.GL_UNSIGNED_BYTE, pixelBuffer);
            pixelBuffer.rewind();
            
            // Convert to image
            int[] pixelData = new int[width * height];
            for (int i = 0; i < pixelData.length; i++) {
                int r = pixelBuffer.get() & 0xFF;
                int g = pixelBuffer.get() & 0xFF;
                int b = pixelBuffer.get() & 0xFF;
                pixelBuffer.get(); // skip alpha
                pixelData[i] = (r << 16) | (g << 8) | b;
            }
            
            // Flip image vertically (OpenGL renders upside down)
            for (int y = 0; y < height / 2; y++) {
                for (int x = 0; x < width; x++) {
                    int temp = pixelData[y * width + x];
                    pixelData[y * width + x] = pixelData[(height - 1 - y) * width + x];
                    pixelData[(height - 1 - y) * width + x] = temp;
                }
            }
            
            image.setRGB(0, 0, width, height, pixelData, 0, width);
            
            // Unbind framebuffer
            mc.getFramebuffer().unbindFramebufferTexture();

            // Convert to byte array
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            String format = quality >= 1.0f ? "png" : "jpg";
            float actualQuality = format.equals("jpg") ? quality : 1.0f;

            ImageIO.write(image, format, baos);

            LucidDreaming.LOGGER.info("Screenshot taken successfully ({} bytes)", baos.size());
            return baos.toByteArray();
        } catch (Exception e) {
            LucidDreaming.LOGGER.error("Failed to take screenshot", e);
            e.printStackTrace();
            return null;
        }
    }

    public static byte[] takeScreenshot() throws IOException {
        return takeScreenshot(0.8f);
    }
}