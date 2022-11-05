package me.crimp.claudius.mod.modules.text;

import me.crimp.claudius.mod.command.Command;
import me.crimp.claudius.mod.modules.Module;
import net.minecraftforge.client.event.ClientChatEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class ChatSuffix extends Module {
    public ChatSuffix() {
        super("ChatSuffix", "Appends your message", Category.Text, true, false, false);
    }

    @SubscribeEvent
    public void onChat(ClientChatEvent event) {
        if (event.getMessage().startsWith("/")
                || event.getMessage().startsWith(".") || event.getMessage().startsWith(",") || event.getMessage().startsWith("-")
                || event.getMessage().startsWith("!") || event.getMessage().startsWith(Command.getCommandPrefix(toString())) || event.getMessage().startsWith("@") || event.getMessage().startsWith("$") || event.getMessage().startsWith("*")) return;
        event.setMessage(event.getMessage() + " \u1d9c\u02e1\u1d43\u1d58\u1d48\u1da6\u1d58\u02e2"); // Adds the suffix to the end of the message
    }
}