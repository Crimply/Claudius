package me.crimp.claudius.managers;

import me.crimp.claudius.event.events.Render2DEvent;
import me.crimp.claudius.event.events.Render3DEvent;
import me.crimp.claudius.mod.Feature;
import me.crimp.claudius.mod.gui.ClickGui;
import me.crimp.claudius.mod.modules.Module;
import me.crimp.claudius.mod.modules.client.*;
import me.crimp.claudius.mod.modules.csgo.*;
import me.crimp.claudius.mod.modules.exploit.CatGirlExploit;
import me.crimp.claudius.mod.modules.exploit.NoClipFly;
import me.crimp.claudius.mod.modules.exploit.PearlBait;
import me.crimp.claudius.mod.modules.fixes.*;
import me.crimp.claudius.mod.modules.misc.*;
import me.crimp.claudius.mod.modules.pvp.*;
import me.crimp.claudius.mod.modules.render.*;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.eventhandler.EventBus;
import org.lwjgl.input.Keyboard;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class ModuleManager extends Feature {
    public List<Module> modules;
    public List<Module> sortedModules = new ArrayList<>();
    public List<String> sortedModulesABC = new ArrayList<>();

    public void init() {
        this.modules = Arrays.asList(
                ClickGuiModule.INSTANCE,

                //csgo
                new CSGOGuiModule(),
                new CSGOWatermark(),

                //exploit
                new NoClipFly(),
                new PearlBait(),

                //misc
                new ToolTips(),
                new BookPreview(),
                new ChatSuffix(),
                new Efly(),
                new DonkeyDrop(),
                new AcidOverlay(),
                new MobOwner(),
                new TrueDurabilityModule(),
                new AllItemDurability(),

                //fixes
                new GuiMovee(),
                new SetDisplay(),
                new AntiLog4j(),
                new NoToast(),

                //client
                new HUD(),
                new RPC(),
                new GuiBlurr(),

                //fake
                new CatGirlExploit(),

                //pvp
                new Burrow(),
                new Surround(),
                new PopLagger(),
                new PopCounter(),
                new AutoKiller(),
                new AutoClicker(),
                new Dispenser32k(),
                new AntiChainPop(),
                new MainHandTotem(),

                //render
                new Austraila(),
                new PenisESP(),
                new EntityAlerts(),
                new AntiAlising(),
                new BurrowEsp()
        );
    }

    public Module getModuleByName(String name) {
        for (Module module : this.modules) {
            if (!module.getName().equalsIgnoreCase(name)) continue;
            return module;
        }
        return null;
    }

    public <T extends Module> T getModuleByClass(Class<T> clazz) {
        for (Module module : this.modules) {
            if (!clazz.isInstance(module)) continue;
            return (T) module;
        }
        return null;
    }

    public boolean isModuleEnabled(String name) {
        Module module = this.getModuleByName(name);
        return module != null && module.isOn();
    }

    public Module getModuleByDisplayName(String displayName) {
        for (Module module : this.modules) {
            if (!module.getDisplayName().equalsIgnoreCase(displayName)) continue;
            return module;
        }
        return null;
    }

    public ArrayList<Module> getEnabledModules() {
        ArrayList<Module> enabledModules = new ArrayList<Module>();
        for (Module module : this.modules) {
            if (!module.isEnabled()) continue;
            enabledModules.add(module);
        }
        return enabledModules;
    }

    public ArrayList<String> getEnabledModulesName() {
        ArrayList<String> enabledModules = new ArrayList<>();
        for (Module module : this.modules) {
            if (!module.isEnabled() || !module.isDrawn()) continue;
            enabledModules.add(module.getFullArrayString());
        }
        return enabledModules;
    }

    public ArrayList<Module> getModulesByCategory(Module.Category category) {
        ArrayList<Module> modulesCategory = new ArrayList<>();
        this.modules.forEach(module -> {
            if (module.getCategory() == category) {
                modulesCategory.add(module);
            }
        });
        return modulesCategory;
    }

    public List<Module.Category> getCategories() {
        return Arrays.asList(Module.Category.values());
    }

    public void onLoad() {
        this.modules.stream().filter(Module::listening).forEach(((EventBus) MinecraftForge.EVENT_BUS)::register);
        this.modules.forEach(Module::onLoad);
    }

    public void onUpdate() {
        this.modules.stream().filter(Feature::isEnabled).forEach(Module::onUpdate);
    }

    public void onTick() {
        this.modules.stream().filter(Feature::isEnabled).forEach(Module::onTick);
    }

    public void onRender2D(Render2DEvent event) {
        this.modules.stream().filter(Feature::isEnabled).forEach(module -> module.onRender2D(event));
    }

    public void onRender3D(Render3DEvent event) {
        this.modules.stream().filter(Feature::isEnabled).forEach(module -> module.onRender3D(event));
    }

    public void sortModules(boolean reverse) {
        this.sortedModules = this.getEnabledModules().stream().filter(Module::isDrawn).sorted(Comparator.comparing(module -> this.renderer.getStringWidth(module.getFullArrayString()) * (reverse ? -1 : 1))).collect(Collectors.toList());
    }

    public void sortModulesABC() {
        this.sortedModulesABC = new ArrayList<>(this.getEnabledModulesName());
        this.sortedModulesABC.sort(String.CASE_INSENSITIVE_ORDER);
    }

    public void onLogout() {
        this.modules.forEach(Module::onLogout);
    }

    public void onLogin() {
        this.modules.forEach(Module::onLogin);
    }

    public void onUnload() {
        this.modules.forEach(MinecraftForge.EVENT_BUS::unregister);
        this.modules.forEach(Module::onUnload);
    }

    public void onUnloadPost() {
        for (Module module : this.modules) module.enabled.setValue(false);
    }

    public void onKeyPressed(int eventKey) {
        if (eventKey == 0 || !Keyboard.getEventKeyState() || ModuleManager.mc.currentScreen instanceof ClickGui) return;
        this.modules.forEach(module -> {
            if (module.getBind().getKey() == eventKey) module.toggle();
        });
    }
}

