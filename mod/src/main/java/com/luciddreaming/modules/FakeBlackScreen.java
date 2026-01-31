package com.luciddreaming.modules;

import com.luciddreaming.LucidDreaming;
import com.luciddreaming.config.ModuleConfigs;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import org.lwjgl.opengl.GL11;

public class FakeBlackScreen extends Module {
    private final Minecraft mc = Minecraft.getMinecraft();

    public FakeBlackScreen() {
        super("FakeBlackScreen", "Display a fake black screen", ModuleCategory.RENDER);
    }

    @Override
    protected void onEnable() {
        LucidDreaming.LOGGER.info("FakeBlackScreen enabled");
    }

    @Override
    protected void onDisable() {
        LucidDreaming.LOGGER.info("FakeBlackScreen disabled");
    }

    @Override
    public void onTick() {
        // Tick event is not used for rendering
        // Rendering is done in RenderGameOverlayEvent
    }

    @SubscribeEvent
    public void onRenderGameOverlay(RenderGameOverlayEvent.Post event) {
        if (!isEnabled()) {
            return;
        }

        if (mc.world == null || mc.player == null) {
            return;
        }

        // Don't render if a GUI is open
        if (mc.currentScreen != null) {
            return;
        }

        // Render black screen overlay on top of everything
        renderBlackScreen();
    }

    private void renderBlackScreen() {
        // Get screen dimensions
        ScaledResolution scaledResolution = new ScaledResolution(mc);
        int width = scaledResolution.getScaledWidth();
        int height = scaledResolution.getScaledHeight();

        // Calculate color with opacity (ARGB format)
        float opacity = (float) ModuleConfigs.fakeBlackScreen.opacity;
        int alpha = (int) (opacity * 255);
        int color = (alpha << 24) | (0 << 16) | (0 << 8) | 0; // Black with alpha

        // Draw black rectangle (like Mousse's RenderUtils.drawRect)
        drawRect(0, 0, width, height, color);
    }

    private void drawRect(float x, float y, float x2, float y2, int color) {
        // Enable blending and disable texture
        GL11.glEnable(GL11.GL_BLEND);
        GL11.glDisable(GL11.GL_TEXTURE_2D);
        GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
        GL11.glEnable(GL11.GL_LINE_SMOOTH);

        // Set color
        glColor(color);

        // Draw rectangle
        GL11.glBegin(GL11.GL_QUADS);
        GL11.glVertex2f(x2, y);
        GL11.glVertex2f(x, y);
        GL11.glVertex2f(x, y2);
        GL11.glVertex2f(x2, y2);
        GL11.glEnd();

        // Restore OpenGL state
        GL11.glEnable(GL11.GL_TEXTURE_2D);
        GL11.glDisable(GL11.GL_BLEND);
        GL11.glDisable(GL11.GL_LINE_SMOOTH);
    }

    private void glColor(int hex) {
        // Convert ARGB to RGBA
        float alpha = ((hex >> 24) & 0xFF) / 255.0F;
        float red = ((hex >> 16) & 0xFF) / 255.0F;
        float green = ((hex >> 8) & 0xFF) / 255.0F;
        float blue = (hex & 0xFF) / 255.0F;

        GL11.glColor4f(red, green, blue, alpha);
    }
}