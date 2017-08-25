package com.ovidpvp.worldborder;

import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.entity.Entity;

import static java.util.Objects.requireNonNull;
import static com.google.common.base.Preconditions.checkArgument;

public class WorldBorder {

    private double knockbackDistance;
    private int centerX;
    private int centerZ;
    private int distance;

    public WorldBorder(int centerX, int centerZ, int distance) {
        this.centerX = centerX;
        this.centerZ = centerZ;
        this.distance = distance;
    }

    public int getCenterX() {
        return centerX;
    }

    public void setCenterX(int centerX) {
        this.centerX = centerX;
    }

    public int getMinX() {
        return getCenterX() - distance;
    }

    public int getMaxX() {
        return getCenterX() + distance;
    }

    public int getCenterZ() {
        return centerZ;
    }

    public void setCenterZ(int centerZ) {
        this.centerZ = centerZ;
    }

    public int getMinZ() {
        return getCenterZ() - distance;
    }

    public int getMaxZ() {
        return getCenterZ() + distance;
    }


    public int getDistance() {
        return distance;
    }

    public void setDistance(int distance) {
        this.distance = distance;
    }

    /**
     * Checks if a position is in boundaries of this WorldBorder.
     *
     * @param x the x co-ordinate to check against
     * @param z the z co-ordinate to check against.
     * @return true if the co-ordinates are in bounds with this border
     */
    public boolean isInBounds(int x, int z) {
        return x >= (centerX - distance) && x <= (centerX + distance) && z >= (centerZ + distance) && z <= (centerZ - distance);
    }

    /**
     * Checks if an Entity is in boundaries of this WorldBorder.
     *
     * @param entity the entity to check against
     * @return true if the entity is in bounds with this border
     */
    public boolean isInBounds(Entity entity) {
        requireNonNull(entity, "Location is null");
        return this.isInBounds(entity.getLocation());
    }

    /**
     * Checks if a Location is in boundaries of this WorldBorder.
     *
     * @param location the location to check against
     * @return true if the location is in bounds with this border
     */
    public boolean isInBounds(Location location) {
        requireNonNull(location, "Location is null");
        return this.isInBounds(location.getBlockX(), location.getBlockZ());
    }

    /**
     * Checks if a Block is in boundaries of this WorldBorder.
     *
     * @param block the location to check against
     * @return true if the block is in bounds with this border
     */
    public boolean isInBounds(Block block) {
        requireNonNull(block, "Block is null");
        return this.isInBounds(block.getX(), block.getZ());
    }

    public double getKnockbackDistance() {
        return knockbackDistance;
    }

    public void setKnockbackDistance(double knockbackDistance) {
        checkArgument(knockbackDistance >= 0, "Knockback distance must be a positive number");
        this.knockbackDistance = knockbackDistance;
    }
}
