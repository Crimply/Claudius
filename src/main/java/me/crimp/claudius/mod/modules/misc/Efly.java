package me.crimp.claudius.mod.modules.misc;

import me.crimp.claudius.mod.command.Command;
import me.crimp.claudius.mod.modules.Module;
import me.crimp.claudius.mod.setting.Setting;
import net.minecraftforge.client.event.ClientChatEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class Efly extends Module {
    public Efly() {
        super("Efly", "Efly", Category.MISC, true, false, false);
    }

    @Override
    public void onEnable() {
        Command.sendMessage("No working rn");
        this.disable();
    }
}