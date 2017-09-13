/*
 * Copyright 2017 Mystiflow
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.mystiflow.worldborder;

import com.mystiflow.worldborder.api.WorldBorderHandler;
import com.mystiflow.worldborder.impl.BasicWorldBorderHandler;
import com.mystiflow.worldborder.listener.WorldBorderListener;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;

public class WorldBorderPlugin extends JavaPlugin {

    private static WorldBorderPlugin instance;

    private WorldBorderHandler worldBorderHandler;

    @Override
    public final void onEnable() {
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

        // Init the handler.
        worldBorderHandler = new BasicWorldBorderHandler(
                this, getConfig().getConfigurationSection("world-border")
        );

        // Register the listener.
        getServer().getPluginManager().registerEvents(
                new WorldBorderListener(worldBorderHandler), this
        );
    }

    @Override
    public final void onDisable() {
        setInstance(null);
    }

    /**
     * Returns the {@link WorldBorderHandler}.
     *
     * @return the handler
     */
    public final WorldBorderHandler getWorldBorderHandler() {
        return worldBorderHandler;
    }

    public static WorldBorderPlugin getInstance() {
        return WorldBorderPlugin.instance;
    }

    private static void setInstance(final WorldBorderPlugin instance) {
        WorldBorderPlugin.instance = instance;
    }
}
