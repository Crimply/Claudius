package me.crimp.claudius.mod.command.commands;

import me.crimp.claudius.Claudius;
import me.crimp.claudius.mod.command.Command;

public class ReloadCommand
        extends Command {
    public ReloadCommand() {
        super("reload", new String[0]);
    }

    @Override
    public void execute(String[] commands) {
        Claudius.capeManager.reload();
        Claudius.reload();

    }
}

