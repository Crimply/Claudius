package me.crimp.claudius.mod.modules.text;

import com.mojang.realmsclient.gui.ChatFormatting;
import io.netty.util.internal.ConcurrentSet;
import me.crimp.claudius.event.events.TotemPopEvent;
import me.crimp.claudius.mod.command.Command;
import me.crimp.claudius.mod.modules.Module;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.util.HashMap;
import java.util.Set;

public class SelfPopCounter
        extends Module {
    public static HashMap<String, Integer> TotemPopContainer = new HashMap();
    private final Set<EntityPlayer> player = new ConcurrentSet<>();

    public SelfPopCounter() {
        super("SelfPopCounter", "Counts self totem pops.", Category.Text, true, false, false);
    }
    public static SelfPopCounter INSTANCE = new SelfPopCounter();
    String totemSpelling;
    public int l_Count = 1;


    @SubscribeEvent
    public void onPop(TotemPopEvent event) {
            for (EntityPlayer player : this.player) {
                if (SelfPopCounter.fullNullCheck()) {
                    return;
                }
                if (TotemPopContainer.containsKey(player.getName())) {
                    l_Count = TotemPopContainer.get(player.getName());
                    TotemPopContainer.put(player.getName(), ++l_Count);
                } else {
                    TotemPopContainer.put(player.getName(), l_Count);
                }

                if (l_Count != -1) {
                    if (l_Count == 1) {
                        totemSpelling = " totem";
                    } else if (l_Count > 1) {
                        totemSpelling = " totems";
                    }
                    if (player.getName().equals(mc.player.getName())) {
                        Command.sendMessage(ChatFormatting.LIGHT_PURPLE + "I" + ChatFormatting.RESET + " have popped " + ChatFormatting.LIGHT_PURPLE + l_Count + ChatFormatting.RESET + totemSpelling);
                    }
                }
                this.player.remove(player);
            }
        }
    }

