package me.crimp.claudius.mod.modules.fixes;

import net.minecraft.network.play.server.SPacketChat;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import me.crimp.claudius.event.events.PacketEvent;
import me.crimp.claudius.mod.modules.Module;
import me.crimp.claudius.mod.command.Command;

public class AntiLog4j extends Module {
    public AntiLog4j() {
        super("AntiLog4J", "Stops log4j", Category.Fixes, true, false, true);
        this.enabled.setValue(true);
        this.drawn.setValue(false);
    }


    @SubscribeEvent
    public void onPacketReceive(PacketEvent.Receive event) {
        if (event.getPacket() instanceof SPacketChat) {
            String text = ((SPacketChat) event.getPacket()).getChatComponent().getUnformattedText();
            if (text.contains("${") || text.contains("$<") || text.contains("$:-") || text.contains("jndi:ldap") || text.contains("$(")) {
                //DO NOT USE THE logger IN CornerStore CLASS THAT WOULD BE PRINTING THE RCE CODE INTO LOG4J.
                Command.sendMessage("Potential RCE Exploit Blocked.");
                event.setCanceled(true);
            }
        }
    }
}