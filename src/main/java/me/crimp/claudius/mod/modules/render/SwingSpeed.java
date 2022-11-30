package me.crimp.claudius.mod.modules.render;

import me.crimp.claudius.mod.modules.Module;
import me.crimp.claudius.mod.setting.Setting;

public class SwingSpeed extends Module {
    public Setting<Boolean> on = register(new Setting<>("Its On?", true));
    private static SwingSpeed INSTANCE = new SwingSpeed();
    public Setting<RenderMode> mode = this.register(new Setting<RenderMode>("PMode", RenderMode.ON));
    public SwingSpeed() {
        super("SwingSpeed", "Changes Swing Speed", Category.Render, false, false);
    }

    public static SwingSpeed getINSTANCE() {
        if (INSTANCE == null) {
            INSTANCE = new SwingSpeed();
        }
        return INSTANCE;
    }

    private void setInstance() {
        INSTANCE = this;
    }
    public enum RenderMode {
        SOLID,
        ON

    }
}
