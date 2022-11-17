package me.crimp.claudius.mod.modules.render;

import me.crimp.claudius.event.events.PacketEvent;
import me.crimp.claudius.event.events.Render3DEvent;
import me.crimp.claudius.mixins.mixins.ICPacketAnimation;
import me.crimp.claudius.mod.modules.Module;
import me.crimp.claudius.mod.setting.Setting;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityEnderCrystal;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.network.play.client.CPacketAnimation;
import net.minecraft.util.EnumHand;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class Animations extends Module {

    private final Setting<Mode> mode = register(new Setting<>("Mode", Mode.CANCEL));
    public final Setting<Boolean> crouch = register(new Setting<>("EveryoneCrouches", true));
    public final Setting<Boolean> noCrystalRotation = register(new Setting<>("NoCrystalMove", false));
    public static Animations INSTANCE;

    private enum Mode {
        CANCEL//, OFFHAND, MAINHAND, OPPOSITE, NONE
    }

    public Animations() {
        super("Animations", "Allows for custom animations", Category.Render,false,false);
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
        if (this.mode.getValue() == Mode.CANCEL) {
            mc.player.swingProgressInt = -1;
        }
       // mc.player.getActivePotionEffect(MobEffects.).getAmplifier();
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

    @SubscribeEvent
    public void onPacketSent(PacketEvent.Send event) {
        if(mc.player == null || mc.world == null) return;
        if(event.getPacket() instanceof CPacketAnimation) {
//            } else if (mode.getValue() == Mode.OFFHAND) {
//                CPacketAnimation packet = (CPacketAnimation) event.getPacket();
//                ((ICPacketAnimation) packet).setHand(EnumHand.OFF_HAND);
//            } else if (mode.getValue() == Mode.MAINHAND) {
//                CPacketAnimation packet = (CPacketAnimation) event.getPacket();
//                ((ICPacketAnimation) packet).setHand(EnumHand.MAIN_HAND);
//            } else if (mode.getValue() == Mode.OPPOSITE) {
//                CPacketAnimation packet = (CPacketAnimation) event.getPacket();
//                ((ICPacketAnimation) packet).setHand(packet.getHand() == EnumHand.MAIN_HAND ? EnumHand.OFF_HAND : EnumHand.MAIN_HAND);
            }
        }
    }
