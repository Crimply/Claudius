package me.crimp.claudius.mod.modules.render;

import me.crimp.claudius.mod.modules.Module;
import net.minecraft.entity.effect.EntityLightningBolt;
import net.minecraft.entity.player.EntityPlayer;

public class DeathEffect extends Module {
    private static final DeathEffect INSTANCE = new DeathEffect();

    public DeathEffect() {
        super("DeathEffect", "DeathEffect", Category.Render, true, false, false);
    }

    public static DeathEffect getInstance() {
        return INSTANCE;
    }

    public void onDeath(EntityPlayer player) {
        if (this.enabled.getValue()) {
            mc.world.spawnEntity(new EntityLightningBolt(mc.world, player.posX, player.posY, player.posZ, true));
        }
    }
}
