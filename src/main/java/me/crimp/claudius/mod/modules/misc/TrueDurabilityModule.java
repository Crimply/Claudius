package me.crimp.claudius.mod.modules.misc;

import me.crimp.claudius.mod.modules.Module;
import me.crimp.claudius.utils.SpecialTagCompound;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.awt.*;
import java.util.List;


public class TrueDurabilityModule extends Module {
  public TrueDurabilityModule() {
    super("TrueDurability", "", Category.MISC, true, false, false);
  }

  @SubscribeEvent
  public void itemToolTip(ItemTooltipEvent event) {
    List<String> toolTip = event.getToolTip();
    ItemStack stack = event.getItemStack();
    int max = stack.getMaxDamage();

    if (stack.isEmpty() || max <= 0) return;
    if (stack.hasTagCompound()) {
      assert stack.getTagCompound() != null;
      if (stack.getTagCompound().getBoolean("Unbreakable")) {
        toolTip.set(7, "Durability");
        toolTip.add("Durability: 99999+");
      }
      return;
    }

    int damage;
    NBTTagCompound tag = stack.getTagCompound();
    if (tag instanceof SpecialTagCompound) {
      damage = ((SpecialTagCompound) tag).getTrueDamage();
    } else damage = stack.getItemDamage();

    long count = (long) max - (long) damage;

    //toolTip.add("");
    toolTip.add("Durability: " + count + " / " + Long.toString(max) + "" + TextFormatting.RESET);
  }
}
