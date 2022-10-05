package me.crimp.claudius.mod.command.commands;

import com.mojang.realmsclient.gui.ChatFormatting;
import me.crimp.claudius.mod.command.Command;
import me.crimp.claudius.utils.PlayerUtil;

import java.util.List;
import java.util.UUID;

public class HistoryCommand extends Command {
    public HistoryCommand() {
        super("names", new String[]{"<player>"});
    }

    @Override
    public void execute(String[] commands) {
        List<String> names;
        UUID uuid;
        if (commands.length == 1 || commands.length == 0) sendMessage(ChatFormatting.RED + "Please specify a player.");
        try {
            uuid = PlayerUtil.getUUIDFromName(commands[0]);
        } catch (Exception e) {
            sendMessage("An error occured.");
            return;
        }
        try {
            names = PlayerUtil.getHistoryOfNames(uuid);
        } catch (Exception e) {
            sendMessage("An error occured.");
            return;
        }
        if (names != null) {
            sendMessage(commands[0] + "\u00c2\u00b4s name history:");
            for (String name : names) {
                sendMessage(name);
            }
        } else {
            sendMessage("No names found.");
        }
    }
}

