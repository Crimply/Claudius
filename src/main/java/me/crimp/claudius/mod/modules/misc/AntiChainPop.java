package me.crimp.claudius.mod.modules.misc;

import me.crimp.claudius.mod.modules.Module;
import net.minecraft.network.play.client.CPacketChatMessage;
import net.minecraft.network.play.server.SPacketDisconnect;
import net.minecraft.util.text.TextComponentString;

public class AntiChainPop extends Module {
    public AntiChainPop() {
        super("AntiChainPop", "AntiChainPop", Category.MISC, true, false, false);
    }

    @Override
    public void onUpdate() {
        if (mc.player.getHealth() == 3.5f) {
            mc.player.connection.sendPacket(new CPacketChatMessage("Holdon Mum Wants Me"));
            mc.player.connection.sendPacket(new SPacketDisconnect(new TextComponentString("ANTICHAINPOP")));
        }
    }
}