package me.crimp.claudius.mod.modules.render;

import me.crimp.claudius.Claudius;
import me.crimp.claudius.event.events.Render2DEvent;
import me.crimp.claudius.mod.modules.Module;
import me.crimp.claudius.utils.ColorUtil;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.client.event.RenderWorldLastEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class playerlist extends Module {
    public playerlist() {
        super("playerlist", "", Category.Render, true, false, false);}

        public String a = null;
        @SubscribeEvent
        public void onWorldRender (RenderWorldLastEvent event){
            if (!fullNullCheck()) return;
            for (final EntityPlayer p : mc.world.playerEntities) {
                if (p != mc.getRenderViewEntity() && p.isEntityAlive()) {
                    if (p.getName().startsWith("Body #")) {
                        continue;
                    }
                    a = p + " \n";
                }
            }
        }
    @Override
    public void onRender2D(Render2DEvent event) {
        drawList();
    }


    public void drawList() {
        Claudius.textManager.drawString(a, 10,10, ColorUtil.toRGBA(255, 255, 255, 255), false);
    }
}
