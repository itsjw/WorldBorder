package com.ovidpvp.worldborder;

import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;

public class WorldBorderPlugin extends JavaPlugin {

    private static WorldBorderPlugin instance;

    private WorldBorderHandler worldBorderHandler;

    @Override
    public void onEnable() {
        WorldBorderPlugin.instance = this;

        // Create a default config file.
        File folder = getDataFolder();
        if (!folder.exists()) {
            folder.mkdir();
        } else {
            File file = new File(folder, "config.yml");
            if (!file.exists()) {
                saveResource(file.getName(), false);
            }
        }

        worldBorderHandler = new WorldBorderHandler(this, getConfig().getConfigurationSection("world-border"));
    }

    @Override
    public void onDisable() {
        WorldBorderPlugin.instance = null;
    }

    public static WorldBorderPlugin getInstance() {
        return WorldBorderPlugin.instance;
    }

    public WorldBorderHandler getWorldBorderHandler() {
        return worldBorderHandler;
    }
}
