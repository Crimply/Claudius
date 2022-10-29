package me.crimp.claudius.mod.modules.pvp;

import me.crimp.claudius.mod.modules.Module;
import net.minecraft.network.play.client.CPacketChatMessage;

public class AutoKiller extends Module {
    public AutoKiller() {
        super("AutoKiller", "AutoKiller", Category.Pvp, true, false, false);
    }

    @Override
    public void onEnable() {
        mc.player.connection.sendPacket(new CPacketChatMessage("/Kill"));
        this.enabled.setValue(Boolean.FALSE);
    }
}

