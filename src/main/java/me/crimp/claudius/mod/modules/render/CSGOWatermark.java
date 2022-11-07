package me.crimp.claudius.mod.modules.render;

import com.mojang.realmsclient.gui.ChatFormatting;
import me.crimp.claudius.Claudius;
import me.crimp.claudius.event.events.Render2DEvent;
import me.crimp.claudius.mod.modules.Module;
import me.crimp.claudius.mod.setting.Setting;
import me.crimp.claudius.utils.ColorUtil;
import me.crimp.claudius.utils.RenderUtil;
import net.minecraft.client.Minecraft;

public class CSGOWatermark extends Module {
    private final Setting<Snap> SnapMode = this.register(new Setting<Snap>("Snap", Snap.Off));
    public Setting<Integer> X = this.register(new Setting("X", 10, 0, 1200));
    public Setting<Integer> Y = this.register(new Setting("Y", 10, 0, 1200));
    public Setting<Boolean> Name = this.register(new Setting("Name", true));
    public Setting<Boolean> Ping = this.register(new Setting("Ping", true));
    public Setting<Boolean> Config = this.register(new Setting("Config", false));
    public Setting<Boolean> Fps = this.register(new Setting("Fps", false));
    public Setting<Boolean> Ip = this.register(new Setting("Ip", false));

    private String message = "";

    public CSGOWatermark() {
        super("Watermark", "CSGOey", Category.Render, true, false, false);
    }

    private enum Snap {
        TopLeft, TopRight, BottemLeft, BottemRight, Off
    }
    @Override
    public void onRender2D(Render2DEvent event) {
        drawCsgoWatermark();
    }

    public void drawCsgoWatermark() {
        message = ChatFormatting.DARK_AQUA + "Cdus " + ChatFormatting.RESET;
        if (Name.getValue()) {
            message = message + mc.player.getName();
        }
        if (Ping.getValue()) {
            message = message + " Ping:" + Claudius.serverManager.getPing() + "ms";
        }
        if (Config.getValue()) {
            message = message + " Cfg:" + Claudius.configManager.CurrentConfig();
        }
        if (Fps.getValue()) {
            message = message + " Fps:" + Minecraft.getDebugFPS();
        }
        if (Ip.getValue()) {
            if (!mc.isSingleplayer()) {
                String serverIp = mc.currentServerData.serverIP;
                message = message + " IP:" + serverIp + ":" + mc.serverPort;
            } else {
                message = message + " IP:Singleplayer";
            }
        }

        int math = mc.fontRenderer.getStringWidth(message) / 2;
        if (SnapMode.getValue().equals(Snap.Off)) {
            RenderUtil.drawRectangleCorrectly(X.getValue(), Y.getValue(), mc.fontRenderer.FONT_HEIGHT + 8, mc.fontRenderer.getStringWidth(message) + 4, ColorUtil.toRGBA(0, 0, 0, 255));
            Claudius.textManager.drawString(message, X.getValue() + 3, Y.getValue() + 1, ColorUtil.toRGBA(255, 255, 255, 255), false);
        } else if (SnapMode.getValue().equals(Snap.TopLeft)) {
            RenderUtil.drawRectangleCorrectly(10, 10, mc.fontRenderer.FONT_HEIGHT + 8, mc.fontRenderer.getStringWidth(message) + 4, ColorUtil.toRGBA(0, 0, 0, 255));
            Claudius.textManager.drawString(message, 10 + 3, 1 + 1, ColorUtil.toRGBA(255, 255, 255, 255), false);
        } else if (SnapMode.getValue().equals(Snap.TopRight)) {
            RenderUtil.drawRectangleCorrectly(10, 10, mc.fontRenderer.FONT_HEIGHT + 8, mc.fontRenderer.getStringWidth(message) + 4, ColorUtil.toRGBA(0, 0, 0, 255));
            Claudius.textManager.drawString(message, 640 -math + 3, 1 + 1, ColorUtil.toRGBA(255, 255, 255, 255), false);
        } else if (SnapMode.getValue().equals(Snap.BottemLeft)) {
            RenderUtil.drawRectangleCorrectly(10, 10, mc.fontRenderer.FONT_HEIGHT + 8, mc.fontRenderer.getStringWidth(message) + 4, ColorUtil.toRGBA(0, 0, 0, 255));
            Claudius.textManager.drawString(message, 1 + 3, 530 + 1, ColorUtil.toRGBA(255, 255, 255, 255), false);
        } else if (SnapMode.getValue().equals(Snap.BottemRight)) {
            RenderUtil.drawRectangleCorrectly(10, 10, mc.fontRenderer.FONT_HEIGHT + 8, mc.fontRenderer.getStringWidth(message) + 4, ColorUtil.toRGBA(0, 0, 0, 255));
            Claudius.textManager.drawString(message, 640 -math + 3, 530 + 1, ColorUtil.toRGBA(255, 255, 255, 255), false);
        }
    }
}