package me.crimp.claudius.mod.modules.render;

import me.crimp.claudius.mod.modules.Module;
import me.crimp.claudius.mod.setting.Setting;
import me.crimp.claudius.utils.Timer;


public class strichcode extends Module {
    public Setting<Integer> Ampl = this.register(new Setting<>("Amplifier", -999, 0, 999));
    public strichcode() {
        super("strichcode", "strichcode", Category.Render, true, false, false);
    }
    Timer timer = new Timer();
    int apl = Ampl.getValue() * 10000;

    @Override
    public void onUpdate() {
        //mc.player.addPotionEffect(new PotionEffect(MobEffects.HASTE, 0,Ampl.getValue()));
        if (mc.player.swingProgressInt == 1) {
            int swingcount = 0;
            timer.reset();
            timer.passedMs(apl);
            mc.player.swingProgressInt = ++swingcount;
        }
    }
}


//    @Override
//    public void onUpdate() {
//        mc.player.addPotionEffect(new PotionEffect(MobEffects.MINING_FATIGUE, 0,Ampl.getValue()));
//        mc.player.clearActivePotions();
//    }