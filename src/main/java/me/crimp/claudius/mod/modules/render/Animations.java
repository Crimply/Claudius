package me.crimp.claudius.mod.modules.render;

import me.crimp.claudius.event.events.Render3DEvent;
import me.crimp.claudius.mod.modules.Module;
import me.crimp.claudius.mod.setting.Setting;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityEnderCrystal;
import net.minecraft.entity.player.EntityPlayer;

public class Animations extends Module {

    public final Setting<Boolean> crouch = register(new Setting<>("EveryoneCrouches", true));
    public final Setting<Boolean> Glowing = register(new Setting<>("EveryoneGlows", true));
    public final Setting<Boolean> noCrystalRotation = register(new Setting<>("NoCrystalMove", false));
    public static Animations INSTANCE;

    public Animations() {
        super("Animations", "Allows for custom animations", Category.Render, false, false);
        INSTANCE = this;
    }

    @Override
    public void onUpdate() {
        if (crouch.getValue()) {
            for (EntityPlayer player : mc.world.playerEntities) {
                if (player.equals(mc.player)) continue;
                player.setSneaking(true);
            }
        }
        if (Glowing.getValue()) {
            for (EntityPlayer player : mc.world.playerEntities) {
                if (player.equals(mc.player)) continue;
                player.setGlowing(true);
            }
        }
    }

    @Override
    public void onRender3D(Render3DEvent event) {
        if (noCrystalRotation.getValue()) {
            for (Entity entity : mc.world.loadedEntityList) {
                if (!(entity instanceof EntityEnderCrystal)) continue;
                EntityEnderCrystal crystal = (EntityEnderCrystal) entity;
                crystal.rotationPitch = 0;
                crystal.rotationYaw = 0;
                crystal.prevRotationPitch = 0;
                crystal.prevRotationYaw = 0;
                crystal.innerRotation = 0;
            }
        }
    }
}
