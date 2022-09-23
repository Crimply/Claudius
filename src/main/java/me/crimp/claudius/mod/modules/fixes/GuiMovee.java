package me.crimp.claudius.mod.modules.fixes;

import me.crimp.claudius.mod.gui.ClickGui;
import me.crimp.claudius.mod.modules.Module;
import net.minecraft.client.settings.KeyBinding;
import net.minecraftforge.client.settings.KeyConflictContext;
import org.lwjgl.input.Keyboard;


public class GuiMovee extends Module {
    public GuiMovee() {
        super("ClickGuiMove", "GuiMove", Category.FIXES, true, true, false);
        this.enabled.setValue(true);
        this.drawn.setValue(false);
    }

    private static final KeyBinding[] KEYS = {mc.gameSettings.keyBindForward, mc.gameSettings.keyBindRight, mc.gameSettings.keyBindBack, mc.gameSettings.keyBindLeft, mc.gameSettings.keyBindJump, mc.gameSettings.keyBindSprint};

    @Override
    public void onUpdate() {
        if ((mc.currentScreen instanceof ClickGui)) {
            for (final KeyBinding keyBinding : KEYS) {
                if (Keyboard.isKeyDown(keyBinding.getKeyCode())) {
                    if (keyBinding.getKeyConflictContext() != KeyConflictContext.UNIVERSAL) {
                        keyBinding.setKeyConflictContext(KeyConflictContext.UNIVERSAL);
                    }
                    KeyBinding.setKeyBindState(keyBinding.getKeyCode(), true);
                } else {
                    KeyBinding.setKeyBindState(keyBinding.getKeyCode(), false);
                }
            }
        }
    }
}