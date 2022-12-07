package me.crimp.claudius.mod.modules.misc;


import me.crimp.claudius.event.events.DeathCauseEvent;
import me.crimp.claudius.mod.command.Command;
import me.crimp.claudius.mod.modules.Module;
import net.minecraft.util.DamageSource;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;


public class AutoEmemy extends Module {
    public AutoEmemy() {
        super("AutoEnemy", "Automatic way of adding Players to enemy list. it adds when killed by them", Category.Misc, false, true);
    }

    @SubscribeEvent
    public void onDeath(DeathCauseEvent cause) {
        DamageSource a = cause.getCause();
        Command.sendMessage(a.toString());
    }
}