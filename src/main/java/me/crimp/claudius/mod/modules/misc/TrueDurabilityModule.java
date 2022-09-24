package me.crimp.claudius.mod.modules.misc;

import me.crimp.claudius.mod.modules.Module;
import me.crimp.claudius.utils.SpecialTagCompound;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.util.List;


public class TrueDurabilityModule extends Module {
  public TrueDurabilityModule() {
    super("TrueDurability", "Display unbreakable items in red enchant, add real durability in tooltip", Category.MISC, true, false, false);
  }

  @SubscribeEvent
  public void itemToolTip(ItemTooltipEvent event) {

    ItemStack stack = event.getItemStack();
    int max = stack.getMaxDamage();
    List<String> toolTip1 = event.getToolTip();
    if(stack.isEmpty() || max <= 0) return;
    if(stack.hasTagCompound() && stack.getTagCompound().getBoolean("Unbreakable")) {
      toolTip1.add(TextFormatting.DARK_AQUA + "Durability: 999999+ " + TextFormatting.RESET);
      return;
    }


    List<String> toolTip = event.getToolTip();

    int damage;
    NBTTagCompound tag = stack.getTagCompound();
    if(tag != null && tag instanceof SpecialTagCompound) {
      damage = ((SpecialTagCompound)tag).getTrueDamage();
    } else damage = stack.getItemDamage();

    long count = (long)max - (long)damage;

    TextFormatting color;
    if(damage > max) color = TextFormatting.DARK_AQUA;
    else color = TextFormatting.RESET;
    toolTip.add(color.toString() + "Durability: " + Long.toString(count) + TextFormatting.RESET + " / " + Long.toString(max) + "");
  }
}
