package me.crimp.claudius.mod.modules.render;

import me.crimp.claudius.event.events.PacketEvent;
import me.crimp.claudius.event.events.Render3DEvent;
import me.crimp.claudius.mixins.mixins.ICPacketAnimation;
import me.crimp.claudius.mod.modules.Module;
import me.crimp.claudius.mod.setting.Setting;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityEnderCrystal;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.network.play.client.CPacketAnimation;
import net.minecraft.util.EnumHand;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class Animations extends Module {

    private final Setting<Mode> mode = this.register(new Setting<>("Mode", Mode.CANCEL));
    private final Setting<Boolean> strict = this.register(new Setting<>("Strict", false));
    private final Setting<Boolean> render = this.register(new Setting<>("Render", false));
    public final Setting<Boolean> crouch = this.register(new Setting<>("Crouch", true));
    public final Setting<Boolean> noCrystalRotation = this.register(new Setting<>("NoCrystalRotation", false));
    public static Animations INSTANCE;

    private enum Mode {
        CANCEL, OFFHAND, MAINHAND, OPPOSITE, NONE
    }

    public Animations() {
        super("Animations", "Allows for custom animations", Category.Render,true,false,false);
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
            if (this.mode.getValue() == Mode.CANCEL) {
                if (!this.strict.getValue() || mc.playerController.getIsHittingBlock() || mc.player.isSwingInProgress) {
                    event.setCanceled(true);
                }
            } else if (mode.getValue() == Mode.OFFHAND) {
                CPacketAnimation packet = (CPacketAnimation) event.getPacket();
                ((ICPacketAnimation) packet).setHand(EnumHand.OFF_HAND);
            } else if (mode.getValue() == Mode.MAINHAND) {
                CPacketAnimation packet = (CPacketAnimation) event.getPacket();
                ((ICPacketAnimation) packet).setHand(EnumHand.MAIN_HAND);
            } else if (mode.getValue() == Mode.OPPOSITE) {
                CPacketAnimation packet = (CPacketAnimation) event.getPacket();
                ((ICPacketAnimation) packet).setHand(packet.getHand() == EnumHand.MAIN_HAND ? EnumHand.OFF_HAND : EnumHand.MAIN_HAND);
            }

            if (render.getValue()) {
                EnumHand hand = ((CPacketAnimation) event.getPacket()).getHand();
                try {
                    if (!mc.player.isSwingInProgress || mc.player.swingProgressInt >= getArmSwingAnimationEnd() / 2 || mc.player.swingProgressInt < 0) {
                        mc.player.swingProgressInt = -1;
                        mc.player.isSwingInProgress = true;
                        mc.player.swingingHand = hand;
                    }
                } catch (Exception ignored) {

                }
            }
        }
    }

    private int getArmSwingAnimationEnd()
    {
        if (mc.player.isPotionActive(MobEffects.HASTE))
        {
            return 6 - (1 + mc.player.getActivePotionEffect(MobEffects.HASTE).getAmplifier());
        }
        else
        {
            return mc.player.isPotionActive(MobEffects.MINING_FATIGUE) ? 6 + (1 + mc.player.getActivePotionEffect(MobEffects.MINING_FATIGUE).getAmplifier()) * 2 : 6;
        }
    }
}
