package me.crimp.claudius.utils;

import net.minecraft.client.Minecraft;
import me.crimp.claudius.Claudius;

public interface Globals {
    Minecraft mc = Minecraft.getMinecraft();

    default boolean fullNullCheck() {
        return mc.player != null && mc.world != null;
    }

    default Claudius getCrimp() {
        return Claudius.INSTANCE;
    }
}
