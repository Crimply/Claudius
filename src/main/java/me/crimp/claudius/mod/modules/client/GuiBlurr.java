package me.crimp.claudius.mod.modules.client;

import me.crimp.claudius.mod.modules.*;
import me.crimp.claudius.utils.Util;
import net.minecraft.client.*;
import net.minecraft.client.gui.inventory.*;
import net.minecraft.client.gui.*;
import net.minecraftforge.fml.client.*;
import net.minecraft.util.*;

public class GuiBlurr extends Module {
    final Minecraft mc;

    public GuiBlurr() {
        super("GUIBlur", "", Category.Client, true, false, false);
        this.mc = Minecraft.getMinecraft();
    }

    @Override
    public void onEnable() {
        if (this.mc.world != null) {
            if (ClickGuiModule.INSTANCE.isEnabled() || this.mc.currentScreen instanceof GuiContainer || this.mc.currentScreen instanceof GuiChat || this.mc.currentScreen instanceof GuiConfirmOpenLink || this.mc.currentScreen instanceof GuiEditSign || this.mc.currentScreen instanceof GuiGameOver || this.mc.currentScreen instanceof GuiOptions || this.mc.currentScreen instanceof GuiIngameMenu || this.mc.currentScreen instanceof GuiVideoSettings || this.mc.currentScreen instanceof GuiScreenOptionsSounds || this.mc.currentScreen instanceof GuiControls || this.mc.currentScreen instanceof GuiCustomizeSkin || this.mc.currentScreen instanceof GuiModList && this.mc.world != null ) {
                    Util.mc.entityRenderer.loadShader(new ResourceLocation("shaders/post/blurr.json"));
                }
            }
        }

    @Override
    public void onDisable () {
            if (this.mc.world != null) {
                Util.mc.entityRenderer.getShaderGroup().deleteShaderGroup();
            }
        }
    }