package me.crimp.claudius.mod.modules.client;

import me.crimp.claudius.mod.modules.*;
import me.crimp.claudius.utils.Util;
import net.minecraft.client.*;
import net.minecraft.client.gui.inventory.*;
import net.minecraft.client.gui.*;
import net.minecraftforge.fml.client.*;
import net.minecraft.client.renderer.*;
import net.minecraft.entity.player.*;
import net.minecraft.util.*;

public class GuiBlurr extends Module
{
    final Minecraft mc;

    public GuiBlurr() {
        super("GUIBlur", "", Category.CLIENT, true, false, false);
        this.mc = Minecraft.getMinecraft();
    }

    public void onDisable() {
        if (this.mc.world != null) {
            Util.mc.entityRenderer.getShaderGroup().deleteShaderGroup();
        }
    }

    public void onUpdate() {
        if (this.mc.world != null) {
            if (ClickGuiModule.INSTANCE.isEnabled() || this.mc.currentScreen instanceof GuiContainer || this.mc.currentScreen instanceof GuiChat || this.mc.currentScreen instanceof GuiConfirmOpenLink || this.mc.currentScreen instanceof GuiEditSign || this.mc.currentScreen instanceof GuiGameOver || this.mc.currentScreen instanceof GuiOptions || this.mc.currentScreen instanceof GuiIngameMenu || this.mc.currentScreen instanceof GuiVideoSettings || this.mc.currentScreen instanceof GuiScreenOptionsSounds || this.mc.currentScreen instanceof GuiControls || this.mc.currentScreen instanceof GuiCustomizeSkin || this.mc.currentScreen instanceof GuiModList) {
                if (OpenGlHelper.shadersSupported && Util.mc.getRenderViewEntity() instanceof EntityPlayer) {
                    if (Util.mc.entityRenderer.getShaderGroup() != null) {
                        Util.mc.entityRenderer.getShaderGroup().deleteShaderGroup();
                    }
                    try {
                        Util.mc.entityRenderer.loadShader(new ResourceLocation("shaders/post/blurr.json"));
                    }
                    catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                else if (Util.mc.entityRenderer.getShaderGroup() != null && Util.mc.currentScreen == null) {
                    Util.mc.entityRenderer.getShaderGroup().deleteShaderGroup();
                }
            }
            else if (Util.mc.entityRenderer.getShaderGroup() != null) {
                Util.mc.entityRenderer.getShaderGroup().deleteShaderGroup();
            }
        }
    }
}