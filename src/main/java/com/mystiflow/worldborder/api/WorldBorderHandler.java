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
package com.mystiflow.worldborder.api;

import org.bukkit.World;

import javax.annotation.Nonnull;

/**
 * Handler to internally stores WorldBorders.
 */
public interface WorldBorderHandler {

    /**
     * Gets a WorldBorder for a world.
     *
     * @param world the world
     * @return the WorldBorder or null
     */
    WorldBorder getBorder(@Nonnull World world) throws NullPointerException;

    /**
     * Gets a WorldBorder for a world.
     *
     * @param worldName name of world
     * @return the WorldBorder or null
     */
    WorldBorder getBorder(@Nonnull String worldName);

    /**
     * Sets the World Border for a given world name.
     *
     * @param worldName   the world name to replace for
     * @param worldBorder the border to replace
     */
    void replaceBorder(@Nonnull String worldName,
                       @Nonnull WorldBorder worldBorder)
            throws NullPointerException;

    /**
     * Clears the World Border for a given world name.
     *
     * @param worldName the world name to clear for
     */
    void clearBorder(@Nonnull String worldName) throws NullPointerException;
}
