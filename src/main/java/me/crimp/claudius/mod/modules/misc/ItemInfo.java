package me.crimp.claudius.mod.modules.misc;

import me.crimp.claudius.mod.modules.Module;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.util.List;

public class ItemInfo extends Module {
    public ItemInfo() {
        super("ItemInfo", "ItemInfo", Category.MISC, true, false, false);
    }

    @SubscribeEvent
    public void itemToolTip2(ItemTooltipEvent event) {
        List<String> toolTip = event.getToolTip();
        ItemStack stack = event.getItemStack();
        final ItemStack currentItem = TrueDurabilityModule.mc.player.inventory.getCurrentItem();
        if (!currentItem.isEmpty()) {
            final List tooltip = currentItem.getTooltip((EntityPlayer) TrueDurabilityModule.mc.player, (ITooltipFlag.TooltipFlags.ADVANCED));
            if (currentItem.getTagCompound() != null) {
                for (final String s : stack.getTagCompound().getKeySet()) {
                    tooltip.add(s + ":" + stack.getTagCompound().getTag(s).toString());
                }
            }
        }
    }
}
