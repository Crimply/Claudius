package me.crimp.claudius.mod.modules.client;

import me.crimp.claudius.Claudius;
import me.crimp.claudius.event.events.ClientEvent;
import me.crimp.claudius.mod.command.Command;
import me.crimp.claudius.mod.gui.ClickGui;
import me.crimp.claudius.mod.modules.Module;
import me.crimp.claudius.mod.setting.Bind;
import me.crimp.claudius.mod.setting.Setting;

import net.minecraft.client.settings.GameSettings;
import net.minecraft.client.settings.KeyBinding;
import net.minecraftforge.client.settings.KeyConflictContext;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import com.mojang.realmsclient.gui.ChatFormatting;

import org.lwjgl.input.Keyboard;

import static me.crimp.claudius.Claudius.configManager;

public class ClickGuiModule extends Module {
    public static final ClickGuiModule INSTANCE = new ClickGuiModule();
    public Setting<String> prefix = this.register(new Setting<>("Prefix", "!"));
    public Setting<Boolean> customFov = this.register(new Setting<>("CustomFov", false));
    public Setting<Float> fov = this.register(new Setting<>("Fov", 110f, -180f, 180f));
    public Setting<Integer> red = this.register(new Setting<>("Red", 116, 0, 255));
    public Setting<Integer> green = this.register(new Setting<>("Green", 85, 0, 255));
    public Setting<Integer> blue = this.register(new Setting<>("Blue", 185, 0, 255));
    public Setting<Integer> hoverAlpha = this.register(new Setting<>("Alpha", 255, 0, 255));
    public Setting<Integer> topRed = this.register(new Setting<>("SecondRed", 116, 0, 255));
    public Setting<Integer> topGreen = this.register(new Setting<>("SecondGreen", 85, 0, 255));
    public Setting<Integer> topBlue = this.register(new Setting<>("SecondBlue", 165, 0, 255));
    public Setting<Integer> alpha = this.register(new Setting<>("HoverAlpha", 240, 0, 255));
    public Setting<Integer> BGRed = this.register(new Setting<>("BGRed", 255, 0, 255));
    public Setting<Integer> BGGreen = this.register(new Setting<>("BGGreen", 202, 0, 255));
    public Setting<Integer> BGBlue = this.register(new Setting<>("BGBlue", 165, 0, 255));
    public Setting<Integer> BGalpha = this.register(new Setting<>("BGalpha", 150, 0, 255));
    public Setting<Boolean> Guimove = register(new Setting<>("ClickGuiMove", true, "Kekw"));

    public ClickGuiModule() {
        super("ClickGui", "Opens the ClickGui", Module.Category.CLIENT, true, false, false);
        this.bind.setValue(new Bind(Keyboard.KEY_J));
    }

    private static final KeyBinding[] KEYS = {mc.gameSettings.keyBindForward, mc.gameSettings.keyBindRight, mc.gameSettings.keyBindBack, mc.gameSettings.keyBindLeft, mc.gameSettings.keyBindJump, mc.gameSettings.keyBindSprint};

    @Override
    public void onUpdate() {
        if (this.customFov.getValue()) ClickGuiModule.mc.gameSettings.setOptionFloatValue(GameSettings.Options.FOV, this.fov.getValue());
        if ((mc.currentScreen instanceof ClickGui &&  this.Guimove.getValue())) {
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

    @SubscribeEvent
    public void onSettingChange(ClientEvent event) {
        if (event.getStage() == 2 && event.getSetting().getFeature().equals(this)) {
            if (event.getSetting().equals(this.prefix)) {
                Claudius.commandManager.setPrefix(this.prefix.getPlannedValue());
                Command.sendMessage("Prefix set to " + ChatFormatting.DARK_GRAY + Claudius.commandManager.getPrefix());
            }
            Claudius.colorManager.setColor(this.red.getPlannedValue(), this.green.getPlannedValue(), this.blue.getPlannedValue(), this.hoverAlpha.getPlannedValue());
        }
    }

    @Override
    public void onLogout() {
        configManager.saveConfig(configManager.config.replaceFirst("claudius/", ""));
    }

    @Override
    public void onEnable() {
        mc.displayGuiScreen(me.crimp.claudius.mod.gui.ClickGui.getClickGui());
    }

    @Override
    public void onLoad() {
        Claudius.colorManager.setColor(this.red.getValue(), this.green.getValue(), this.blue.getValue(), this.hoverAlpha.getValue());
        Claudius.commandManager.setPrefix(this.prefix.getValue());
    }

    @Override
    public void onTick() {
        if (!(ClickGuiModule.mc.currentScreen instanceof me.crimp.claudius.mod.gui.ClickGui)) this.disable();
    }
}

