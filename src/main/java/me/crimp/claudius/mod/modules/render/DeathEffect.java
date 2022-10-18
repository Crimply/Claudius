package me.crimp.claudius.mod.modules.render;

import me.crimp.claudius.mod.modules.Module;
import me.crimp.claudius.utils.Globals;
import net.minecraft.entity.effect.EntityLightningBolt;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.util.SoundCategory;


public class DeathEffect extends Module {
    private static final DeathEffect INSTANCE = new DeathEffect();

    public DeathEffect() {
        super("DeathEffect", "DeathEffect", Category.RENDER, true, false, false);
    }

    public static DeathEffect getInstance() {
        return INSTANCE;
    }

    public void onDeath(EntityPlayer player) {
        if (this.enabled.getValue()) {
            final EntityLightningBolt bolt = new EntityLightningBolt(Globals.mc.world, Double.longBitsToDouble(Double.doubleToLongBits(2.700619365101586E307) ^ 0x7FC33AA2E6830ED7L), Double.longBitsToDouble(Double.doubleToLongBits(4.288545480809007E306) ^ 0x7F986DA963B0A5BFL), Double.longBitsToDouble(Double.doubleToLongBits(3.3865723560928404E307) ^ 0x7FC81CFA62BC4207L), false);
            Globals.mc.world.playSound(player.getPosition(), SoundEvents.ENTITY_LIGHTNING_THUNDER, SoundCategory.WEATHER, Float.intBitsToFloat(Float.floatToIntBits(13.150525f) ^ 0x7ED2688D), Float.intBitsToFloat(Float.floatToIntBits(10.325938f) ^ 0x7EA5370B), false);
            bolt.setLocationAndAngles(player.posX, player.posY, player.posZ, Float.intBitsToFloat(Float.floatToIntBits(3.2116163E38f) ^ 0x7F719D7B), Float.intBitsToFloat(Float.floatToIntBits(2.2278233E38f) ^ 0x7F279A51));
            Globals.mc.world.spawnEntity(bolt);
        }
    }
}
