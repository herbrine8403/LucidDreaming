package com.luciddreaming.modules;

import com.luciddreaming.LucidDreaming;
import com.luciddreaming.config.ModuleConfigs;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
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
    public void onRenderGameOverlay(RenderGameOverlayEvent event) {
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

        // Render black screen overlay
        renderBlackScreen();
    }

    private void renderBlackScreen() {
        // Enable blending for transparency
        GL11.glEnable(GL11.GL_BLEND);
        GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
        GL11.glDisable(GL11.GL_DEPTH_TEST);
        GL11.glDisable(GL11.GL_TEXTURE_2D);

        // Get screen dimensions
        ScaledResolution scaledResolution = new ScaledResolution(mc);
        int width = scaledResolution.getScaledWidth();
        int height = scaledResolution.getScaledHeight();

        // Draw black rectangle with opacity
        float opacity = (float) ModuleConfigs.fakeBlackScreen.opacity;
        GL11.glColor4f(0.0f, 0.0f, 0.0f, opacity);
        
        GL11.glBegin(GL11.GL_QUADS);
        GL11.glVertex2f(0, 0);
        GL11.glVertex2f(width, 0);
        GL11.glVertex2f(width, height);
        GL11.glVertex2f(0, height);
        GL11.glEnd();

        // Restore OpenGL state
        GL11.glEnable(GL11.GL_TEXTURE_2D);
        GL11.glEnable(GL11.GL_DEPTH_TEST);
        GL11.glDisable(GL11.GL_BLEND);
        
        GL11.glColor4f(1.0f, 1.0f, 1.0f, 1.0f);
    }
}