package com.ovidpvp.worldborder;

import com.ovidpvp.worldborder.listener.WorldBorderListener;
import org.bukkit.World;
import org.bukkit.configuration.ConfigurationSection;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;

import static java.util.Objects.requireNonNull;

public class WorldBorderHandler {

    private final Map<String, WorldBorder> worldBorders = new HashMap<>();

    public WorldBorderHandler(WorldBorderPlugin plugin, ConfigurationSection section) {
        if (section.isConfigurationSection("worlds")) {
            final ConfigurationSection worldSection = section.getConfigurationSection("worlds");
            for (String worldName : worldSection.getKeys(false)) {
                if (worldSection.isInt(worldName + ".border")) {
                    final int border = worldSection.getInt(worldName + ".border");
                    this.setWorldBorder(worldName, new WorldBorder(-border, border, -border, border));

                    plugin.getLogger().log(Level.INFO, "Set world border for world " + worldName + " to " + border);
                }
            }
        }

        plugin.getServer().getPluginManager().registerEvents(new WorldBorderListener(this), plugin);
    }

    public WorldBorder getWorldBorder(World world) {
        requireNonNull(world, "world is null");

        return worldBorders.get(world.getName());
    }

    public void setWorldBorder(String worldName, WorldBorder worldBorder) {
        requireNonNull(worldName, "worldName is null");
        requireNonNull(worldBorder, "worldBorder is null");

        this.worldBorders.put(worldName, worldBorder);
    }
}
