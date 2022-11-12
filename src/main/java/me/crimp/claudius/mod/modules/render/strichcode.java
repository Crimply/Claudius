package me.crimp.claudius.mod.modules.render;

import me.crimp.claudius.mod.modules.Module;
import me.crimp.claudius.mod.setting.Setting;
import me.crimp.claudius.utils.Timer;


public class strichcode extends Module {
    public Setting<Integer> Ampl = this.register(new Setting<>("Amplifier", -999, 0, 999));
    public strichcode() {
        super("strichcode", "strichcode", Category.Render, true, false, false);
    }

    int delay;
    int swingcount;

    @Override
    public void onUpdate() {
        if (mc.player.swingProgressInt > 1) {
            if (delay > 0) --delay;
            if (delay == 0) {
                delay = Ampl.getValue();
                mc.player.swingProgressInt = ++swingcount;
            }
        }
    }
}


//    @Override
//    public void onUpdate() {
//        mc.player.addPotionEffect(new PotionEffect(MobEffects.MINING_FATIGUE, 0,Ampl.getValue()));
//        mc.player.clearActivePotions();
//    }
//(MobEffects.MINING_FATIGUE).getAmplifier() = 25;




























