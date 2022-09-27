package me.crimp.claudius.mod.modules.render;

import me.crimp.claudius.managers.ColorManager;
import me.crimp.claudius.mod.modules.Module;
import me.crimp.claudius.mod.setting.Setting;
import me.crimp.claudius.utils.RenderUtil;
import net.minecraft.entity.effect.EntityLightningBolt;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.util.math.Vec3d;

import java.util.ArrayList;
import java.util.ConcurrentModificationException;

public class DeathEffect extends Module {
    private final Setting<Boolean> thunder = register(new Setting<>("thunder", true, ""));
    private final Setting<Integer> numbersThunder = register(new Setting<>("numbersThunder", 1, 1, 10));
    private final Setting<Boolean> sound = register(new Setting<>("sound", true, ""));
    private final Setting<Integer> numberSound = register(new Setting<>("numberSound", 1, 1, 10));

    public DeathEffect() {
        super("DeathEffect", "DeathEffect", Category.RENDER, true, false, false);
    }

    ArrayList<EntityPlayer> playersDead = new ArrayList<>();

    final Object sync = new Object();


    @Override
    public void onEnable() {
        playersDead.clear();
    }

    @Override
    public void onUpdate() {

        if (mc.world == null) {
            playersDead.clear();
            return;
        }

        mc.world.playerEntities.forEach(entity -> {
            if (playersDead.contains(entity)) {
                if (entity.getHealth() > 0)
                    playersDead.remove(entity);
            } else {
                if (entity.getHealth() == 0) {
                    if (this.thunder.getValue())
                        for (int i = 0; i < numbersThunder.getValue(); i++)
                            mc.world.spawnEntity(new EntityLightningBolt(mc.world, entity.posX, entity.posY, entity.posZ, true));
                    if (this.sound.getValue())
                        for (int i = 0; i < numberSound.getValue(); i++)
                            mc.player.playSound(SoundEvents.ENTITY_LIGHTNING_THUNDER, 0.5f, 1.f);
                    playersDead.add(entity);
                }
            }
        });
    }
}