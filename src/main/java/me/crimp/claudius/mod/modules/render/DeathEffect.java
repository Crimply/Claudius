package me.crimp.claudius.mod.modules.render;

import me.crimp.claudius.managers.ColorManager;
import me.crimp.claudius.mod.modules.Module;
import me.crimp.claudius.mod.modules.pvp.PopCounter;
import me.crimp.claudius.mod.setting.Setting;
import me.crimp.claudius.utils.RenderUtil;
import net.minecraft.entity.effect.EntityLightningBolt;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.util.math.Vec3d;

import java.util.ArrayList;
import java.util.ConcurrentModificationException;


public class DeathEffect extends Module {
    private static DeathEffect INSTANCE = new DeathEffect();
    private final Setting<Boolean> sound = register(new Setting<>("sound", true, ""));
    private final Setting<Integer> numberSound = register(new Setting<>("numberSound", 1, 1, 10));

    public DeathEffect() {
        super("DeathEffect", "DeathEffect", Category.RENDER, true, false, false);
    }

    public static DeathEffect getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new DeathEffect();
        }
        return INSTANCE;
    }

    final Object sync = new Object();

    @Override
    public void onUpdate() {
        if (mc.world == null) {
            return;
        }
    }

    public void onDeath(EntityPlayer player) {
        mc.world.spawnEntity(new EntityLightningBolt(mc.world, player.posX, player.posY, player.posZ, true));
        if (this.sound.getValue()) {
            mc.player.playSound(SoundEvents.ENTITY_LIGHTNING_THUNDER, 0.5f, 1.f);
        }
    }
}
