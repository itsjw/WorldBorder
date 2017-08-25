package com.ovidpvp.worldborder;

import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.entity.Entity;

import static java.util.Objects.requireNonNull;

public class WorldBorder {

    private static final int MIN_AREA = 10;

    private int minX;
    private int maxX;
    private int minZ;
    private int maxZ;

    public WorldBorder(int x1, int x2, int z1, int z2) {
        if (Math.abs(x1 - x2) < MIN_AREA && Math.abs(z1 - z2) < MIN_AREA) {
            throw new IllegalArgumentException("World border must be at least " + MIN_AREA + "x" + MIN_AREA + " blocks");
        }

        this.minX = Math.min(x1, x2);
        this.maxX = Math.max(x1, x2);
        this.minZ = Math.min(z1, z2);
        this.maxZ = Math.max(z1, z2);
    }

    public int getMinX() {
        return minX;
    }

    public void setMinX(int minX) {
        this.minX = minX;
    }

    public int getMaxX() {
        return maxX;
    }

    public void setMaxX(int maxX) {
        this.maxX = maxX;
    }

    public int getMinZ() {
        return minZ;
    }

    public void setMinZ(int minZ) {
        this.minZ = minZ;
    }

    public int getMaxZ() {
        return maxZ;
    }

    public void setMaxZ(int maxZ) {
        this.maxZ = maxZ;
    }

    public boolean isInBounds(int x, int z) {
        return x >= minX && x <= maxX && z >= minZ && z <= maxZ;
    }

    public boolean isInBounds(Entity entity) {
        requireNonNull(entity, "Location is null");
        return this.isInBounds(entity.getLocation());
    }

    public boolean isInBounds(Location location) {
        requireNonNull(location, "Location is null");
        return this.isInBounds(location.getBlockX(), location.getBlockZ());
    }

    public boolean isInBounds(Block block) {
        requireNonNull(block, "Block is null");
        return this.isInBounds(block.getX(), block.getZ());
    }
}
