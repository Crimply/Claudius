package me.crimp.claudius.mod.modules.client;

import me.crimp.claudius.Claudius;
import me.crimp.claudius.mod.modules.Module;
import me.crimp.claudius.mod.setting.Setting;

public class Prefix extends Module {
    public Setting<String> prefix = this.register(new Setting("Prefix", "!"));
    public Prefix() {super("prefix", "", Module.Category.Client, true, true, true);}
    public static Prefix INSTANCE = new Prefix();

    public void SetPrefix() {
        Claudius.commandManager.setPrefix(prefix.getPlannedValue());
    }

    @Override
    public void onLoad() {
        Claudius.commandManager.setPrefix(prefix.getValue());
    }
}