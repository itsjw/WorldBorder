package com.ovidpvp.worldborder.listener;

import com.ovidpvp.worldborder.WorldBorder;
import com.ovidpvp.worldborder.WorldBorderHandler;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerTeleportEvent;

public class WorldBorderListener implements Listener {

    private final WorldBorderHandler worldBorderHandler;

    public WorldBorderListener(WorldBorderHandler worldBorderHandler) {
        this.worldBorderHandler = worldBorderHandler;
    }

    private boolean tryBounceOutOfBorder(PlayerMoveEvent event) {
        final Location from = event.getFrom();
        final Location to = event.getTo();
        if (from.getBlockX() != to.getBlockX() || from.getBlockY() != to.getBlockY() || from.getBlockZ() != to.getBlockZ()) {
            final World world = to.getWorld();
            final WorldBorder worldBorder = worldBorderHandler.getWorldBorder(world);
            if (worldBorder != null) {
                double x = to.getX(), z = to.getZ();

                final int toBlockX = to.getBlockX();
                if (toBlockX < worldBorder.getMinX()) {
                    x = worldBorder.getMinX() + worldBorder.getKnockbackDistance();
                } else if (toBlockX > worldBorder.getMaxX()) {
                    x = worldBorder.getMaxX() - worldBorder.getKnockbackDistance();
                }

                final int toBlockZ = to.getBlockZ();
                if (toBlockZ < worldBorder.getMinZ()) {
                    z = worldBorder.getMinZ() + worldBorder.getKnockbackDistance();
                } else if (toBlockZ > worldBorder.getMaxZ()) {
                    z = worldBorder.getMaxZ() - worldBorder.getKnockbackDistance();
                }

                if (x != to.getX() || z != to.getZ()) {
                    to.setX(x);
                    to.setY(world.getHighestBlockYAt((int) x, (int) z)); // TODO Check suffocation, lava, etc.
                    to.setZ(z);
                    return true;
                }
            }
        }

        return false;
    }

    @EventHandler(ignoreCancelled = true, priority = EventPriority.HIGHEST)
    public void onPlayerTeleport(PlayerTeleportEvent event) {
        if (tryBounceOutOfBorder(event)) {
            event.getPlayer().sendMessage(ChatColor.RED + "You cannot teleport somewhere outside of the world border!");
        }
    }

    @EventHandler(ignoreCancelled = true, priority = EventPriority.HIGHEST)
    public void onPlayerMove(PlayerMoveEvent event) {
        if (tryBounceOutOfBorder(event)) {
            event.getPlayer().sendMessage(ChatColor.RED + "You have reached the world border!");
        }
    }

    @EventHandler(ignoreCancelled = true, priority = EventPriority.HIGHEST)
    public void onBlockBreak(BlockBreakEvent event) {
        final Block block = event.getBlock();
        final WorldBorder worldBorder = worldBorderHandler.getWorldBorder(block.getWorld());
        if (worldBorder != null && !worldBorder.isInBounds(block)) {
            event.setCancelled(true);
            event.getPlayer().sendMessage(ChatColor.RED + "You cannot build outside of the world border!");
        }
    }

    @EventHandler(ignoreCancelled = true, priority = EventPriority.HIGHEST)
    public void onBlockPlace(BlockPlaceEvent event) {
        final Block block = event.getBlock();
        final WorldBorder worldBorder = worldBorderHandler.getWorldBorder(block.getWorld());
        if (worldBorder != null && !worldBorder.isInBounds(block)) {
            event.setCancelled(true);
            event.getPlayer().sendMessage(ChatColor.RED + "You cannot build outside of the world border!");
        }
    }
}
