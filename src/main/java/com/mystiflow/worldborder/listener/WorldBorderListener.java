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
