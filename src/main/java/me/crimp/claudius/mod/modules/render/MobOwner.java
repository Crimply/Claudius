package me.crimp.claudius.mod.modules.render;

import me.crimp.claudius.mod.command.Command;
import me.crimp.claudius.mod.modules.Module;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.passive.AbstractHorse;
import net.minecraft.entity.passive.EntityTameable;
import org.apache.commons.io.IOUtils;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.UUID;

public class MobOwner extends Module {

    public MobOwner() {
        super("MobOwner", "",Category.Render, true, false, false);
    }

    public static HashMap<UUID, String> knownEntities = new HashMap<UUID, String>();

    @Override
    public void onUpdate() {
        if (Minecraft.getMinecraft().world == null || Minecraft.getMinecraft().player == null || Minecraft.getMinecraft().player.getUniqueID() == null)
            return;

        for (final Entity entity : Minecraft.getMinecraft().world.loadedEntityList) {
            if (entity instanceof EntityTameable) {
                final EntityTameable entityTameable = (EntityTameable) entity;
                if (entityTameable.isTamed() && entityTameable.getOwner() != null) {
                    entityTameable.setAlwaysRenderNameTag(true);
                    entityTameable.setCustomNameTag("Owner: " + entityTameable.getOwner().getDisplayName().getFormattedText());
                }
            }
            if (entity instanceof AbstractHorse) {
                final AbstractHorse abstractHorse = (AbstractHorse) entity;
                if (!abstractHorse.isTame() || abstractHorse.getOwnerUniqueId() == null) {
                    continue;
                }
                if (!knownEntities.containsKey(abstractHorse.getOwnerUniqueId())) {
                    knownEntities.put(abstractHorse.getOwnerUniqueId(), getNameFromUUID(abstractHorse.getOwnerUniqueId()));
                }
                abstractHorse.setAlwaysRenderNameTag(true);
                try {
                    abstractHorse.setCustomNameTag("Owner: " + knownEntities.get(abstractHorse.getOwnerUniqueId()));
                } catch (Exception ignored) {
                }
            }
        }

    };

    @Override
    public void onDisable() {
        for (final Entity entity : Minecraft.getMinecraft().world.loadedEntityList) {
            if (entity instanceof AbstractHorse || entity instanceof EntityTameable && entity.getCustomNameTag().contains("Owner:")) {
                entity.setAlwaysRenderNameTag(false);
                entity.setCustomNameTag("");
            }
        }
        super.onDisable();
    }

    public String getNameFromUUID(UUID uuid) {
        String url = "https://sessionserver.mojang.com/session/minecraft/profile/" + uuid;
        try {
            String nameJson = IOUtils.toString(new URL(url));
            JSONArray nameValue = (JSONArray) JSONValue.parseWithException(nameJson);
            String playerSlot = nameValue.get(nameValue.size() - 1).toString();
            JSONObject nameObject = (JSONObject) JSONValue.parseWithException(playerSlot);
            return nameObject.get("name").toString();
        } catch (IOException | ParseException e) {
            Command.sendMessage("Could not connect to Mojang API! Unable to fetch player name for mob owner");
            e.printStackTrace();
            return null;
        }
    }
}