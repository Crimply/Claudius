package me.crimp.claudius.mod.modules.render;

import me.crimp.claudius.mod.modules.Module;
import me.crimp.claudius.utils.Util;
import net.minecraft.util.ResourceLocation;

public class AcidOverlay extends Module {

    public AcidOverlay() {
        super("AcidOverlay", "Pill In ibiza", Category.Render, false, false);
    }


    @Override
    public void onEnable() {
        if (this.mc.world != null) {
            Util.mc.entityRenderer.loadShader(new ResourceLocation("shaders/post/wobble.json"));
        }
    }

    @Override
    public void onDisable() {
        if (this.mc.world != null) {
            Util.mc.entityRenderer.getShaderGroup().deleteShaderGroup();
        }
    }
}
