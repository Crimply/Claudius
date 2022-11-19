package me.crimp.claudius.mod.modules.render;

import me.crimp.claudius.mod.modules.Module;
import me.crimp.claudius.mod.setting.Setting;
import net.minecraft.network.play.client.CPacketPlayer;

import static me.crimp.claudius.utils.EntityUtil.forward;


public class ClipFly extends Module {
    public final Setting<Mode> flight = register(new Setting<>("Mode", Mode.Clip));
    public final Setting<Integer> packets = register(new Setting<>("Packets", 80, 1, 300));
    public final Setting<Integer> speed = register(new Setting<>("XZ Speed", 7, -99, 99, v -> flight.getValue().equals(Mode.Flight)));
    public final Setting<Integer> speedY = register(new Setting<>("Y Speed", 7, -99, 99, v -> !flight.getValue().equals(Mode.Relative)));
    public final Setting<Boolean> bypass = register(new Setting<>("Bypass", false));
    public final Setting<Integer> interval = register(new Setting<>("Interval", 25, 1, 100, v -> flight.getValue().equals(Mode.Clip)));
    public final Setting<Boolean> update = register(new Setting<>("Update Position Client Side", false));

    public ClipFly() {
        super("ClipFly", "Allows for custom animations", Category.Render, false, false);
    }

    int num = 0;

    private enum Mode {
        Flight, Clip, Relative
    }

    double startFlat = 0;

    public void onEnable() {
        startFlat = mc.player.posY;
        num = 0;
    }

    @Override
    public void onTick() {

        double[] dir = forward(speed.getValue());

        double yposition;

        if (flight.getValue().equals(Mode.Flight)) {
            double xPos = mc.player.posX;
            double yPos = mc.player.posY;
            double zPos = mc.player.posZ;

            if (mc.gameSettings.keyBindJump.isKeyDown() && !mc.gameSettings.keyBindSneak.isKeyDown())
                yPos += speedY.getValue();
            else if (mc.gameSettings.keyBindSneak.isKeyDown())
                yPos -= speedY.getValue();

            xPos += dir[0];
            zPos += dir[1];

            mc.player.connection.sendPacket(new CPacketPlayer.Position(xPos, yPos, zPos, false));
            if (update.getValue())
                mc.player.setPosition(xPos, yPos, zPos);
            if (bypass.getValue())
                mc.player.connection.sendPacket(new CPacketPlayer.Position(mc.player.posX, mc.player.posY + 0.05, mc.player.posZ, true));
        }

        if (flight.getValue().equals(Mode.Clip))

            if (mc.gameSettings.keyBindSprint.isKeyDown() || mc.player.ticksExisted % interval.getValue() == 0) {
                for (int i = 0; i < packets.getValue(); i++) {

                    yposition = mc.player.posY + speedY.getValue();

                    mc.player.connection.sendPacket(new CPacketPlayer.Position(mc.player.posX, yposition, mc.player.posZ, false));
                    if (update.getValue()) mc.player.setPosition(mc.player.posX, yposition, mc.player.posZ);
                    if (bypass.getValue())
                        mc.player.connection.sendPacket(new CPacketPlayer.Position(mc.player.posX, mc.player.posY + 0.05, mc.player.posZ, true));
                }
            }
    }
}