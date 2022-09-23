package me.crimp.claudius.mod.modules.fake;

import me.crimp.claudius.mod.command.Command;
import me.crimp.claudius.mod.modules.Module;
import net.minecraft.client.Minecraft;

public class Auto32kBypass extends Module {
    public Auto32kBypass() {
        super("Auto32kBypass", "Auto32kBypass", Category.EXPLOIT, true, false, false);
    }


    @Override
    public void onEnable() {
        String serverName = Minecraft.getMinecraft().currentServerData.serverIP;
        if (mc.isSingleplayer()) {
            Command.sendSilentMessage("single");
        }

        else {
            Command.sendMessage("if ur on " + serverName + " this wont help u");
        }
    }
}
