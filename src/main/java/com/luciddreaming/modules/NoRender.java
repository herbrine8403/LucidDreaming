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

    @Override
    protected void onEnable() {
        LucidDreaming.LOGGER.info("NoRender enabled");
    }

    @Override
    protected void onDisable() {
        LucidDreaming.LOGGER.info("NoRender disabled");
    }

    @Override
    public void onTick() {
        if (mc.world == null || mc.player == null) {
            return;
        }

        // Apply rendering settings
        applyRenderSettings();
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
            mc.gameSettings.particleSetting = 0; // All
        }

        // Hide weather
        if (ModuleConfigs.noRender.hideWeather) {
            mc.world.setRainStrength(0.0f);
            mc.world.setThunderStrength(0.0f);
        }

        // Hide sky (controlled by game settings)
        if (ModuleConfigs.noRender.hideSky) {
            mc.gameSettings.ambientOcclusion = 0;
        }
    }
}