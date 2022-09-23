package me.crimp.claudius.mod.modules.misc;

import me.crimp.claudius.mod.modules.Module;
import net.minecraftforge.client.event.ClientChatEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class ChatSuffix extends Module {
    public ChatSuffix() {
        super("ChatSuffix", "Appends your message", Category.MISC, true, false, false);
    }

    @SubscribeEvent
    public void onChat(ClientChatEvent event) {
        String OnPointSuffix = " \u23d0\u23d0 \u1d04\u0280\u026a\u1d0d\u1d18\u1d04\u029f\u026a\u1d07\u0274\u1d1b";
        if (event.getMessage().startsWith("/") || event.getMessage().startsWith(".")
                || event.getMessage().startsWith(",") || event.getMessage().startsWith("-")
                || event.getMessage().startsWith("!") || event.getMessage().startsWith("@")
                || event.getMessage().startsWith("$") || event.getMessage().startsWith("*")) return;
        event.setMessage(event.getMessage() + OnPointSuffix); // Adds the suffix to the end of the message
    }
}