package com.mystiflow.worldborder.impl;

import com.mystiflow.worldborder.api.WorldBorder;
import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.entity.Entity;

import static com.google.common.base.Preconditions.checkArgument;
import static java.util.Objects.requireNonNull;

public class BasicWorldBorder implements WorldBorder {

    private double knockbackDistance;
    private int centerX;
    private int centerZ;
    private int distance;

    /**
     * Creates a new WorldBorder with a given center and distance.
     *
     * @param centerX the x center
     * @param centerZ the z center
     * @param distance the distance in blocks
     */
    public BasicWorldBorder(int centerX, int centerZ, int distance) {
        this.centerX = centerX;
        this.centerZ = centerZ;
        this.distance = distance;
    }

    @Override
    public int getMinX() {
        return getCenterX() - distance;
    }

    @Override
    public int getMaxX() {
        return getCenterX() + distance;
    }

    @Override
    public int getMinZ() {
        return getCenterZ() - distance;
    }

    @Override
    public int getMaxZ() {
        return getCenterZ() + distance;
    }

    /**
     * Gets the center x co-ordinate of this block.
     *
     * @return x center
     */
    public int getCenterX() {
        return centerX;
    }

    @Override
    public void setCenterX(int centerX) {
        this.centerX = centerX;
    }

    @Override
    public int getCenterZ() {
        return centerZ;
    }

    @Override
    public void setCenterZ(int centerZ) {
        this.centerZ = centerZ;
    }

    @Override
    public int getDistance() {
        return distance;
    }

    @Override
    public void setDistance(int distance) {
        this.distance = distance;
    }

    @Override
    public boolean isInBounds(int x, int z) {
        return x >= (centerX - distance) && x <= (centerX + distance)
                && z >= (centerZ - distance) && z <= (centerZ + distance);
    }

    @Override
    public boolean isInBounds(Entity entity) {
        requireNonNull(entity, "Location is null");
        return this.isInBounds(entity.getBlockX(), entity.getBlockZ());
    }

    @Override
    public boolean isInBounds(Location location) {
        requireNonNull(location, "Location is null");
        return this.isInBounds(location.getBlockX(), location.getBlockZ());
    }

    @Override
    public boolean isInBounds(Block block) {
        requireNonNull(block, "Block is null");
        return this.isInBounds(block.getX(), block.getZ());
    }

    @Override
    public double getKnockbackDistance() {
        return knockbackDistance;
    }

    @Override
    public void setKnockbackDistance(double knockbackDistance) throws IllegalArgumentException {
        checkArgument(knockbackDistance >= 0, "Knockback distance must be a positive number");
        this.knockbackDistance = knockbackDistance;
    }
}
