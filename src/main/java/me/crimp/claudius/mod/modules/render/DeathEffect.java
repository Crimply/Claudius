package me.crimp.claudius.mod.modules.render;

import me.crimp.claudius.mod.modules.Module;
import net.minecraft.entity.effect.EntityLightningBolt;
import net.minecraft.entity.player.EntityPlayer;

public class DeathEffect extends Module {
    public DeathEffect() {
        super("DeathEffect", "DeathEffect", Category.Render, true, false, false);
    }
    public static DeathEffect INSTANCE = new DeathEffect();

    public void onDeath(EntityPlayer player) {
        if (this.enabled.getValue()) {
            mc.world.spawnEntity(new EntityLightningBolt(mc.world, player.posX, player.posY, player.posZ, true));
        }
    }
}
