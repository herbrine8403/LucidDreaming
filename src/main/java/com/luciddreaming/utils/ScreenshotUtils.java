package com.luciddreaming.utils;

import com.luciddreaming.LucidDreaming;
import net.minecraft.client.Minecraft;
import net.minecraft.client.shader.Framebuffer;
import net.minecraft.util.ScreenShotHelper;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class ScreenshotUtils {
    private static Minecraft mc = Minecraft.getMinecraft();

    public static byte[] takeScreenshot(float quality) throws IOException {
        if (mc == null || mc.world == null) {
            LucidDreaming.LOGGER.warn("Cannot take screenshot: Minecraft not initialized");
            return null;
        }

        try {
            BufferedImage image = ScreenShotHelper.createScreenshot(mc.displayWidth, mc.displayHeight, mc.getFramebuffer());

            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            String format = quality >= 1.0f ? "png" : "jpg";
            float actualQuality = format.equals("jpg") ? quality : 1.0f;

            ImageIO.write(image, format, baos);

            LucidDreaming.LOGGER.info("Screenshot taken successfully ({} bytes)", baos.size());
            return baos.toByteArray();
        } catch (Exception e) {
            LucidDreaming.LOGGER.error("Failed to take screenshot", e);
            return null;
        }
    }

    public static byte[] takeScreenshot() throws IOException {
        return takeScreenshot(0.8f);
    }
}