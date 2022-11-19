package me.crimp.claudius.mod.modules.render;

import com.mojang.realmsclient.gui.ChatFormatting;
import me.crimp.claudius.Claudius;
import me.crimp.claudius.event.events.Render2DEvent;
import me.crimp.claudius.mod.modules.Module;
import me.crimp.claudius.mod.modules.text.BetterWatermark;
import me.crimp.claudius.mod.modules.text.PopCounter;
import me.crimp.claudius.mod.setting.Setting;
import me.crimp.claudius.utils.ColorUtil;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import java.math.BigDecimal;

public class PlayerRadar extends Module {
    private final Setting<Integer> amount = register(new Setting<>("PlayerCount", 10, 1,100));
    public Setting<Integer> X = register(new Setting<>("X", 10, 0, 950));
    public Setting<Integer> Y = register(new Setting<>("Y", 10, 0, 550));

    public PlayerRadar() {
        super("PlayerRadar", "Shows players in render distance on hud", Category.Render, false, false);
    }
    public static PlayerRadar INSTANCE = new PlayerRadar();

    public static float round(float d, int decimalPlace) {
        BigDecimal bd = new BigDecimal(Float.toString(d));
        bd = bd.setScale(decimalPlace, BigDecimal.ROUND_HALF_UP);
        return bd.floatValue();
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
                    heal = " " + ChatFormatting.GREEN + round(health, 1) + "";
                } else if (health >= 4.0) {
                    heal = " " + ChatFormatting.YELLOW + round(health, 1) + "";
                } else {
                    heal = " " + ChatFormatting.RED + round(health, 1) + "";
                }

                String name = entity.getGameProfile().getName();
                String str = name + heal  + ChatFormatting.RESET;

                if (Claudius.enemyManager.isEnemy(entity.getName()))
                    Claudius.textManager.drawString(ChatFormatting.RED + str, X.getValue() + 5, Y.getValue() + i * 10, ColorUtil.toRGBA(255, 255, 255, 255), false);
                else
                    Claudius.textManager.drawString(str, X.getValue() + 5, Y.getValue() + i * 10, ColorUtil.toRGBA(255, 255, 255, 255), false);


                if (Claudius.friendManager.isFriend(entity.getName()))
                    Claudius.textManager.drawString(ChatFormatting.AQUA + str, X.getValue() + 5, Y.getValue() + i * 10, ColorUtil.toRGBA(255, 255, 255, 255), false);
                else
                    Claudius.textManager.drawString(str, X.getValue() + 5, Y.getValue() + i * 10, ColorUtil.toRGBA(255, 255, 255, 255), false);


            }
        }
    }
}
