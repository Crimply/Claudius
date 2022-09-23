package me.crimp.claudius.mod.modules.misc;

import me.crimp.claudius.mod.modules.Module;
import net.minecraft.client.gui.toasts.IToast;

public class NoToast extends Module {
    public NoToast() {
        super("NoToast", "NoToast", Category.MISC, true, false, false);
    }


    @Override
    public void onUpdate() {
        mc.toastGui.clear();
    }
}

