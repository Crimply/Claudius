package me.crimp.claudius.mod.modules.client;

import com.mojang.realmsclient.gui.ChatFormatting;
import me.crimp.claudius.Claudius;
import me.crimp.claudius.event.events.ClientEvent;
import me.crimp.claudius.mod.command.Command;
import me.crimp.claudius.mod.modules.Module;
import me.crimp.claudius.mod.setting.Setting;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class Prefix extends Module {
    public Setting<String> prefix = this.register(new Setting("Prefix", "!"));
    public Prefix() {super("prefix", "", Module.Category.Client, true, false, true);}
    public static Prefix INSTANCE = new Prefix();

    @SubscribeEvent
    public void onSettingChange(ClientEvent event) {
        if (event.getSetting().equals(prefix)) {
            Claudius.commandManager.setPrefix(prefix.getPlannedValue());
            Command.sendMessage("Prefix set to " + ChatFormatting.DARK_GRAY + Claudius.commandManager.getPrefix());
        }
    }

    @Override
    public void onLoad() {
        Claudius.commandManager.setPrefix(prefix.getValue());
    }
}