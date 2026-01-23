package com.luciddreaming.modules;

import com.luciddreaming.LucidDreaming;
import com.luciddreaming.config.ModuleConfigs;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.Entity;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;

public class NoRender extends Module {
    private final Minecraft mc = Minecraft.getMinecraft();

    public NoRender() {
        super("NoRender", "Limit rendering to save performance", ModuleCategory.RENDER);
    }

    // Store original settings
    private float originalRainStrength = 0.0f;
    private float originalThunderStrength = 0.0f;
    private int originalParticleSetting = 0;
    private int originalAmbientOcclusion = 0;

    @Override
    protected void onEnable() {
        LucidDreaming.LOGGER.info("NoRender enabled");
        // Save original settings
        if (mc.world != null) {
            originalRainStrength = mc.world.getRainStrength(1.0f);
            originalThunderStrength = mc.world.getThunderStrength(1.0f);
        }
        originalParticleSetting = mc.gameSettings.particleSetting;
        originalAmbientOcclusion = mc.gameSettings.ambientOcclusion;
    }

    @Override
    protected void onDisable() {
        LucidDreaming.LOGGER.info("NoRender disabled");
        // Restore all original settings when module is disabled
        restoreOriginalSettings();
    }

    @Override
    public void onTick() {
        if (mc.world == null || mc.player == null) {
            return;
        }

        // Apply rendering settings only if enabled
        if (isEnabled()) {
            applyRenderSettings();
        }
    }

    private void applyRenderSettings() {
        // Hide entities
        if (ModuleConfigs.noRender.hideEntities) {
            for (Entity entity : mc.world.loadedEntityList) {
                if (entity != mc.player) {
                    entity.setInvisible(true);
                }
            }
        } else {
            for (Entity entity : mc.world.loadedEntityList) {
                entity.setInvisible(false);
            }
        }

        // Hide particles (controlled by game settings)
        if (ModuleConfigs.noRender.hideParticles) {
            mc.gameSettings.particleSetting = 2; // Minimal
        } else {
            mc.gameSettings.particleSetting = originalParticleSetting;
        }

        // Hide weather
        if (ModuleConfigs.noRender.hideWeather) {
            mc.world.setRainStrength(0.0f);
            mc.world.setThunderStrength(0.0f);
        }

        // Hide sky (controlled by game settings)
        if (ModuleConfigs.noRender.hideSky) {
            mc.gameSettings.ambientOcclusion = 0;
        } else {
            mc.gameSettings.ambientOcclusion = originalAmbientOcclusion;
        }
    }

    private void restoreOriginalSettings() {
        // Restore all entities to visible
        if (mc.world != null) {
            for (Entity entity : mc.world.loadedEntityList) {
                entity.setInvisible(false);
            }
        }

        // Restore weather settings
        if (mc.world != null) {
            // Reset weather settings (this will allow natural weather to return)
            mc.world.setRainStrength(originalRainStrength);
            mc.world.setThunderStrength(originalThunderStrength);
        }

        // Restore particle settings
        mc.gameSettings.particleSetting = originalParticleSetting;

        // Restore sky/ambient occlusion settings
        mc.gameSettings.ambientOcclusion = originalAmbientOcclusion;
    }
}