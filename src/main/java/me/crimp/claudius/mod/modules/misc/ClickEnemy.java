package me.crimp.claudius.mod.modules.misc;

import me.crimp.claudius.Claudius;
import me.crimp.claudius.mod.modules.Module;
import me.crimp.claudius.mod.setting.Bind;
import me.crimp.claudius.mod.setting.Setting;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.RayTraceResult;
import org.lwjgl.input.Keyboard;

public class ClickEnemy extends Module {
    private boolean clicked = false;
    public Setting<Bind> Keybind = register(new Setting<Bind>("EnemyBind", new Bind(-1)));

    public ClickEnemy() {
        super("ClickEnemy", "ClickEnemy", Category.Misc, false, false);
    }

    @Override
    public void onUpdate() {
        if (Keyboard.isKeyDown(Keybind.getValue().getKey())) {
            if (!clicked && McfSync.mc.currentScreen == null) {
                this.onClick();
            }
            clicked = true;
        } else {
            clicked = false;
        }
    }

    private void onClick() {
        Entity entity;
        RayTraceResult result = McfSync.mc.objectMouseOver;
        if (result != null && result.typeOfHit == RayTraceResult.Type.ENTITY && (entity = result.entityHit) instanceof EntityPlayer) {
            if (Claudius.enemyManager.isEnemy(entity.getName())) {
                Claudius.enemyManager.removeEnemy(entity.getName());
            } else if (!Claudius.enemyManager.isEnemy(entity.getName())) {
                Claudius.enemyManager.addEnemy(entity.getName());
            }
        }
    }
}
