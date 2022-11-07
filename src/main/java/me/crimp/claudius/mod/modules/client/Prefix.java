package me.crimp.claudius.mod.modules.client;

import me.crimp.claudius.Claudius;
import me.crimp.claudius.event.events.ClientEvent;
import me.crimp.claudius.mod.modules.Module;
import me.crimp.claudius.mod.setting.Setting;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class Prefix extends Module {
    public Setting<String> prefix = this.register(new Setting("Prefix", "!"));
    public Prefix() {super("prefix", "", Module.Category.Client, true, true, true);}
    public static Prefix INSTANCE = new Prefix();

    @SubscribeEvent
    public void onSettingChange(ClientEvent event) {
        Claudius.commandManager.setPrefix(this.prefix.getValue());
    }


    @Override
    public void onLoad() {
        Claudius.commandManager.setPrefix(this.prefix.getValue());
    }
}