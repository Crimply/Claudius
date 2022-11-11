package me.crimp.claudius.mod.modules.text;

import com.mojang.realmsclient.gui.ChatFormatting;
import io.netty.util.internal.ConcurrentSet;
import me.crimp.claudius.event.events.DeathEvent;
import me.crimp.claudius.event.events.TotemPopEvent;
import me.crimp.claudius.mod.command.Command;
import me.crimp.claudius.mod.modules.Module;
import me.crimp.claudius.mod.modules.client.HUD;
import me.crimp.claudius.utils.Timer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;


public class SelfPopCounter extends Module {
    public SelfPopCounter() {
        super("SelfPopCounter", "Counts self totem pops.", Category.Text, true, true, true);
    }

    public static SelfPopCounter INSTANCE = new SelfPopCounter();
    String totemSpelling;
    public int l_Count;
    private Boolean hasdied = false;


    @SubscribeEvent
    public void onPop(TotemPopEvent event) {
        if (!HUD.INSTANCE.SelfPops.getValue()) return;
        if  (hasdied.equals(true)) {l_Count = 0;}
        l_Count++;
        if (l_Count == 1) {totemSpelling = " totem";}
        else if (l_Count > 1 || l_Count == 0) {totemSpelling = " totems";}
        if (event.getPlayer().getEntityId() == mc.player.getEntityId()) {
            Command.sendOverwriteClientMessage(ChatFormatting.BLUE + "You" + ChatFormatting.RESET + " have popped " + ChatFormatting.BLUE + l_Count + ChatFormatting.RESET + totemSpelling);}
    }


    @SubscribeEvent
    public void onDeath(DeathEvent event) {
        if (!HUD.INSTANCE.SelfPops.getValue()) return;
        if (event.getPlayer().getEntityId() == mc.player.getEntityId()) {
            hasdied = true;
            Command.sendOverwriteClientMessage(ChatFormatting.BLUE + "You" + ChatFormatting.RESET + " just died after popping " + ChatFormatting.BLUE + l_Count + ChatFormatting.RESET + totemSpelling);
            }
        }
    }

