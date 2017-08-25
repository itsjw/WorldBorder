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

    private final WorldBorderPlugin plugin;

    public WorldBorderHandler(WorldBorderPlugin plugin, ConfigurationSection section) {
        this.plugin = plugin;
        this.setupConfiguration(section);

        plugin.getServer().getPluginManager().registerEvents(new WorldBorderListener(this), plugin);
    }

    private void setupConfiguration(ConfigurationSection section) {
        if (section.isConfigurationSection("worlds")) {
            final ConfigurationSection worldSection = section.getConfigurationSection("worlds");
            for (String worldName : worldSection.getKeys(false)) {
                if (worldSection.isInt(worldName + ".border")) {
                    final int centerX = worldSection.getInt(worldName + ".centerX");
                    final int centerZ = worldSection.getInt(worldName + ".centerZ");
                    final int distance = worldSection.getInt(worldName + ".distance");

                    WorldBorder worldBorder = new WorldBorder(centerX, centerZ, distance);
                    worldBorder.setKnockbackDistance(worldSection.getInt(worldName + ".knockback-distance"));

                    this.worldBorders.put(worldName, worldBorder);
                    plugin.getLogger().log(Level.INFO, "Set world border distance for world " + worldName + " to " + distance);
                }
            }
        }
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
