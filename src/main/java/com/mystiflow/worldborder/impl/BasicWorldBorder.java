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

import com.mystiflow.worldborder.api.WorldBorder;
import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.entity.Entity;

import javax.annotation.Nonnull;

import static com.google.common.base.Preconditions.checkArgument;
import static java.util.Objects.requireNonNull;

public class BasicWorldBorder implements WorldBorder {

    private double knockbackDistance;
    private int centerX;
    private int centerZ;
    private int radius;

    /**
     * Creates a new WorldBorder with a given center and radius.
     *
     * @param centerX  the x center
     * @param centerZ  the z center
     * @param radius the radius in blocks
     */
    public BasicWorldBorder(final int centerX, final int centerZ,
                            final int radius) {
        this.centerX = centerX;
        this.centerZ = centerZ;
        this.radius = radius;
    }

    @Override
    public final int getMinX() {
        return centerX - radius;
    }

    @Override
    public final int getMaxX() {
        return centerX + radius;
    }

    @Override
    public final int getMinZ() {
        return centerZ - radius;
    }

    @Override
    public final int getMaxZ() {
        return centerZ + radius;
    }

    @Override
    public final int getCenterX() {
        return centerX;
    }

    @Override
    public final void setCenterX(final int centerX) {
        this.centerX = centerX;
    }

    @Override
    public final int getCenterZ() {
        return centerZ;
    }

    @Override
    public final void setCenterZ(final int centerZ) {
        this.centerZ = centerZ;
    }

    @Override
    public final int getRadius() {
        return radius;
    }

    @Override
    public final void setRadius(final int radius) {
        this.radius = radius;
    }

    @Override
    public final boolean isInBounds(final int x, final int z) {
        return (x >= centerX - radius) && (x <= centerX + radius)
                && (z >= centerZ - radius) && (z <= centerZ + radius);
    }

    @Override
    public final boolean isInBounds(@Nonnull final Entity entity) {
        requireNonNull(entity, "entity is null");
        return this.isInBounds(entity.getBlockX(), entity.getBlockZ());
    }

    @Override
    public final boolean isInBounds(@Nonnull final Location location) {
        requireNonNull(location, "location is null");
        return this.isInBounds(location.getBlockX(), location.getBlockZ());
    }

    @Override
    public final boolean isInBounds(@Nonnull final Block block) {
        requireNonNull(block, "block is null");
        return this.isInBounds(block.getX(), block.getZ());
    }

    @Override
    public final double getKnockbackDistance() {
        return knockbackDistance;
    }

    @Override
    public final void setKnockbackDistance(final double knockbackDistance)
            throws IllegalArgumentException {
        checkArgument(knockbackDistance >= 0,
                "Knockback distance must be a positive number");
        this.knockbackDistance = knockbackDistance;
    }
}
