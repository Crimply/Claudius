package me.crimp.claudius.mod.modules.render;

import akka.dispatch.CachingConfig;
import me.crimp.claudius.mod.modules.Module;
import me.crimp.claudius.mod.setting.Setting;
import me.crimp.claudius.utils.RenderUtil;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.client.event.RenderWorldLastEvent;
import org.lwjgl.opengl.GL11;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class BurrowEsp extends Module {
    private final Setting<Boolean> renderOwn = this.register(new Setting<>("Render Own", true));
    private final Setting<Integer> range = this.register(new Setting<>("Range", 1, 5, 10));
    private final Setting<Boolean> renderBlock = this.register(new Setting<>("Render", true));
    private final Setting<Integer> outlineWidth = this.register(new Setting<>("Outline Width", 1, 2, 5));
    private final Setting<RenderModes> renderMode = this.register(new Setting<>("Render Mode", RenderModes.Full));
   // private final Setting<Color> renderColour = this.register(new Setting<>("Render Colour", new Color(91, 79, 208, 220)));
     //public final Property<RenderModes> renderMode = new Property<>(this, "Render Mode", "The type of box to render", RenderModes.Full);
     //public final Property<Color> renderColour = new Property<>(this, "Render Colour", "The colour for the burrow blocks", new Color(91, 79, 208, 220));
    public BurrowEsp() {
        super("BurrowEsp", "BurrowEsp", Category.RENDER, true, false, false);
    }

    private final List<BlockPos> burrowBlocksList = new ArrayList<>();

    @Override
    public void onEnable() {
        if (nullCheck()) return;

        burrowBlocksList.clear();
    }

    @Override
    public void onDisable() {
        if (nullCheck()) return;

        burrowBlocksList.clear();
    }

    public void onUpdate() {
        if (nullCheck()) return;

        burrowBlocksList.clear();

        for (EntityPlayer entityPlayer : mc.world.playerEntities) {
            if (entityPlayer.getDistance(mc.player) <= range.getValue()) {
                if (entityPlayer == mc.player && renderOwn.getValue()) {
                    if (mc.world.getBlockState(new BlockPos(mc.player.posX, mc.player.posY, mc.player.posZ)).getBlock() == Blocks.OBSIDIAN) {
                        burrowBlocksList.add(new BlockPos(mc.player.posX, mc.player.posY, mc.player.posZ));
                    }
                } else {
                    if (entityPlayer != mc.player && !renderOwn.getValue()) {
                        if (mc.world.getBlockState(new BlockPos(entityPlayer.posX, entityPlayer.posY, entityPlayer.posZ)).getBlock() == Blocks.OBSIDIAN) {
                            burrowBlocksList.add(new BlockPos(entityPlayer.posX, entityPlayer.posY, entityPlayer.posZ));
                        }
                    }
                }
            }
        }
    }

    public void onRenderWorldLast(RenderWorldLastEvent event) {
        if (nullCheck()) return;

        if (renderBlock.getValue()) {
            for (BlockPos burrowBlock : burrowBlocksList) {
                if (burrowBlock != null) {
                    GL11.glLineWidth(outlineWidth.getValue().floatValue());
                    //RenderUtil.blockESP(burrowBlock, renderMode.getValue() != RenderModes.Outline, renderMode.getValue() != RenderModes.Box, 0, 0, renderColour.getValue());
                }
            }
        }
    }

    public enum RenderModes {
        Box,
        Outline,
        Full
    }
}
