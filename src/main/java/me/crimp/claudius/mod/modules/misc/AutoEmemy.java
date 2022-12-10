package me.crimp.claudius.mod.modules.misc;


import com.mojang.realmsclient.gui.ChatFormatting;
import me.crimp.claudius.claudius;
import me.crimp.claudius.event.events.PacketEvent;
import me.crimp.claudius.mod.command.Command;
import me.crimp.claudius.mod.modules.Module;
import me.crimp.claudius.mod.setting.Setting;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.network.play.server.SPacketChat;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.util.ArrayList;
import java.util.List;


public class AutoEmemy extends Module {
    public Setting<Boolean> Crashgame = register(new Setting<>("Add Msgs", false, "wiw"));
    public AutoEmemy() {
        super("AutoEnemy", "Automatic way of adding Players to enemy list. it adds when killed by them", Category.Misc, false, true);
    }

    //private final List<String> fyck = new ArrayList<>();

    @SubscribeEvent
    public void onPacketReceive(PacketEvent.Receive event) {
        if (event.getPacket() instanceof SPacketChat) {
            String text = ((SPacketChat) event.getPacket()).getChatComponent().getFormattedText();
            if (text.contains("by") || text.contains("got") || text.contains("fucked") || text.contains("shoved")) {
                if (text.contains(mc.player.getName())) {
                    for (Object o : mc.world.loadedEntityList) {
                        if (o instanceof EntityPlayer && o != mc.player) {
                            EntityPlayer entity = (EntityPlayer) o;
                            String name = entity.getGameProfile().getName();

                            Command.sendMessage(name + " : check 1");

                            if (text.contains(name)) {
                                claudius.enemyManager.addEnemy(name);
                                if (Crashgame.getValue()) {
                                    Command.sendMessage(name + "Is Now A " + ChatFormatting.RED + "Enemy");
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}