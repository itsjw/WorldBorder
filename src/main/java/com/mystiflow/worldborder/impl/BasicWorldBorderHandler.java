package com.mystiflow.worldborder.impl;

import com.mystiflow.worldborder.WorldBorderPlugin;
import com.mystiflow.worldborder.api.WorldBorderHandler;
import com.mystiflow.worldborder.api.WorldBorder;
import org.bukkit.World;
import org.bukkit.configuration.ConfigurationSection;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;

import static java.util.Objects.requireNonNull;

public class BasicWorldBorderHandler implements WorldBorderHandler {

    private final Map<String, WorldBorder> worldBorders = new HashMap<>();

    private final WorldBorderPlugin plugin;

    public BasicWorldBorderHandler(WorldBorderPlugin plugin, ConfigurationSection section) {
        this.plugin = plugin;
        this.setupConfiguration(section);
    }

    private void setupConfiguration(ConfigurationSection section) {
        if (section.isConfigurationSection("worlds")) {
            final ConfigurationSection worldSection = section.getConfigurationSection("worlds");
            for (String worldName : worldSection.getKeys(false)) {
                final int centerX = worldSection.getInt(worldName + ".centerX", 0);
                final int centerZ = worldSection.getInt(worldName + ".centerZ", 0);
                final int distance = worldSection.getInt(worldName + ".distance", 10000);

                WorldBorder worldBorder = new BasicWorldBorder(centerX, centerZ, distance);
                worldBorder.setKnockbackDistance(worldSection.getDouble(worldName + ".knockback-distance", 2.5));

                this.worldBorders.put(worldName, worldBorder);

                plugin.getLogger().log(Level.INFO, "Created world border for world '" + worldName + "' with distance " + distance);
                plugin.getLogger().log(Level.INFO, "Knockback distance of '" + worldName + "' is " + worldBorder.getKnockbackDistance());
            }
        }
    }

    @Override
    public WorldBorder getWorldBorder(World world) {
        return this.getWorldBorder(requireNonNull(world, "world is null").getName());
    }

    @Override
    public WorldBorder getWorldBorder(String worldName) {
        requireNonNull(worldName, "world is null");

        return worldBorders.get(worldName);
    }

    @Override
    public void setWorldBorder(String worldName, WorldBorder worldBorder) {
        requireNonNull(worldName, "worldName is null");
        requireNonNull(worldBorder, "worldBorder is null");

        this.worldBorders.put(worldName, worldBorder);
    }
}
