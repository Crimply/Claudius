package me.crimp.claudius.mod.modules.render;

import me.crimp.claudius.mod.modules.Module;

public class NoToast extends Module {
    public NoToast() {
        super("NoToast", "NoToast", Category.Render, false, false);
    }


    @Override
    public void onUpdate() {
        mc.toastGui.clear();
    }
}

