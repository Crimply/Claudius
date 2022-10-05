package me.crimp.claudius.mod.command.commands;

import com.mojang.realmsclient.gui.ChatFormatting;
import me.crimp.claudius.mod.command.Command;

import java.awt.*;
import java.net.URI;

public class AutoDox extends Command {
    public AutoDox() {
        super("AutoDox", new String[0]);
    }

    @Override
    public void execute(String[] commands) {
        //mc.ingameGUI.addChatMessage(ChatType.SYSTEM, new TextComponentString("https://tenor.com/view/swat-gif-22417124"));
        mc.player.sendChatMessage("");
        Command.sendMessage("Now Ur On DoxBin Cunt" + ChatFormatting.BLUE);
        final Desktop desktop = Desktop.isDesktopSupported() ? Desktop.getDesktop() : null;
        if (desktop != null && desktop.isSupported(Desktop.Action.BROWSE)) {
            try {
                desktop.browse(new URI("https://www.youtube.com/watch?v=tw0tinIDWzQ&ab_channel=JamesBandz-Topic"));
            }
            catch (Throwable ignored) {}
        }

    }
}

