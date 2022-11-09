package me.crimp.claudius.mod.modules.render;

import me.crimp.claudius.Claudius;
import me.crimp.claudius.event.events.EntitySpawnEvent;
import me.crimp.claudius.event.events.Render2DEvent;
import me.crimp.claudius.mod.modules.Module;
import me.crimp.claudius.utils.ColorUtil;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.util.ArrayList;
import java.util.List;

public class playerlist extends Module {
    public playerlist() {
        super("playerlist", "", Category.Render, true, false, false);}

    public String name = "null";
    private final List<String> Players = new ArrayList<>();


    @Override
    public void onUpdate() {
        mc.world.playerEntities.forEach(entity -> {
            Players.add(String.valueOf(entity));
        });
    }

    @SubscribeEvent
    public void onEntitySpawn(EntitySpawnEvent event) {
        if (fullNullCheck()) return;
        if (event.getEntity() instanceof EntityPlayer ) {
            if (event.getType().equals(EntitySpawnEvent.Type.Despawn)) {
                Players.clear();
            }
        }
    }


    @Override
    public void onRender2D(Render2DEvent event) {
        drawList();
    }


    public void drawList() {
        Claudius.textManager.drawString(Players.toString() ,10,10, ColorUtil.toRGBA(255, 255, 255, 255), false);
    }
}
