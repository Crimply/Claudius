package me.crimp.claudius;

import club.minnced.discord.rpc.DiscordEventHandlers;
import club.minnced.discord.rpc.DiscordRPC;
import club.minnced.discord.rpc.DiscordRichPresence;

public class DiscordPresence {
    private static final String ID = "1022802066609020928";

    private static final DiscordRichPresence PRESENCE = new DiscordRichPresence();
    private static final DiscordRPC RPC = DiscordRPC.INSTANCE;

    public static void start() {
        DiscordEventHandlers handler = new DiscordEventHandlers();


        handler.disconnected = ((errorCode, message) -> System.out.println("Discord RPC disconnected, errorCode: " + errorCode + ", message: " + message));

        RPC.Discord_Initialize(ID, handler, true, null);

        PRESENCE.startTimestamp = System.currentTimeMillis() / 1000L;
        PRESENCE.details = "Best Util Client";
        PRESENCE.largeImageKey = "hamlet";
        PRESENCE.largeImageText = "Playing Claudius";
        PRESENCE.smallImageKey = "king";
        PRESENCE.smallImageText = "Best Best Best";
        PRESENCE.state = "Obliterating On Lil Kids";

        RPC.Discord_UpdatePresence(PRESENCE);
    }

    public static void stop() {
        RPC.Discord_Shutdown();
        RPC.Discord_ClearPresence();
    }
}
