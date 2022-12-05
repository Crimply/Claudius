package me.crimp.claudius.mod.modules.render;

import me.crimp.claudius.mod.modules.Module;
import me.crimp.claudius.mod.setting.Setting;
import net.minecraftforge.client.event.RenderPlayerEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class Wireframe extends Module {
    public final Setting<Float> alpha = register(new Setting<Float>("PAlpha", 255.0f, 0.1f, 255.0f));
    public final Setting<Float> lineWidth = this.register(new Setting<Float>("PLineWidth", 1.0f, 0.1f, 3.0f));
    public Setting<RenderMode> mode = this.register(new Setting<RenderMode>("PMode", RenderMode.SOLID));
    public Setting<Boolean> players = this.register(new Setting<Boolean>("Players", Boolean.FALSE));
    public Setting<Boolean> playerModel = this.register(new Setting<Boolean>("PlayerModel", Boolean.FALSE));

    public Wireframe() {
        super("Wireframe", "Draws a wireframe esp around other players.", Category.Render, false, false);
    }

    public static Wireframe INSTANCE = new Wireframe();

    @SubscribeEvent
    public void onRenderPlayerEvent(RenderPlayerEvent.Pre event) {
        event.getEntityPlayer().hurtTime = 0;
    }

    public enum RenderMode {
        SOLID,
        WIREFRAME

    }
}
