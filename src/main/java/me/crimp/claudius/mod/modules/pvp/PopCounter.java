package me.crimp.claudius.mod.modules.pvp;

import me.crimp.claudius.mod.command.Command;
import me.crimp.claudius.mod.modules.Module;
import me.crimp.claudius.mod.setting.Setting;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.network.play.client.CPacketChatMessage;

import java.util.HashMap;

public class PopCounter
        extends Module {
    public Setting<Boolean> PopMsg = this.register(new Setting<>("Send in Chat", false));
    public static HashMap<String, Integer> TotemPopContainer = new HashMap();
    private static PopCounter INSTANCE = new PopCounter();

    public PopCounter() {
        super("PopCounter", "Counts other players totem pops.", Module.Category.PVP, true, false, false);
        this.setInstance();
    }

    public static PopCounter getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new PopCounter();
        }
        return INSTANCE;
    }

    private void setInstance() {
        INSTANCE = this;
    }

    @Override
    public void onEnable() {
        TotemPopContainer.clear();
    }

    public void onDeath(EntityPlayer player) {
        if (TotemPopContainer.containsKey(player.getName())) {
            int l_Count = TotemPopContainer.get(player.getName());
            TotemPopContainer.remove(player.getName());
            if (this.enabled.getValue()) {
                if (l_Count == 1) {
                    Command.sendMessage(player.getName() + " died after popping " + l_Count + " Totem");
                    if (this.PopMsg.getValue()) {
                        mc.player.connection.sendPacket(new CPacketChatMessage(player.getName() + " died after popping " + l_Count + " Totem, Thanks To The Power Of Claudius"));
                    }
                } else {
                    Command.sendMessage(player.getName() + " died after popping " +  l_Count + " Totems");
                    if (this.PopMsg.getValue()) {
                        mc.player.connection.sendPacket(new CPacketChatMessage(player.getName() + " died after popping " +  l_Count + " Totems, Thanks To The Power Of Claudius"));
                    }
                }
            }
        }
    }

    public void onTotemPop(EntityPlayer player) {
        if (PopCounter.fullNullCheck()) {
            return;
        }
        if (PopCounter.mc.player.equals(player)) {
            return;
        }
        int l_Count = 1;
        if (TotemPopContainer.containsKey(player.getName())) {
            l_Count = TotemPopContainer.get(player.getName());
            TotemPopContainer.put(player.getName(), ++l_Count);
        } else {
            TotemPopContainer.put(player.getName(), l_Count);
        }
        if (this.enabled.getValue()) {
            if (l_Count == 1) {
                Command.sendMessage(player.getName() + " popped " + l_Count + " Totem.");
                if (this.PopMsg.getValue()) {
                    mc.player.connection.sendPacket(new CPacketChatMessage(player.getName() + " popped " + l_Count + " Totem, Thanks To The Power Of Claudius"));
                }
            } else {
                Command.sendMessage(player.getName() + " popped " + l_Count  + " Totems.");
                if (this.PopMsg.getValue()) {
                    mc.player.connection.sendPacket(new CPacketChatMessage(player.getName() + " popped " + l_Count  + " Totems, Thanks To The Power Of Claudius"));
                }
            }
        }
    }
}

