package me.crimp.claudius.mod.modules.render;

import com.mojang.realmsclient.gui.ChatFormatting;
import me.crimp.claudius.Claudius;
import me.crimp.claudius.event.events.Render2DEvent;
import me.crimp.claudius.mod.modules.Module;
import me.crimp.claudius.mod.setting.Setting;
import me.crimp.claudius.utils.ColorUtil;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;

public class PlayerRadar extends Module {
    private final Setting<Integer> amount = register(new Setting<>("RenderingUp", 10, 1,100));

    public PlayerRadar() {
        super("PlayerRadar", "Shows players in render distance on hud", Category.Render, true, false, false);
    }

    @Override
    public void onRender2D(Render2DEvent event) {
        int i = 0;
        for (Object o : mc.world.loadedEntityList) {
            if (o instanceof EntityPlayer && o != Minecraft.getMinecraft().player) {
                i++;
                if (i > amount.getValue()) return;
                EntityPlayer entity = (EntityPlayer) o;
                float health = entity.getHealth() + entity.getAbsorptionAmount();

                String heal;
                if (health >= 12.0) {
                    heal = " \u00a72" + health + "";
                } else if (health >= 4.0) {
                    heal = " \u00a76" + health  + "";
                } else {
                    heal = " \u00a74" + health+ " ";
                }

                String name = entity.getGameProfile().getName();
                String str = "\n"+ name + heal + ChatFormatting.RESET + " " + 0;

                Claudius.textManager.drawString(str ,10,10, ColorUtil.toRGBA(255, 255, 255, 255), false);
            }
        }
    }
}
