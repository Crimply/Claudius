package me.crimp.claudius.mod.modules.render;

import me.crimp.claudius.mod.modules.Module;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class AcidMode extends Module {

    public AcidMode() {
        super("Pill In ibiza", "", Category.MISC , true ,false, false);
    }

    public static boolean firstRun = true;

    @SubscribeEvent
    public void onRenderGameOverlay(RenderGameOverlayEvent event) {

        if (Minecraft.getMinecraft().world == null || Minecraft.getMinecraft().player == null)
            return;

        if (OpenGlHelper.shadersSupported) {
            if (Minecraft.getMinecraft().currentScreen != null) {
                firstRun = true;
            }
            if (Minecraft.getMinecraft().currentScreen == null && firstRun) {
                Minecraft.getMinecraft().entityRenderer.loadShader(new ResourceLocation("shaders/post/wobble.json"));
                firstRun = false;
            }
        }
    }

    @Override
    public void onDisable() {
        if (mc.player == null || mc.world == null) return;
        Minecraft.getMinecraft().entityRenderer.getShaderGroup();
        Minecraft.getMinecraft().entityRenderer.getShaderGroup().deleteShaderGroup();
        firstRun = true;
        super.onDisable();
    }

}
