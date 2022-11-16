package me.crimp.claudius.mod.modules.render;

import me.crimp.claudius.mod.modules.Module;
import net.minecraft.client.settings.GameSettings;

public class Austraila extends Module {
    public Austraila() {
        super("Austraila", "Austraila", Category.Render, false, false);
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
