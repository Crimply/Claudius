package me.crimp.claudius.mod.modules.render;

import me.crimp.claudius.mod.modules.Module;

public class SwingSpeed extends Module {
   //  public Setting<Boolean> Ampl = this.register(new Setting<>("Amplifier", false));
    public SwingSpeed() {
        super("SwingSpeed", "strichcode", Category.Render, true, true, false);
    }
    public static SwingSpeed INSTANCE = new SwingSpeed();

    @Override
    public void onUpdate() {
        if (this.isEnabled()) this.disable();
    }
}


























