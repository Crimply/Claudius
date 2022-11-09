package me.crimp.claudius.mod.modules.render;

import me.crimp.claudius.mod.modules.Module;
import me.crimp.claudius.mod.setting.Setting;
import me.crimp.claudius.utils.Timer;


public class strichcode extends Module {
    public Setting<Integer> Ampl = this.register(new Setting<>("Amplifier", 1, 0, 15));
    public strichcode() {
        super("strichcode", "strichcode", Category.Render, true, false, false);
    }
    Timer timer = new Timer();
    int apl = Ampl.getValue() / 5;

    @Override
    public void onUpdate() {
        if (mc.player.swingProgressInt == 1) {
            timer.reset();
            mc.player.swingProgressInt = 1;
            timer.hasReached(apl);
            mc.player.swingProgressInt = 2;
            timer.hasReached(apl);
            mc.player.swingProgressInt = 3;
            timer.hasReached(apl);
            mc.player.swingProgressInt = 4;
            timer.hasReached(apl);
            mc.player.swingProgressInt = 5;
            timer.hasReached(apl);
        }
    }
}


//    @Override
//    public void onUpdate() {
//        mc.player.addPotionEffect(new PotionEffect(MobEffects.MINING_FATIGUE, 0,Ampl.getValue()));
//        mc.player.clearActivePotions();
//    }