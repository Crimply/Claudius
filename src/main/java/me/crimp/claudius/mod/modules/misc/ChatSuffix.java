package me.crimp.claudius.mod.modules.misc;

import me.crimp.claudius.mod.modules.Module;
import net.minecraftforge.client.event.ClientChatEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class ChatSuffix extends Module {
    public ChatSuffix() {
        super("ChatSuffix", "Appends your message", Category.Misc, true, false, false);
    }

    @SubscribeEvent
    public void onChat(ClientChatEvent event) {
        String OnPointSuffix = " \u1d9c\u02e1\u1d43\u1d58\u1d48\u1da6\u1d58\u02e2";
        if (event.getMessage().startsWith("/") || event.getMessage().startsWith(".")
                || event.getMessage().startsWith(",") || event.getMessage().startsWith("-")
                || event.getMessage().startsWith("!") || event.getMessage().startsWith("@")
                || event.getMessage().startsWith("$") || event.getMessage().startsWith("*")) return;
        event.setMessage(event.getMessage() + OnPointSuffix); // Adds the suffix to the end of the message
    }
}