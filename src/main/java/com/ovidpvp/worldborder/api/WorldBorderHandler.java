package com.ovidpvp.worldborder.api;

import org.bukkit.World;

public interface WorldBorderHandler {

    WorldBorder getWorldBorder(World world);

    WorldBorder getWorldBorder(String worldName);

    void setWorldBorder(String worldName, WorldBorder worldBorder);
}
