package me.crimp.claudius.event.events;

import me.crimp.claudius.event.EventStage;
import net.minecraft.util.DamageSource;

public class DeathCauseEvent extends EventStage {

    private DamageSource cause;
    public DeathCauseEvent(DamageSource cause) {
        this.cause = cause;
    }
    public DamageSource getCause() {
        return cause;
    }
}
