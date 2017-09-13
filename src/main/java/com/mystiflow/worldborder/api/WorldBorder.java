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

import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.entity.Entity;

import javax.annotation.Nonnull;

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
     * Gets the radius in blocks of this border.
     *
     * @return radius of border
     */
    int getRadius();

    /**
     * Sets the radius in blocks of this border.
     *
     * @param radius radius to set
     */
    void setRadius(int radius);

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
    boolean isInBounds(@Nonnull Entity entity);

    /**
     * Checks if a Location is in boundaries of this WorldBorder.
     *
     * @param location the location to check against
     * @return true if the location is in bounds with this border
     */
    boolean isInBounds(@Nonnull Location location);

    /**
     * Checks if a Block is in boundaries of this WorldBorder.
     *
     * @param block the location to check against
     * @return true if the block is in bounds with this border
     */
    boolean isInBounds(@Nonnull Block block);

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
    void setKnockbackDistance(double knockbackDistance)
            throws IllegalArgumentException;
}
