package me.crimp.claudius.mod.modules.client;

import me.crimp.claudius.DiscordPresence;
import me.crimp.claudius.mod.modules.Module;

public class RPC
        extends Module {
    public static RPC INSTANCE;

    public RPC() {
        super("DiscordRippyBippy", "Discord rich presence", Module.Category.CLIENT, false, false, false);
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

