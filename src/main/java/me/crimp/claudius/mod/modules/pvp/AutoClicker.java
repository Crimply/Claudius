package me.crimp.claudius.mod.modules.pvp;

import me.crimp.claudius.mod.modules.Module;
import me.crimp.claudius.mod.setting.Setting;
import net.minecraft.client.settings.KeyBinding;
import org.lwjgl.input.Mouse;

public class AutoClicker extends Module {
    private long lastClick;
    private long hold;

    private double speed;
    private double holdLength;
    public Setting<Boolean> Always = this.register(new Setting<>("Alwasys oin", true));

    public AutoClicker() {
        super("AutoClicker", "TestDesc", Category.EXPLOIT, true, false, false);
    }


    public void onUpdate() {
        if(Mouse.isButtonDown(0) || this.Always.getValue()) {
            if(System.currentTimeMillis() - lastClick > speed * 1000) {
                lastClick = System.currentTimeMillis();
                if(hold < lastClick) {
                    hold = lastClick;
                }
                int key = mc.gameSettings.keyBindAttack.getKeyCode();
                KeyBinding.setKeyBindState(key, true);
                KeyBinding.onTick(key);
            } else if (System.currentTimeMillis() - hold > holdLength * 1000) {
                KeyBinding.setKeyBindState(mc.gameSettings.keyBindAttack.getKeyCode(), false);
            }
        }
    }

}

