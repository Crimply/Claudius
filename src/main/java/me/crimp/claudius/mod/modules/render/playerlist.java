package me.crimp.claudius.mod.modules.render;

import me.crimp.claudius.Claudius;
import me.crimp.claudius.event.events.Render2DEvent;
import me.crimp.claudius.mod.modules.Module;
import me.crimp.claudius.utils.ColorUtil;
import net.minecraft.entity.player.EntityPlayer;

public class playerlist extends Module {
    public playerlist() {
        super("playerlist", "", Category.Render, true, false, false);}

    public String text = "";

    @Override
    public void onUpdate() {
        for (Object ent : mc.world.playerEntities) {
            if (ent instanceof EntityPlayer) {
                EntityPlayer player = (EntityPlayer) ent;
                if (player.equals(mc.player)) continue;
                text = player.getHealth() + " " + player.getName();
            }
        }
    }



    @Override
    public void onRender2D(Render2DEvent event) {
        Claudius.textManager.drawString(text ,10,10, ColorUtil.toRGBA(255, 255, 255, 255), false);
    }
}
