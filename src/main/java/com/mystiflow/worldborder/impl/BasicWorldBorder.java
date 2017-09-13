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
