package me.crimp.claudius.mod.modules.client;

import me.crimp.claudius.mod.gui.screens.CSGOGui;
import me.crimp.claudius.mod.modules.Module;

public class CSGOGuiModule extends Module {
    public CSGOGuiModule() {
        super("CSGOGui", "Opens a  CSGO style GUI", Category.CLIENT, true, false, false);
    }

    @Override
    public void onEnable() {
        mc.displayGuiScreen(new CSGOGui());
    }
}
