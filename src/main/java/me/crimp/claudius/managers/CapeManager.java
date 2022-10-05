package me.crimp.claudius.managers;

import me.crimp.claudius.utils.Globals;
import net.minecraft.util.ResourceLocation;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.*;

public class CapeManager implements Globals {

    private final List<UUID> ogCapes = new ArrayList<>();
    private final List<UUID> contributorCapes = new ArrayList<>();

    private final List<ResourceLocation> ogCapeFrames = new ArrayList<>();

    public static int capeFrameCount = 0;

    public ResourceLocation getOgCape() {
        return ogCapeFrames.get(capeFrameCount % 35);
    }

    public CapeManager() {
        try { // og
            URL capesList = new URL("https://raw.githubusercontent.com/Crimply/Claudius-Capes/main/Ogcape.txt");
            BufferedReader in = new BufferedReader(new InputStreamReader(capesList.openStream()));
            String inputLine;
            while ((inputLine = in.readLine()) != null) {
                ogCapes.add(UUID.fromString(inputLine));
            }
        } catch (Exception ignored) {
        }
        try { // dev
            URL capesList = new URL("https://raw.githubusercontent.com/Crimply/Claudius-Capes/main/Devcape.txt");
            BufferedReader in = new BufferedReader(new InputStreamReader(capesList.openStream()));
            String inputLine;
            while ((inputLine = in.readLine()) != null) {
                contributorCapes.add(UUID.fromString(inputLine));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void reload() {
        try { // og
            URL capesList = new URL("https://raw.githubusercontent.com/Crimply/Claudius-Capes/main/Ogcape.txtt");
            BufferedReader in = new BufferedReader(new InputStreamReader(capesList.openStream()));
            String inputLine;
            while ((inputLine = in.readLine()) != null) {
                ogCapes.add(UUID.fromString(inputLine));
            }
        } catch (Exception ignored) {
        }
        try { // dev
            URL capesList = new URL("https://raw.githubusercontent.com/Crimply/Claudius-Capes/main/Devcape.txt");
            BufferedReader in = new BufferedReader(new InputStreamReader(capesList.openStream()));
            String inputLine;
            while ((inputLine = in.readLine()) != null) {
                contributorCapes.add(UUID.fromString(inputLine));
            }
        } catch (Exception ignored) {
        }
    }

    public boolean isOg(UUID uuid) {
        return this.ogCapes.contains(uuid);
    }

    public boolean isContributor(UUID uuid) {
        return this.contributorCapes.contains(uuid);
    }

}
