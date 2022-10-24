package me.crimp.claudius.mod.modules.client;

import me.crimp.claudius.DiscordPresence;
import me.crimp.claudius.mod.modules.Module;
import me.crimp.claudius.mod.setting.Setting;

public class RPC
        extends Module {
    public static RPC INSTANCE;
    //public final Setting<Boolean> IP = this.register(new Setting<>("id",true));

    public RPC() {
        super("DiscordRichP", "Discord rich presence", Module.Category.CLIENT, false, false, false);
        this.enabled.setValue(true);
        this.drawn.setValue(false);
        INSTANCE = this;
    }

    @Override
    public void onEnable() {
        DiscordPresence.start();
    }

    @Override
    public void onDisable() {
        DiscordPresence.stop();
    }
}

