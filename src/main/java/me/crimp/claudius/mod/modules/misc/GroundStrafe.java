package me.crimp.claudius.mod.modules.misc;

import me.crimp.claudius.Claudius;
import me.crimp.claudius.event.events.MoveEvent;
import me.crimp.claudius.mod.modules.Module;
import me.crimp.claudius.mod.setting.Setting;
import me.crimp.claudius.utils.EntityUtil;
import me.crimp.claudius.utils.Timer;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
public class GroundStrafe extends Module {

    public  final Setting<Mode> mode = register(new Setting<>("Mode", Mode.New));
    public  final Setting<Double> speed = register(new Setting<Double>("Speed", 0.4, (double) 0, 5.0));
    public  final Setting<Boolean> useTimer = register(new Setting<>("UseTimer", true));
    public  final Setting<Boolean> onGroundOnly = register(new Setting<>("OnGroundOnly", false));
    public  final Setting<Boolean> noLiquid = register(new Setting<>("NoLiquid", true));
    public  GroundStrafe INSTANCE;
    double playerSpeed;
    //public static final Setting<Boolean> smartEnable = new Setting<>("EnableOnStairs", false); //TODO: FIX THIS

    public GroundStrafe() {
        super("GroundStrafe", "Zooooooooooom", Category.MISC,true,false,false);
        INSTANCE = this;
    }
    private final Timer Timer = new Timer();

    @SubscribeEvent
    public void onMove(MoveEvent event) {
        if ((noLiquid.getValue() && mc.player.isInLava() || mc.player.isInWater()) || mc.player.isElytraFlying())
            return;
        if (mode.getValue().equals(Mode.Old)) {
            playerSpeed = speed.getValue();

            playerSpeed *= EntityUtil.getBaseMoveSpeed() / 0.2873;

            if (mc.player.onGround) {
                double[] dir = EntityUtil.forward(playerSpeed);
                event.setX(dir[0]);
                event.setZ(dir[1]);
            }
        }
        if (mode.getValue().equals(Mode.New)) {
            if (mc.player.collidedHorizontally || mc.player.movementInput.sneak || mc.player.moveForward == 0) return;

            if (mc.player.onGround || !onGroundOnly.getValue()) {
                if (mc.player.ticksExisted % 2 == 0) {
                    Claudius.tickManager.setTicks(1.0f);
                } else {
                    if (useTimer.getValue()) {
                        Claudius.tickManager.setTicks(1.2f);
                    } else {
                        Claudius.tickManager.setTicks(1.0f);
                    }
                }
                playerSpeed = EntityUtil.getBaseMoveSpeed();
            }
        } else {
            Claudius.tickManager.setTicks(1.0f);
        }

        playerSpeed = Math.max(playerSpeed, EntityUtil.getBaseMoveSpeed());
        EntityUtil.setVanilaSpeed(event, playerSpeed);

        if (!useTimer.getValue()) {
            Claudius.tickManager.setTicks(1.0f);
        }
    }

    @Override
    public void onDisable() {Claudius.tickManager.setTicks(1.0f);
    }

    public enum Mode {
        Old, New
    }
}
