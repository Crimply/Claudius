package me.crimp.claudius.mod.gui.screens;

import me.crimp.claudius.utils.RenderUtil;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.util.ResourceLocation;

import java.awt.*;

public class CSGOGui extends GuiScreen {
    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        mc.getTextureManager().bindTexture(new ResourceLocation("textures/neverLoose.png"));
        RenderUtil.drawRect(20 , 20 , 1000, 1000, new Color(60, 60, 60, 0).getRGB());

    }
}



