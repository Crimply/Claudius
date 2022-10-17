package me.crimp.claudius.mod.modules.misc;

import me.crimp.claudius.mod.modules.Module;
import me.crimp.claudius.mod.setting.Setting;
import me.crimp.claudius.utils.DamageTagCompound;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.util.List;


public class ExtraTooltips extends Module {
  public Setting<Boolean> NonDuraitems = this.register(new Setting<>("NonDuraitems", false));
  public Setting<Boolean> TrueDurabilty = this.register(new Setting<>("TrueDurabilty", false));

  public ExtraTooltips() {
    super("TrueDurability", "Display unbreakable items in red enchant, add real durability in tooltip", Category.MISC, true, false, false);
    //this.drawn.setValue(false);
  }

  @SubscribeEvent
  public void itemToolTip(ItemTooltipEvent event) {

    ItemStack stack = event.getItemStack();
    int max = stack.getMaxDamage();
    List<String> toolTip1 = event.getToolTip();
    if (stack.isEmpty() || max <= 0) return;
    if (stack.hasTagCompound() && stack.getTagCompound().getBoolean("Unbreakable")) {
      toolTip1.add("");
      toolTip1.add(TextFormatting.GRAY + "Durability: " + TextFormatting.GOLD + "Tag.Unbreakable ");
      return;
    }


    List<String> toolTip = event.getToolTip();
    int durabilty = toolTip.indexOf("Durability:");

    int damage;
    NBTTagCompound tag = stack.getTagCompound();
    if (tag != null && tag instanceof DamageTagCompound) {
      damage = ((DamageTagCompound) tag).getTrueDamage();
    } else damage = stack.getItemDamage();

    long count = (long) max - (long) damage;
    if (count > max) {
      if (this.TrueDurabilty.getValue()) {
        toolTip.set(durabilty, TextFormatting.GRAY + "Durability: " + TextFormatting.GOLD + Long.toString(count) + TextFormatting.GRAY + " / " + Long.toString(max));
        if (this.NonDuraitems.getValue()) {
          if (max == 0) {
            toolTip.add(TextFormatting.GRAY + "Durability: " + Long.toString(count) + " / " + Long.toString(max));
          }
        }
      }
    }
  }
}
