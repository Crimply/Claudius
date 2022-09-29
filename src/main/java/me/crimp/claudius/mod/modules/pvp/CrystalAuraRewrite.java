package me.crimp.claudius.mod.modules.pvp;

import me.crimp.claudius.Claudius;
import me.crimp.claudius.event.events.UpdateWalkingPlayerEvent;
import me.crimp.claudius.mod.modules.Module;
import me.crimp.claudius.mod.setting.Setting;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.util.EnumHand;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class CrystalAuraRewrite extends Module {

    public Setting<Boolean> Place = this.register(new Setting<>("Place", true));
    public Setting<Boolean> Break = this.register(new Setting<>("Break", true));
    public Setting<Boolean> GappPause = this.register(new Setting<>("GappPause", false));
    public Setting<Float> PlayerRange = this.register(new Setting<>("PlayerRange", 5f,1f,10f));
    public Setting<Float> PlaceDelay = this.register(new Setting<>("PlaceDelay", 0f,0f,20f));
    public Setting<Float> BreakSpeed = this.register(new Setting<>("BreakSpeed", 20f,0f,20f));

    public CrystalAuraRewrite() {
        super("CrystalAura", "CrystalAura", Category.PVP, true, false, false);
    }

    private int hitTicks;
    private int placeTicks;
    private EntityPlayer target;



    @SubscribeEvent
    public void onWalk (UpdateWalkingPlayerEvent event) {
        doCrystal();
        hitTicks ++;
        placeTicks ++;
    }



    private void doCrystal() {
        if (!fullNullCheck() || this.GappPause.getValue() && mc.player.getHeldItem(EnumHand.MAIN_HAND).getItem().equals(Items.GOLDEN_APPLE)) return;
        target = GetPlayer();
        if (target == null) return;
        if (this.Place.getValue() && placeTicks > this.PlaceDelay.getValue()) {
            //placecrystal
        }
        if (this.Break.getValue() && hitTicks > this.BreakSpeed.getValue()) {
            //breakcrystal
        }
    }

    private EntityPlayer GetPlayer() {
        EntityPlayer playerTarget = null;
        for (EntityPlayer player: mc.world.playerEntities) {
            if (Claudius.friendManager.isFriend(player)) continue;
            if (player.getName() == mc.player.getName()) continue;
            if (player.getDistanceSq(mc.player) > this.PlayerRange.getValue() * this.PlayerRange.getValue()) continue;
            if (player.isDead) continue;
            if (playerTarget != null) {
                if (player.getDistance(player) > player.getDistance(playerTarget)) continue;
            }
            playerTarget = player;
        }
        return playerTarget;
    }
}

