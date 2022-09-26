package me.crimp.claudius.mod.modules.misc;

import me.crimp.claudius.mod.modules.Module;
import me.crimp.claudius.utils.SpecialTagCompound;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.util.List;


public class AllItemDurability extends Module {
  public AllItemDurability() {
    super("ItemInfo", "tooltips", Category.MISC, true, false, false);
  }

  @SubscribeEvent
  public void itemToolTip(ItemTooltipEvent event) {

    ItemStack stack = event.getItemStack();
    int max = stack.getMaxDamage();


    List<String> toolTip = event.getToolTip();

    int damage;
    NBTTagCompound tag = stack.getTagCompound();
    if (tag != null && tag instanceof SpecialTagCompound) {
      damage = ((SpecialTagCompound) tag).getTrueDamage();
    } else damage = stack.getItemDamage();

    long count = (long) max - (long) damage;
    for (final String s : stack.getTagCompound().getKeySet()) {
      toolTip.add(s + stack.getTagCompound().getTag(s).toString());
      if (max == 0) {
        toolTip.add(TextFormatting.GRAY + "Durability: " + Long.toString(count) + " / " + Long.toString(max));
      }
    }
  }
}
