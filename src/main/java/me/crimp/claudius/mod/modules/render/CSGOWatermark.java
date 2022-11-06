package me.crimp.claudius.mod.modules.render;

import com.mojang.realmsclient.gui.ChatFormatting;
import me.crimp.claudius.Claudius;
import me.crimp.claudius.event.events.Render2DEvent;
import me.crimp.claudius.mod.modules.Module;
import me.crimp.claudius.mod.setting.Setting;
import me.crimp.claudius.utils.ColorUtil;
import me.crimp.claudius.utils.RenderUtil;

public class CSGOWatermark extends Module {
    public Setting<Integer> X = this.register(new Setting("X", 10, 0, 1200));
    public Setting<Integer> Y = this.register(new Setting("Y", 10, 0, 1200));
    public Setting<Boolean> Name = this.register(new Setting("Name", true));
    public Setting<Boolean> Ping = this.register(new Setting("Ping", true));
    public Setting<Boolean> Config = this.register(new Setting("Config", false));
    //public Setting<Boolean> Fps = this.register(new Setting("Fps", false));
    public Setting<Boolean> Ip = this.register(new Setting("Ip", false));

    private String message = "";

    public CSGOWatermark() {
        super("CSGOWatermark", "CSGOey", Category.Render, true, false, false);
    }

    @Override
    public void onRender2D(Render2DEvent event) {
        drawCsgoWatermark();
    }

    public void drawCsgoWatermark() {
        message = ChatFormatting.DARK_AQUA + "Cdus" + ChatFormatting.RESET;
        if (Name.getValue()) {
            message = message + mc.player.getName();
        }
        if (Ping.getValue()) {
            message = message + " " + Claudius.serverManager.getPing() + "ms";
        }
        if (Config.getValue()) {
            message = message + " " + Claudius.configManager.CurrentConfig();
        }
        if (Ip.getValue()) {
            if (!mc.isSingleplayer()) {
                String serverIp = mc.currentServerData.serverIP;
                message = message + " " + serverIp + ":" + mc.serverPort;
            } else {
                message = message + " Singleplayer";
            }
        }

        int Width = mc.fontRenderer.getStringWidth(message);
        int Height = mc.fontRenderer.FONT_HEIGHT;


        RenderUtil.drawRectangleCorrectly(X.getValue(), Y.getValue(), Width + 8, Height+4, ColorUtil.toRGBA(0, 0, 0, 255));
        Claudius.textManager.drawString(message, X.getValue() + 3, Y.getValue() + 1, ColorUtil.toRGBA(255, 255, 255, 255),false);
    }
}