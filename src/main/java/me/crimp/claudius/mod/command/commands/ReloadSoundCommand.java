//
// Decompiled by Procyon v0.5.36
//

package me.crimp.claudius.mod.command.commands;

import com.mojang.realmsclient.gui.ChatFormatting;
import me.crimp.claudius.mod.command.Command;
import net.minecraft.client.audio.SoundHandler;
import net.minecraft.client.audio.SoundManager;
import net.minecraftforge.fml.common.ObfuscationReflectionHelper;

public class ReloadSoundCommand extends Command {
    public ReloadSoundCommand() {
        super("sound", new String[0]);
    }

    @Override
    public void execute(final String[] commands) {
        try {
            final SoundManager sndManager = ObfuscationReflectionHelper.getPrivateValue(SoundHandler.class, ReloadSoundCommand.mc.getSoundHandler(), new String[]{"sndManager", "sndManager"});
            sndManager.reloadSoundSystem();
            Command.sendMessage("Reloaded Sound System.");
        } catch (Exception e) {
            System.out.println("Could not restart sound manager: " + e);
            e.printStackTrace();
            Command.sendMessage("Couldnt Reload Sound System!");
        }
    }
}
