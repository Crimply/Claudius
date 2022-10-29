package me.crimp.claudius.mod.modules.fixes;

import me.crimp.claudius.mod.modules.Module;

public class NoToast extends Module {
    public NoToast() {
        super("NoToast", "NoToast", Category.Fixes, true, false, false);
    }


    @Override
    public void onUpdate() {
        mc.toastGui.clear();
    }
}

