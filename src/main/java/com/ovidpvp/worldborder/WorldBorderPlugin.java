package com.ovidpvp.worldborder;

import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;

public class WorldBorderPlugin extends JavaPlugin {

    private WorldBorderHandler worldBorderHandler;

    @Override
    public void onEnable() {
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

    public WorldBorderHandler getWorldBorderHandler() {
        return worldBorderHandler;
    }
}
