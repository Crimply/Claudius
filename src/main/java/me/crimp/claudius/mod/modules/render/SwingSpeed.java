package me.crimp.claudius.mod.modules.render;

import me.crimp.claudius.Claudius;
import me.crimp.claudius.mod.command.Command;
import me.crimp.claudius.mod.modules.Module;
import me.crimp.claudius.mod.setting.Setting;

public class SwingSpeed extends Module {
    // public Setting<Integer> Ampl = this.register(new Setting<>("Amplifier", 10, 0, 50));

    public SwingSpeed() {
        super("SwingSpeed", "strichcode", Category.Render, true, false, false);
    }
    public static SwingSpeed INSTANCE = new SwingSpeed();

//    @Override
//    public void onUpdate() {
////        if (Claudius.moduleManager.isModuleEnabled("SwingSpeed")) {
////            Command.sendOverwriteClientMessage("SwingOn");
//        }
//    }
}


//    @Override
//    public void onUpdate() {
//        mc.player.addPotionEffect(new PotionEffect(MobEffects.MINING_FATIGUE, 0,Ampl.getValue()));
//        mc.player.clearActivePotions();
//    }
//(MobEffects.MINING_FATIGUE).getAmplifier() = 25;




























