/*
 * Copyright (C) 2017 Mystiflow <mystiflow@gmail.com>
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the "Software"), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */
package com.mystiflow.worldborder.api;

import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.entity.Entity;

/**
 * Represents a map limit for a World.
 */
public interface WorldBorder {

    /**
     * Gets the lowest x co-ordinate that this border will still be in bounds.
     *
     * @return lowest in bounds x co-ordinate
     */
    int getMinX();

    /**
     * Gets the highest x co-ordinate that this border will still be in bounds.
     *
     * @return highest in bounds x co-ordinate
     */
    int getMaxX();

    /**
     * Gets the lowest z co-ordinate that this border will still be in bounds.
     *
     * @return lowest in bounds z co-ordinate
     */
    int getMinZ();

    /**
     * Gets the highest z co-ordinate that this border will still be in bounds.
     *
     * @return highest in bounds z co-ordinate
     */
    int getMaxZ();

    /**
     * Gets the center x co-ordinate of this block.
     *
     * @return x center
     */
    int getCenterX();

    /**
     * Sets the center x co-ordinate of this border.
     *
     * @param centerX x co-ordinate to set
     */
    void setCenterX(int centerX);

    /**
     * Gets the center z co-ordinate of this block.
     *
     * @return z center
     */
    int getCenterZ();

    /**
     * Sets the center z co-ordinate of this border.
     *
     * @param centerZ z co-ordinate to set
     */
    void setCenterZ(int centerZ);

    /**
     * Gets the distance in blocks of this border.
     *
     * @return distance of border
     */
    int getDistance();

    /**
     * Sets the distance in blocks of this border.
     *
     * @param distance distance to set
     */
    void setDistance(int distance);

    /**
     * Checks if a position is in boundaries of this WorldBorder.
     *
     * @param x the x co-ordinate to check against
     * @param z the z co-ordinate to check against.
     * @return true if the co-ordinates are in bounds with this border
     */
    boolean isInBounds(int x, int z);

    /**
     * Checks if an Entity is in boundaries of this WorldBorder.
     *
     * @param entity the entity to check against
     * @return true if the entity is in bounds with this border
     */
    boolean isInBounds(Entity entity);

    /**
     * Checks if a Location is in boundaries of this WorldBorder.
     *
     * @param location the location to check against
     * @return true if the location is in bounds with this border
     */
    boolean isInBounds(Location location);

    /**
     * Checks if a Block is in boundaries of this WorldBorder.
     *
     * @param block the location to check against
     * @return true if the block is in bounds with this border
     */
    boolean isInBounds(Block block);

    /**
     * Gets the distance in blocks that this border will knock
     * an entity that is `not in bounds back.
     *
     * @return distance in blocks
     */
    double getKnockbackDistance();

    /**
     * Sets the distance in blocks that this border will knock
     * an entity that is not in bounds back.
     *
     * @param knockbackDistance distance in blocks to set
     * @throws IllegalArgumentException if distance value is negative
     */
    void setKnockbackDistance(double knockbackDistance) throws IllegalArgumentException;
}
