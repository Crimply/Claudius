package me.crimp.claudius.mod.modules.misc;

import me.crimp.claudius.mod.modules.Module;

public class AntiChainPop extends Module {
    public AntiChainPop() {
        super("AntiChainPop", "AntiChainPop", Category.MISC, true, false, false);
    }

    @Override
    public void onUpdate() {
        if (mc.player.getHealth() == 0) {mc.world.sendQuittingDisconnectingPacket();}
    }
}