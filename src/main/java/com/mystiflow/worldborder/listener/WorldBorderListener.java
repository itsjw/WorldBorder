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
package com.mystiflow.worldborder.listener;

import com.mystiflow.worldborder.api.WorldBorder;
import com.mystiflow.worldborder.api.WorldBorderHandler;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerTeleportEvent;

public class WorldBorderListener implements Listener {

    private static final String DENY_BUILD = ChatColor.RED + "You cannot "
            + "build outside of the world border!";
    private static final String DENY_TELEPORT = ChatColor.RED + "You cannot "
            + "teleport outside of the world border!";
    private static final String DENY_ENTRY = ChatColor.RED + "You have reached "
            + "the world border!";

    private final WorldBorderHandler wbHandler;

    public WorldBorderListener(final WorldBorderHandler wbHandler) {
        this.wbHandler = wbHandler;
    }

    private void adjustToPos(final Location to, final WorldBorder worldBorder) {
        final double knockback = worldBorder.getKnockbackDistance();

        final int toBlockX = to.getBlockX();
        if (toBlockX < worldBorder.getMinX()) {
            to.setX(worldBorder.getMinX() + knockback);
        } else if (toBlockX > worldBorder.getMaxX()) {
            to.setX(worldBorder.getMaxX() - knockback);
        }
        final int toBlockZ = to.getBlockZ();
        if (toBlockZ < worldBorder.getMinZ()) {
            to.setZ(worldBorder.getMinZ() + knockback);
        } else if (toBlockZ > worldBorder.getMaxZ()) {
            to.setZ(worldBorder.getMaxZ() - knockback);
        }
    }

    @EventHandler(ignoreCancelled = true, priority = EventPriority.HIGHEST)
    public final void onPlayerTeleport(final PlayerTeleportEvent event) {
        final Location to = event.getTo();
        final double origX = to.getX();
        final double origZ = to.getZ();
        this.adjustToPos(to, wbHandler.getBorder(to.getWorld()));
        if (Double.compare(origX, to.getX()) != 0
                || Double.compare(origZ, to.getZ()) != 0) {
            event.getPlayer().sendMessage(DENY_TELEPORT);
        }
    }

    @EventHandler(ignoreCancelled = true, priority = EventPriority.HIGHEST)
    public final void onPlayerMove(final PlayerMoveEvent event) {
        final Location from = event.getFrom();
        final Location to = event.getTo();
        // If the player didn't move a complete block, ignore.
        if (from.getBlockX() == to.getBlockX()
                && from.getBlockY() == to.getBlockY()
                && from.getBlockZ() == to.getBlockZ()) {
            return;
        }

        final double origX = to.getX();
        final double origZ = to.getZ();
        this.adjustToPos(to, wbHandler.getBorder(to.getWorld()));
        if (Double.compare(origX, to.getX()) != 0
                || Double.compare(origZ, to.getZ()) != 0) {
            event.getPlayer().sendMessage(DENY_ENTRY);
        }
    }

    @EventHandler(ignoreCancelled = true, priority = EventPriority.HIGHEST)
    public final void onBlockBreak(final BlockBreakEvent event) {
        final Block block = event.getBlock();
        final WorldBorder worldBorder = wbHandler.getBorder(block.getWorld());
        if (worldBorder != null && !worldBorder.isInBounds(block)) {
            event.setCancelled(true);
            event.getPlayer().sendMessage(DENY_BUILD);
        }
    }

    @EventHandler(ignoreCancelled = true, priority = EventPriority.HIGHEST)
    public final void onBlockPlace(final BlockPlaceEvent event) {
        final Block block = event.getBlock();
        final WorldBorder worldBorder = wbHandler.getBorder(block.getWorld());
        if (worldBorder != null && !worldBorder.isInBounds(block)) {
            event.setCancelled(true);
            event.getPlayer().sendMessage(DENY_BUILD);
        }
    }
}
