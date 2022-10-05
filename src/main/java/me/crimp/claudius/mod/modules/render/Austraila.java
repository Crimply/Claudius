package me.crimp.claudius.mod.modules.render;

import me.crimp.claudius.mod.modules.Module;
import me.crimp.claudius.utils.Util;
import net.minecraft.client.settings.GameSettings;
import net.minecraft.util.ResourceLocation;

public class Austraila extends Module {
    public Austraila() {
        super("Austraila", "Austraila", Category.RENDER, true, false, false);
    }

    int Fov = -110;
    int Fov2 = 110;

    @Override
    public void onEnable() {
        mc.gameSettings.setOptionFloatValue(GameSettings.Options.FOV, this.Fov);
    }

    @Override
    public void onDisable() {
        mc.gameSettings.setOptionFloatValue(GameSettings.Options.FOV, this.Fov2);
    }
}
