package me.crimp.claudius.mod.modules.render;

import me.crimp.claudius.mod.modules.Module;
import me.crimp.claudius.mod.setting.Setting;

public class SwingSpeed extends Module {
    public Setting<Integer> Ampl = this.register(new Setting<>("Amplifier", 20,5,50));
    public SwingSpeed() {
        super("SwingSpeed", "strichcode", Category.Render, true, false, false);
    }
    public static SwingSpeed INSTANCE = new SwingSpeed();
}


























