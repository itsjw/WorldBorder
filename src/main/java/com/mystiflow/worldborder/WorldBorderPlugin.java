/*
 * Copyright (C) 2017 Mystiflow <mystiflow@gmail.com>
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the "Software"), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */
package com.mystiflow.worldborder;

import com.mystiflow.worldborder.listener.WorldBorderListener;
import com.mystiflow.worldborder.api.WorldBorderHandler;
import com.mystiflow.worldborder.impl.BasicWorldBorderHandler;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;

public class WorldBorderPlugin extends JavaPlugin {

    private static WorldBorderPlugin instance;

    private WorldBorderHandler worldBorderHandler;

    @Override
    public void onEnable() {
        setInstance(this);

        // Create a default config file.
        File folder = getDataFolder();
        if (!folder.exists()) {
            if (!folder.mkdir()) {
                throw new RuntimeException("Failed to create plugin directory");
            }

            getLogger().info("Plugin directory has been created");
        } else {
            File configFile = new File(folder, "config.yml");
            if (!configFile.exists()) {
                saveResource(configFile.getName(), false);
                getLogger().info("Config file has been created");
            }
        }

        worldBorderHandler = new BasicWorldBorderHandler(this, getConfig().getConfigurationSection("world-border"));
        getServer().getPluginManager().registerEvents(new WorldBorderListener(worldBorderHandler), this);
    }

    @Override
    public void onDisable() {
        setInstance(null);
    }

    public static WorldBorderPlugin getInstance() {
        return WorldBorderPlugin.instance;
    }

    private static void setInstance(WorldBorderPlugin instance) {
        WorldBorderPlugin.instance = instance;
    }

    public WorldBorderHandler getWorldBorderHandler() {
        return worldBorderHandler;
    }
}
