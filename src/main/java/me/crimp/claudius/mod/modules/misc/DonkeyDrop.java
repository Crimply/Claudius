package me.crimp.claudius.mod.modules.misc;


import me.crimp.claudius.mod.modules.Module;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.passive.AbstractHorse;
import net.minecraft.init.Items;
import net.minecraft.inventory.ClickType;
import net.minecraft.item.ItemStack;
import net.minecraft.network.play.client.CPacketEntityAction;


public class DonkeyDrop extends Module {

    public DonkeyDrop() {
            super("DonkeyDrop", "DonkeyDrop", Category.MISC, true, false, false);
        }

    @Override
    public void onEnable() {
        runThread();
        super.onEnable();
    }

    private void runThread() {
        (new Thread(() -> {
            try {
                if (mc.player.getRidingEntity() instanceof AbstractHorse) {
                    mc.getConnection().sendPacket(new CPacketEntityAction(mc.player.getRidingEntity(), CPacketEntityAction.Action.OPEN_INVENTORY));
                    Thread.sleep(175);
                    for (int i = 1; i < 17; ++i) {
                        Thread.sleep(75);
                        final ItemStack itemStack = mc.player.openContainer.getInventory().get(i);
                        if (!itemStack.isEmpty() && itemStack.getItem() != Items.AIR) {
                            mc.playerController.windowClick(mc.player.openContainer.windowId, i, 0, ClickType.PICKUP, mc.player);
                            mc.playerController.windowClick(mc.player.openContainer.windowId, -999, 0, ClickType.PICKUP, mc.player);
                        }
                    }
                }
                Minecraft.getMinecraft().displayGuiScreen(null);
                this.toggle();
            } catch (Exception e) {
                e.printStackTrace();
            }
        })
        ).start();
    }


}
