package me.crimp.claudius.mod.modules.text;

import com.mojang.realmsclient.gui.ChatFormatting;
import me.crimp.claudius.event.events.DeathEvent;
import me.crimp.claudius.event.events.TotemPopEvent;
import me.crimp.claudius.mod.command.Command;
import me.crimp.claudius.mod.modules.Module;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;


public class SelfPopCounter extends Module {

    public SelfPopCounter() {
        super("SelfPopCounter", "Counts self totem pops.", Category.Text, true, false, false);
    }

    public static SelfPopCounter INSTANCE = new SelfPopCounter();
    String totemSpelling;
    public int l_Count;

    @SubscribeEvent
    public void onPop(TotemPopEvent event) {
        l_Count++;
        if (l_Count == 1) {
            totemSpelling = " totem";
        } else if (l_Count > 1) {
            totemSpelling = " totems";
        }
        if (event.getPlayer().getEntityId() == mc.player.getEntityId()) {
            Command.sendMessage(ChatFormatting.BLUE + "You" + ChatFormatting.RESET + " have popped " + ChatFormatting.BLUE + l_Count + ChatFormatting.RESET + totemSpelling);
        }
    }

    @SubscribeEvent
    public void onDeath(DeathEvent event) {
        if (event.getPlayer().getEntityId() == mc.player.getEntityId()) {
            Command.sendMessage(ChatFormatting.BLUE + "You" + ChatFormatting.RESET + " just died after popping " + ChatFormatting.BLUE + l_Count + ChatFormatting.RESET + totemSpelling);
            l_Count = 0;
        }
    }
}

