package me.crimp.claudius.mod.modules.text;

import com.mojang.realmsclient.gui.ChatFormatting;
import io.netty.util.internal.ConcurrentSet;
import me.crimp.claudius.mod.command.Command;
import me.crimp.claudius.mod.modules.Module;
import me.crimp.claudius.mod.modules.client.HUD;
import net.minecraft.entity.player.EntityPlayer;

import java.util.HashMap;
import java.util.Set;

public class SelfPopCounter
        extends Module {
    public static HashMap<String, Integer> TotemPopContainer = new HashMap();
    private final Set<EntityPlayer> announcers = new ConcurrentSet<>();

    public SelfPopCounter() {
        super("SelfPopCounter", "Counts self totem pops.", Category.Text, true, false, false);
    }
    public static SelfPopCounter INSTANCE = new SelfPopCounter();
    String totemSpelling;
    public int l_Count = 1;
    public void onTotemPop(EntityPlayer player) {
        if (SelfPopCounter.fullNullCheck()) {
            return;
        }
        if (TotemPopContainer.containsKey(player.getName())) {
            l_Count = TotemPopContainer.get(player.getName());
            TotemPopContainer.put(player.getName(), ++l_Count);
        } else {
            TotemPopContainer.put(player.getName(), l_Count);
        }

    }

    public void onUpdate() {
        if (!this.announcers.isEmpty() && HUD.INSTANCE.isEnabled() && HUD.INSTANCE.SelfPop.getValue()) {
            int count = l_Count;
            for (EntityPlayer player : this.announcers) {
                if (count != -1) {
                    if (count == 1) {
                        totemSpelling = " totem!";
                    } else if (count > 1) {
                        totemSpelling = " totems!";
                    }
                    if (player.getName().equals(mc.player.getName())) {
                        Command.sendMessage(ChatFormatting.LIGHT_PURPLE + "I" + ChatFormatting.RESET + " have popped " + ChatFormatting.LIGHT_PURPLE + count + ChatFormatting.RESET + totemSpelling);
                    }
                }
                this.announcers.remove(player);
            }
        }
    }
}

