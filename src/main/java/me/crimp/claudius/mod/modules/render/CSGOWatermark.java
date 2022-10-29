package me.crimp.claudius.mod.modules.render;

import me.crimp.claudius.Claudius;
import me.crimp.claudius.event.events.Render2DEvent;
import me.crimp.claudius.mod.modules.Module;
import me.crimp.claudius.mod.setting.Setting;
import me.crimp.claudius.utils.ColorUtil;
import me.crimp.claudius.utils.RenderUtil;
import me.crimp.claudius.utils.Timer;

public class CSGOWatermark extends Module {

    Timer delayTimer = new Timer();
    public Setting<Integer> X = this.register(new Setting("WatermarkX", 10, 0, 1200));
    public Setting<Integer> Y = this.register(new Setting("WatermarkY", 10, 0, 1200));
    public float hue;
    public int red = 1;
    public int green = 1;
    public int blue = 1;

    private String message = "";
    public CSGOWatermark() {
        super("CSGOWatermark", "CSGOey", Category.Render, true, false, false);
    }

    @Override
    public void onRender2D ( Render2DEvent event ) {
        drawCsgoWatermark();
    }

    public void drawCsgoWatermark () {
        int padding = 5;
        message = "Claudius | " + mc.player.getName() + " | " + Claudius.serverManager.getPing() + "ms";
        Integer textWidth = mc.fontRenderer.getStringWidth(message); // taken from wurst+ 3
        Integer textHeight = mc.fontRenderer.FONT_HEIGHT; // taken from wurst+ 3

        //RenderUtil.drawRectangleCorrectly(X.getValue() - 4, Y.getValue() - 4, textWidth + 14, textHeight + 10, ColorUtil.toRGBA(22, 22, 22, 255));
        RenderUtil.drawRectangleCorrectly(X.getValue(), Y.getValue(), textWidth + 4, textHeight + 4, ColorUtil.toRGBA(0, 0, 0, 255));
        RenderUtil.drawRectangleCorrectly(X.getValue(), Y.getValue(), textWidth + 8, textHeight + 4, ColorUtil.toRGBA(0, 0, 0, 255)); // inside
        Claudius.textManager.drawString(message, X.getValue() + 3, Y.getValue() + 3, ColorUtil.toRGBA(255, 255, 255, 255), false); // text
    }
}