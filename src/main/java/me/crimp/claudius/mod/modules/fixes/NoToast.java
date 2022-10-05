package me.crimp.claudius.mod.modules.fixes;

import me.crimp.claudius.mod.modules.Module;
import net.minecraft.client.gui.toasts.IToast;

public class NoToast extends Module {
    public NoToast() {
        super("NoToast", "NoToast", Category.FIXES, true, false, false);
    }


    @Override
    public void onUpdate() {
        mc.toastGui.clear();
    }
}

