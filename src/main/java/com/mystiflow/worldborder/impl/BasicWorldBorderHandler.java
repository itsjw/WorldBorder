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
package com.mystiflow.worldborder.impl;

import com.mystiflow.worldborder.WorldBorderPlugin;
import com.mystiflow.worldborder.api.WorldBorder;
import com.mystiflow.worldborder.api.WorldBorderHandler;
import org.bukkit.World;
import org.bukkit.configuration.ConfigurationSection;

import javax.annotation.Nonnull;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;

import static java.lang.System.lineSeparator;
import static java.util.Objects.requireNonNull;

public class BasicWorldBorderHandler implements WorldBorderHandler {

    private static final double DEFAULT_KNOCKBACK = 2.5D;
    private static final int DEFAULT_DISTANCE = 2500;

    private final Map<String, WorldBorder> worldBorders = new HashMap<>();

    private final WorldBorderPlugin plugin;

    public BasicWorldBorderHandler(final WorldBorderPlugin plugin,
                                   final ConfigurationSection section) {
        this.plugin = plugin;
        this.setupConfiguration(section);
    }

    private void setupConfiguration(final ConfigurationSection section) {
        if (section.isConfigurationSection("worlds")) {
            final ConfigurationSection wSection = section
                    .getConfigurationSection("worlds");

            for (String wName : wSection.getKeys(false)) {
                final int centerX = wSection.getInt(wName + ".centerX", 0);
                final int centerZ = wSection.getInt(wName + ".centerZ", 0);
                final int distance = wSection.getInt(
                        wName + ".distance", DEFAULT_DISTANCE);
                final double knockback = wSection.getDouble(
                        wName + ".knockback-distance", DEFAULT_KNOCKBACK);

                WorldBorder wb = new BasicWorldBorder(
                        centerX, centerZ, distance);
                wb.setKnockbackDistance(knockback);
                this.worldBorders.put(wName, wb);

                this.plugin.getLogger().log(Level.INFO, "Created border"
                        + " for world '" + wName + "'!" + lineSeparator()
                        + "Knockback=" + knockback + lineSeparator()
                        + "Distance = " + distance
                );
            }
        }
    }

    @Override
    public final WorldBorder getBorder(@Nonnull final World world)
            throws NullPointerException {
        return this.getBorder(
                requireNonNull(world, "world is null").getName());
    }

    @Override
    public final WorldBorder getBorder(@Nonnull final String worldName)
            throws NullPointerException {
        return this.worldBorders.get(
                requireNonNull(worldName, "worldName is null"));
    }

    @Override
    public final void replaceBorder(@Nonnull final String worldName,
                                    @Nonnull final WorldBorder worldBorder)
            throws NullPointerException {
        requireNonNull(worldName, "worldName is null");
        requireNonNull(worldBorder, "worldBorder is null");
        this.worldBorders.put(worldName, worldBorder);
    }

    @Override
    public void clearBorder(@Nonnull String worldName)
            throws NullPointerException {
        requireNonNull(worldName, "worldName is null");
        this.worldBorders.remove(worldName);
    }
}
