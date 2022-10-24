package me.crimp.claudius.mod.modules.client;

import me.crimp.claudius.DiscordPresence;
import me.crimp.claudius.mod.modules.Module;
import me.crimp.claudius.mod.setting.Setting;

public class RPC
        extends Module {
    public static RPC INSTANCE;
    public final Setting<String> Id = this.register(new Setting<>("id","1022802066609020928"));
    public final Setting<String> Key = this.register(new Setting<>("Key","1022802066609020928"));

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

