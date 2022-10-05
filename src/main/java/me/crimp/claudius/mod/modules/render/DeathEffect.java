package me.crimp.claudius.mod.modules.render;

import me.crimp.claudius.mod.modules.Module;
import me.crimp.claudius.mod.setting.Setting;
import net.minecraft.entity.effect.EntityLightningBolt;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;


public class DeathEffect extends Module {
    private static DeathEffect INSTANCE = new DeathEffect();
    private final Setting<Boolean> sound = register(new Setting<>("sound", true, ""));

    public DeathEffect() {
        super("DeathEffect", "DeathEffect", Category.RENDER, true, false, false);
    }

    public static DeathEffect getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new DeathEffect();
        }
        return INSTANCE;
    }
    public void onDeath(EntityPlayer player) {
        mc.world.spawnEntity(new EntityLightningBolt(mc.world, player.posX, player.posY, player.posZ, true));
        if (this.sound.getValue()) {
            mc.player.playSound(SoundEvents.ENTITY_LIGHTNING_THUNDER, 0.5f, 1.f);
        }
    }
}
