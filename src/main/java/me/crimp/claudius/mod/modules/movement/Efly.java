package me.crimp.claudius.mod.modules.movement;

import me.crimp.claudius.mod.command.Command;
import me.crimp.claudius.mod.modules.Module;

public class Efly extends Module {
    public Efly() {
        super("Efly", "Efly", Category.Movement, true, false, false);
    }

    @Override
    public void onEnable() {
        Command.sendMessage("No working rn");
        this.disable();
    }
}