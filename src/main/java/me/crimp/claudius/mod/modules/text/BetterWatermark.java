package me.crimp.claudius.mod.modules.text;

import com.mojang.realmsclient.gui.ChatFormatting;
import me.crimp.claudius.Claudius;
import me.crimp.claudius.event.events.Render2DEvent;
import me.crimp.claudius.mod.modules.Module;
import me.crimp.claudius.mod.modules.client.ClickGuiModule;
import me.crimp.claudius.mod.setting.Setting;
import me.crimp.claudius.utils.ColorUtil;
import me.crimp.claudius.utils.RenderUtil;
import net.minecraft.client.Minecraft;


public class BetterWatermark extends Module {

    private final Setting<Snap> SnapMode = register(new Setting<>("Snap", Snap.Off));
    public Setting<Integer> X = register(new Setting<>("X", 10, 0, 1200));
    public Setting<Integer> Y = register(new Setting<>("Y", 10, 0, 1200));
    public Setting<Boolean> Name = register(new Setting<>("PlayerName", true));
    public Setting<Boolean> Ping = register(new Setting<>("Ping", true));
    public Setting<Boolean> Config = register(new Setting<>("Config", false));
    public Setting<Boolean> Fps = register(new Setting<>("Fps", false));
    public Setting<Boolean> Ip = register(new Setting<>("Ip", false));
    public Setting<Boolean> BackGround = register(new Setting<>("BackGround", false));
    public Setting<Boolean> Colour = register(new Setting<>("GuiColour", false));


    public BetterWatermark() {
        super("BetterWatermark", "BetterWatermark", Category.Text, false, false);
    }

    private enum Snap {
        TopLeft, TopRight, BottemLeft, BottemRight, Off
    }

    @Override
    public void onRender2D(Render2DEvent event) {
        drawWatermark();
    }

    int BoxColour;

    public void drawWatermark() {
        String message = ChatFormatting.BOLD+""+ChatFormatting.DARK_AQUA + "Cldus v" + Claudius.MODVER + ChatFormatting.RESET;

        if (Name.getValue()) message = message + " | Name:" +mc.player.getName();
        if (Ping.getValue()) message = message + " | Ping:" + Claudius.serverManager.getPing() + "ms";
        if (Config.getValue()) message = message + " | Cfg:" + Claudius.configManager.CurrentConfig();
        if (Fps.getValue()) message = message + " | Fps:" + Minecraft.getDebugFPS();

        if (Ip.getValue()) {
            if (!mc.isSingleplayer()) {
                String serverIp = mc.currentServerData.serverIP;
                if (mc.serverPort != 0) message = message + " | IP:" + serverIp + ":" + mc.serverPort;else
                    message = message + " | IP:" + serverIp;
            } else message = message + " | IP:Singleplayer";
        }
        if (BackGround.getValue()) {
            if (Colour.getValue())
                BoxColour = ColorUtil.toARGB(ClickGuiModule.INSTANCE.topRed.getValue(), ClickGuiModule.INSTANCE.topGreen.getValue(), ClickGuiModule.INSTANCE.topBlue.getValue(), ClickGuiModule.INSTANCE.topAlpha.getValue());
            else BoxColour = ColorUtil.toRGBA(0, 0, 0, 255);
        }

        int math = mc.fontRenderer.getStringWidth(message) / 2;
        int w = mc.fontRenderer.getStringWidth(message);
        int h = mc.fontRenderer.FONT_HEIGHT;
        int textcolor = ColorUtil.toRGBA(ClickGuiModule.INSTANCE.red.getValue(), ClickGuiModule.INSTANCE.green.getValue(), ClickGuiModule.INSTANCE.blue.getValue());

        if (SnapMode.getValue().equals(Snap.Off)) {
            if (BackGround.getValue()) RenderUtil.drawRectangleCorrectly(X.getValue(), Y.getValue(),  w+ 8, h + 4, BoxColour);
            Claudius.textManager.drawString(message, X.getValue() + 3, Y.getValue() + 3, textcolor, true);
        } else if (SnapMode.getValue().equals(Snap.TopLeft)) {
            if (BackGround.getValue()) RenderUtil.drawRectangleCorrectly(10, 1, w+ 8, h + 4, BoxColour);
            Claudius.textManager.drawString(message, 10 + 3, 1 + 3, textcolor, true);
        } else if (SnapMode.getValue().equals(Snap.TopRight)) {
            if (BackGround.getValue())  RenderUtil.drawRectangleCorrectly(640 +math, 1 + 1, w+ 8, h + 4, BoxColour);
            Claudius.textManager.drawString(message, 640 +math + 3, 1 + 3, textcolor, true);
        } else if (SnapMode.getValue().equals(Snap.BottemLeft)) {
            if (BackGround.getValue()) RenderUtil.drawRectangleCorrectly(1, 530, w+ 8, h + 4, BoxColour);
            Claudius.textManager.drawString(message, 1 + 3, 530 + 3, textcolor, true);
        } else if (SnapMode.getValue().equals(Snap.BottemRight)) {
            if (BackGround.getValue()) RenderUtil.drawRectangleCorrectly(640 +math, 530, w+ 8, h + 4, BoxColour);
            Claudius.textManager.drawString(message, 640 +math + 3, 530 + 3, textcolor, true);
        }
    }
}