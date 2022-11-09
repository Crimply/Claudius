package me.crimp.claudius.mod.modules.render;

import me.crimp.claudius.mod.modules.Module;
import net.minecraft.init.MobEffects;
import net.minecraft.potion.PotionEffect;

public class strichcode extends Module {
    public strichcode() {
        super("strichcode", "strichcode", Category.Render, true, false, false);
    }

    @Override
    public void onUpdate() {
        mc.player.addPotionEffect(new PotionEffect(MobEffects.MINING_FATIGUE, 1));
    }
}
