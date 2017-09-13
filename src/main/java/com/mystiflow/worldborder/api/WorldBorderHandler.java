/*
 * The MIT License (MIT)
 *
 * Copyright (C) 2017 Mystiflow <mystiflow@gmail.com>
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
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
