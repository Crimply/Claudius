package me.crimp.claudius.mod.modules.render;

import me.crimp.claudius.mod.modules.Module;
import me.crimp.claudius.mod.setting.Setting;
import net.minecraftforge.client.event.RenderPlayerEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class Wireframe
        extends Module {
    public static Wireframe INSTANCE = new Wireframe();

    public final Setting<Float> lineWidth = this.register(new Setting<Float>("PLineWidth", Float.valueOf(1.0f), Float.valueOf(0.1f), Float.valueOf(3.0f)));
    public Setting<Boolean> globalcolour = this.register(new Setting<Boolean>("Gui Colour", Boolean.FALSE));
    public Setting<Boolean> players = this.register(new Setting<Boolean>("Players", Boolean.FALSE));
    public Setting<Boolean> playerModel = this.register(new Setting<Boolean>("PlayerModel", Boolean.FALSE));
    public final Setting<Integer> red = this.register(new Setting<>("red", 255, 0, 255));
    public final Setting<Integer> green = this.register(new Setting<>("green", 255, 0, 255));
    public final Setting<Integer> blue = this.register(new Setting<>("blue", 255, 0, 255));
    public final Setting<Float> alpha = this.register(new Setting<Float>("PAlpha", Float.valueOf(255.0f), Float.valueOf(0.1f), Float.valueOf(255.0f)));
    public Wireframe() {
        super("Wireframe", "Draws a wireframe esp around other players.", Category.Render, false, false);
    }

    @SubscribeEvent
    public void onRenderPlayerEvent(RenderPlayerEvent.Pre event) {
        event.getEntityPlayer().hurtTime = 0;
    }

}