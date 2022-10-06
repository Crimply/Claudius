package me.crimp.claudius.mod.modules.misc;

import me.crimp.claudius.mod.modules.Module;
import me.crimp.claudius.mod.setting.Setting;

public class RevStep extends Module {
    public static final Setting<Double> speed = new Setting<>("Speed", 1.0, 0.1, 5.0);
    public static final Setting<Double> height = new Setting<>("Height", 2.0, 1.0, 6.0);

    public RevStep() {
        super("ReverseStep", "Teleports you down blocks", Category.MISC,true,false,false);
    }

    @Override
    public void onUpdate() {
        if (mc.player.onGround && !mc.player.isOnLadder() && !mc.player.isInWater() && !mc.player.isInLava()) {
            for (double y = 0.0; y <= height.getValue() + 0.5; y += 0.1) {
                if (!mc.world.getCollisionBoxes(mc.player, mc.player.getEntityBoundingBox().offset(0.0, -y, 0.0)).isEmpty()) {
                    mc.player.motionY -= speed.getValue();
                }
            }
        }
    }
}