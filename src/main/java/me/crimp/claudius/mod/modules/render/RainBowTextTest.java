package me.crimp.claudius.mod.modules.render;

import me.crimp.claudius.claudius;
import me.crimp.claudius.event.events.Render2DEvent;
import me.crimp.claudius.mod.modules.Module;
import me.crimp.claudius.utils.ColorUtil;

import java.awt.*;

public class RainBowTextTest extends Module {
    public RainBowTextTest() {
        super("RainBowTextTest", "Terstese", Category.Render, false, false);
    }


    int red;
    int green;
    int blue;
    int textcolor;

    @Override
    public void onUpdate() {
        textcolor = ColorUtil.toARGB(red,green,blue,255);
            doRainbow();
        }

    public void onRender2D(Render2DEvent event) {
        claudius.textManager.drawString("A", 10, 60, textcolor, true);
    }



        public void doRainbow () {
            float[] tick_color = new float[]{(float) (System.currentTimeMillis() % 11520L) / 11520.0f};
            int color_rgb_o = Color.HSBtoRGB(tick_color[0], 0.8f, 0.8f);

            red = color_rgb_o >> 16 & 0xFF;
            green = color_rgb_o >> 8 & 0xFF;
            blue = color_rgb_o & 0xFF;

    }
}