package me.crimp.claudius.mod.modules.fixes;


import me.crimp.claudius.mod.modules.Module;
import org.lwjgl.opengl.Display;



public class SetDisplay extends Module {
    public SetDisplay() {
        super("SetDisplay", "Sets Display Title To Minecraft 1.12.2 Fixing Medal.tv Clipping Software", Category.FIXES, true, false, false);
        this.enabled.setValue(true);
        this.drawn.setValue(false);
    }

    @Override
    public void onUpdate() {
        Display.setTitle("Minecraft 1.12.2");
    }
}
