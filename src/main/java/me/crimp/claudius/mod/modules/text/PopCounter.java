package me.crimp.claudius.mod.modules.text;

import me.crimp.claudius.mod.command.Command;
import me.crimp.claudius.mod.modules.Module;
import me.crimp.claudius.mod.setting.Setting;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.network.play.client.CPacketChatMessage;


import java.util.HashMap;

public class PopCounter extends Module {
    public Setting<Boolean> PopMsg = this.register(new Setting<>("Send in Chat", false));

    public PopCounter() {
        super("PopCounter", "Counts other players totem pops.", Category.Text, true, false, false);
    }
    public static PopCounter INSTANCE = new PopCounter();

    @Override
    public void onEnable() {
        l_Count = 0;
    }


    public void onDeath(EntityPlayer player) {
            if (this.enabled.getValue()) {

                if (l_Count == 1) {
                    Command.sendMessage(player.getName() + " died after popping " + l_Count + " Totem");
                    if (this.PopMsg.getValue()) mc.player.connection.sendPacket(new CPacketChatMessage(player.getName() + " died after popping " + l_Count + " Totem, Thanks To The Power Of Claudius"));
                } else {
                    Command.sendMessage(player.getName() + " died after popping " +  l_Count + " Totems");
                    if (this.PopMsg.getValue()) mc.player.connection.sendPacket(new CPacketChatMessage(player.getName() + " died after popping " +  l_Count + " Totems, Thanks To The Power Of Claudius"));
                }
                l_Count = 0;
            }
        }

    int l_Count = 0;
    public void onTotemPop(EntityPlayer player) {
        if (PopCounter.fullNullCheck() || PopCounter.mc.player.equals(player)) return;

        l_Count++;
        if (this.enabled.getValue()) {
            if (l_Count == 1) {
                Command.sendMessage(player.getName() + " popped " + l_Count + " Totem.");
                if (this.PopMsg.getValue()) mc.player.connection.sendPacket(new CPacketChatMessage(player.getName() + " popped " + l_Count + " Totem, Thanks To The Power Of Claudius"));
            } else {
                Command.sendMessage(player.getName() + " popped " + l_Count  + " Totems.");
                if (this.PopMsg.getValue()) mc.player.connection.sendPacket(new CPacketChatMessage(player.getName() + " popped " + l_Count  + " Totems, Thanks To The Power Of Claudius"));
            }
        }
    }
}