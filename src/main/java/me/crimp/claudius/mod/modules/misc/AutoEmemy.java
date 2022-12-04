package me.crimp.claudius.mod.modules.misc;

import me.crimp.claudius.event.events.PacketEvent;
import me.crimp.claudius.mod.modules.Module;
import net.minecraft.network.play.server.SPacketChat;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class AutoEmemy extends Module {
    public AutoEmemy() {
        super("AutoEmemey", "Automatic way of adding Players to enemy list. it adds when killed by them", Category.Misc, false, true);
    }


    @SubscribeEvent
    public void onPacketReceive(PacketEvent.Receive event) {
        if (event.getPacket() instanceof SPacketChat) {
            String text = ((SPacketChat) event.getPacket()).getChatComponent().getUnformattedText();
            if (text.contains(mc.player.getName()) && text.contains("by") && text.contains(mc.player.getName())) {

            }
        }
    }
}