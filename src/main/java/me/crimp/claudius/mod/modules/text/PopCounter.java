package me.crimp.claudius.mod.modules.text;

import com.mojang.realmsclient.gui.ChatFormatting;
import me.crimp.claudius.event.events.DeathEvent;
import me.crimp.claudius.event.events.TotemPopEvent;
import me.crimp.claudius.mod.command.Command;
import me.crimp.claudius.mod.modules.Module;
import me.crimp.claudius.mod.setting.Setting;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;


public class PopCounter extends Module {
    public Setting<Boolean> PopMsg = this.register(new Setting<>("Send in Chat", false));
    public PopCounter() {
        super("PopCounter", "Counts other players totem pops.", Category.Text, false, false);
    }
    public static PopCounter INSTANCE = new PopCounter();
    String totemSpelling;
    public int TotemCount;
    private Boolean hasdied = false;

    @Override
    public void onToggle() {
        TotemCount = 0;
    }


    @SubscribeEvent
    public void onPop(TotemPopEvent event) {
        if (hasdied.equals(true)) {
            hasdied = false;
            TotemCount = 0;
        }
        TotemCount++;
        if (TotemCount == 1) {
            totemSpelling = " totem";
        } else if (TotemCount > 1) {
            totemSpelling = " totems";
        }
        if (event.getPlayer().getEntityId() != mc.player.getEntityId()) {
            Command.sendOverwriteClientMessage(event.getPlayer().getName() + ChatFormatting.RESET + " have popped " + ChatFormatting.BLUE + TotemCount + ChatFormatting.RESET + totemSpelling);
        }
    }


    @SubscribeEvent
    public void onDeath(DeathEvent event) {
        if (event.getPlayer().getEntityId() != mc.player.getEntityId()) {
            hasdied = true;
            if (TotemCount == 0) {
                Command.sendOverwriteClientMessage(event.getPlayer().getName() + ChatFormatting.RESET + " just died after popping " + ChatFormatting.BLUE + "0" + ChatFormatting.RESET + "totems");
            } else {
                Command.sendOverwriteClientMessage(event.getPlayer().getName() + ChatFormatting.RESET + " just died after popping " + ChatFormatting.BLUE + TotemCount + ChatFormatting.RESET + totemSpelling);
            }
        }
    }
}