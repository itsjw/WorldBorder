package com.ovidpvp.worldborder;

import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.entity.Entity;

import static com.google.common.base.Preconditions.checkArgument;
import static java.util.Objects.requireNonNull;

/**
 * Represents a map limit for a World.
 */
public class WorldBorder {

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
    public WorldBorder(int centerX, int centerZ, int distance) {
        this.centerX = centerX;
        this.centerZ = centerZ;
        this.distance = distance;
    }

    /**
     * Gets the lowest x co-ordinate that this border will still be in bounds.
     *
     * @return lowest in bounds x co-ordinate
     */
    public int getMinX() {
        return getCenterX() - distance;
    }

    /**
     * Gets the highest x co-ordinate that this border will still be in bounds.
     *
     * @return highest in bounds x co-ordinate
     */
    public int getMaxX() {
        return getCenterX() + distance;
    }

    /**
     * Gets the lowest z co-ordinate that this border will still be in bounds.
     *
     * @return lowest in bounds z co-ordinate
     */
    public int getMinZ() {
        return getCenterZ() - distance;
    }

    /**
     * Gets the highest z co-ordinate that this border will still be in bounds.
     *
     * @return highest in bounds z co-ordinate
     */
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

    /**
     * Sets the center x co-ordinate of this border.
     *
     * @param centerX x co-ordinate to set
     */
    public void setCenterX(int centerX) {
        this.centerX = centerX;
    }

    /**
     * Gets the center z co-ordinate of this block.
     *
     * @return z center
     */
    public int getCenterZ() {
        return centerZ;
    }

    /**
     * Sets the center z co-ordinate of this border.
     *
     * @param centerZ z co-ordinate to set
     */
    public void setCenterZ(int centerZ) {
        this.centerZ = centerZ;
    }

    /**
     * Gets the distance in blocks of this border.
     *
     * @return distance of border
     */
    public int getDistance() {
        return distance;
    }

    /**
     * Sets the distance in blocks of this border.
     *
     * @param distance distance to set
     */
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

    /**
     * Gets the distance in blocks that this border will knock
     * an entity that is not in bounds back.
     *
     * @return distance in blocks
     */
    public double getKnockbackDistance() {
        return knockbackDistance;
    }

    /**
     * Sets the distance in blocks that this border will knock
     * an entity that is not in bounds back.
     *
     * @param knockbackDistance distance in blocks to set
     * @throws IllegalArgumentException if distance value is negative
     */
    public void setKnockbackDistance(double knockbackDistance) throws IllegalArgumentException {
        checkArgument(knockbackDistance >= 0, "Knockback distance must be a positive number");
        this.knockbackDistance = knockbackDistance;
    }
}
