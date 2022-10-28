package me.crimp.claudius.mod.gui.components;

import me.crimp.claudius.Claudius;
import me.crimp.claudius.mod.Feature;
import me.crimp.claudius.mod.gui.ClickGui;
import me.crimp.claudius.mod.gui.components.items.Item;
import me.crimp.claudius.mod.gui.components.items.button.Button;
import me.crimp.claudius.mod.modules.Module;
import me.crimp.claudius.mod.modules.client.ClickGuiModule;
import me.crimp.claudius.utils.ColorUtil;
import me.crimp.claudius.utils.RenderUtil;
import net.minecraft.client.audio.PositionedSoundRecord;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.init.SoundEvents;

import java.util.ArrayList;

public class Component extends Feature {
    public static int[] counter1 = new int[]{1};
    private final ArrayList<Item> items = new ArrayList<>();
    public boolean drag;
    private int x;
    private int y;
    private int x2;
    private int y2;
    private int width;
    private int height;
    private boolean open;

    public Component(String name, int x, int y, boolean open) {
        super(name);
        this.x = x;
        this.y = y;
        this.width = 80;
        this.height = (int) 17.7f;
        this.open = open;
        this.setupItems();
    }

    public void setupItems() {}

    private void drag(int mouseX, int mouseY) {
        if (!this.drag) {
            return;
        }
        this.x = this.x2 + mouseX;
        this.y = this.y2 + mouseY;
    }

    public void drawScreen (int mouseX, int mouseY, float partialTicks) {
        this.drag(mouseX, mouseY);
        float totalItemHeight = this.open ? this.getTotalItemHeight() - 2.0f : 0.0f;
//        RenderMethods.drawGradientRect(this.x, (float)this.y - 1.5f, this.x + this.width, this.y + this.height - 6, -7829368, -6710887);
        int color = ColorUtil.toARGB(ClickGuiModule.INSTANCE.topRed.getValue(), ClickGuiModule.INSTANCE.topGreen.getValue(), ClickGuiModule.INSTANCE.topBlue.getValue(), 255);
        int color2 = ColorUtil.toARGB(ClickGuiModule.INSTANCE.BGRed.getValue(), ClickGuiModule.INSTANCE.BGGreen.getValue(), ClickGuiModule.INSTANCE.BGBlue.getValue(), ClickGuiModule.INSTANCE.BGalpha.getValue());
        RenderUtil.drawRect(this.x - 2f, (float)this.y - 2.5f, this.x + 2f + this.width, this.y + this.height - 6.0f, /*ColorUtil.toARGB(255,255,255,255)*/ color);
        //RenderUtil.drawRect(this.x - 2f, (float)this.y - 2.5f, this.x + 2f + this.width, this.y + this.height - 6.0f, ColorUtil.toARGB(255,255,255,255));
        RenderUtil.drawRect(this.x, (float)this.y - 1f, this.x + this.width, this.y + this.height - 7, color);//0x77FB4242, 0x77FB4242);

        if (this.open) {
            RenderUtil.drawRect(this.x -2f, (float)this.y + 10.5f, this.x + this.width + 2f, this.open ? (float)(this.y + this.height + 2.2f) + totalItemHeight : (float)(this.y + this.height - 1), /*ColorUtil.toARGB(255,255,255,255)*/ color);//1996488704
            RenderUtil.drawRect(this.x, (float)this.y + 12.5f, this.x + this.width, this.open ? (float)(this.y + this.height) + totalItemHeight : (float)(this.y + this.height - 1), color2);//1996488704
        }
        Claudius.textManager.drawStringWithShadow(this.getName(), (float) this.x + 3.0f, (float) this.y - 4.0f - (float) ClickGui.getClickGui().getTextOffset(), -1);
        Claudius.textManager.drawStringWithShadow("                       \u2727\u0020      ", (float) this.x + 3.0f, (float) this.y - 4.0f - (float) ClickGui.getClickGui().getTextOffset(), -1);
        GlStateManager.pushMatrix();
        GlStateManager.enableBlend();
        GlStateManager.translate(getX() + getWidth() - 7, (getY() + 6) - 0.3F, 0.0F);
        GlStateManager.disableBlend();
        GlStateManager.popMatrix();

        if (this.open) {
            float y = (float)(this.getY() + this.getHeight()) - 3.0f;
            for (Item item : getItems()) {
                item.setLocation((float)this.x + 2.0f, y);
                item.setWidth(this.getWidth() - 4);
                item.drawScreen(mouseX, mouseY, partialTicks);
                y += (float)item.getHeight() + 1.5f;
            }
        }
    }

    public void mouseClicked(int mouseX, int mouseY, int mouseButton) {
        if (mouseButton == 0 && this.isHovering(mouseX, mouseY)) {
            this.x2 = this.x - mouseX;
            this.y2 = this.y - mouseY;
            ClickGui.getClickGui().getComponents().forEach(component -> {
                if (component.drag) {
                    component.drag = false;
                }
            });
            this.drag = true;
            return;
        }
        if (mouseButton == 1 && this.isHovering(mouseX, mouseY)) {
            this.open = !this.open;
            mc.getSoundHandler().playSound(PositionedSoundRecord.getMasterRecord(SoundEvents.UI_BUTTON_CLICK, 1.0f));
            return;
        }
        if (!this.open) {
            return;
        }
        this.getItems().forEach(item -> item.mouseClicked(mouseX, mouseY, mouseButton));
    }

    public void mouseReleased(int mouseX, int mouseY, int releaseButton) {
        if (releaseButton == 0) {
            this.drag = false;
        }
        if (!this.open) {
            return;
        }
        this.getItems().forEach(item -> item.mouseReleased(mouseX, mouseY, releaseButton));
    }

    public void onKeyTyped(char typedChar, int keyCode) {
        if (!this.open) return;
        this.getItems().forEach(item -> item.onKeyTyped(typedChar, keyCode));
    }

    public void addButton(Button button) {
        this.items.add(button);
    }

    public int getX() {
        return this.x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return this.y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getWidth() {
        return this.width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return this.height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public final ArrayList<Item> getItems() {
        return this.items;
    }

    private boolean isHovering(int mouseX, int mouseY) {
        return mouseX >= this.getX() && mouseX <= this.getX() + this.getWidth() && mouseY >= this.getY() && mouseY <= this.getY() + this.getHeight() - (this.open ? 2 : 0);
    }

    private float getTotalItemHeight() {
        float height = 0.0f;
        for (Item item : this.getItems()) {
            height += (float) item.getHeight() + 1.5f;
        }
        return height;
    }
}

