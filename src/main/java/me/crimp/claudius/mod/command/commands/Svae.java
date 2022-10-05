package me.crimp.claudius.mod.command.commands;

import com.mojang.realmsclient.gui.ChatFormatting;
import me.crimp.claudius.Claudius;
import me.crimp.claudius.mod.command.Command;

import static me.crimp.claudius.Claudius.configManager;

public class Svae extends Command {
    public Svae() {
        super("Save", new String[0]);
    }

    @Override
    public void execute(String[] commands) {
        configManager.saveConfig(Claudius.configManager.config.replaceFirst("claudius/", ""));
        mc.player.sendChatMessage(".save");
        mc.player.sendChatMessage("*save");
        Command.sendMessage("Prolly Saved Ur configs" + ChatFormatting.BLUE);
    }
}

