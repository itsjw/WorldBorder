package com.ovidpvp.worldborder;

import com.ovidpvp.worldborder.api.WorldBorder;
import com.ovidpvp.worldborder.impl.BasicWorldBorder;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class WorldBorderTest {

    @Test
    public void testWorldBorder() {
        final WorldBorder worldBorder = new BasicWorldBorder(0, 0, 100);
        // Last edge block of border.
        assertTrue(worldBorder.isInBounds(100, 100));
        assertTrue(worldBorder.isInBounds(-100, -100));
        assertTrue(worldBorder.isInBounds(100, -100));
        assertTrue(worldBorder.isInBounds(-100, 100));
        // One block over border.
        assertFalse(worldBorder.isInBounds(101, 50));
        assertFalse(worldBorder.isInBounds(50, 101));
        assertFalse(worldBorder.isInBounds(-101, 50));
        assertTrue(worldBorder.isInBounds(50, -100));

        // Tests if the min and max getters return the correct values.
        assertTrue(worldBorder.getMinX() == -100);
        assertTrue(worldBorder.getMaxX() == 100);
        assertTrue(worldBorder.getMinZ() == -100);
        assertTrue(worldBorder.getMaxZ() == 100);
    }

    @Test
    public void testOffCentreWorldBorder() {
        WorldBorder worldBorder = new BasicWorldBorder(100, 100, 100);
        assertTrue(worldBorder.isInBounds(200, 200));
        assertFalse(worldBorder.isInBounds(201, 200));
        assertTrue(worldBorder.isInBounds(0, 0));
        assertFalse(worldBorder.isInBounds(-1, 0));

        worldBorder = new BasicWorldBorder(-100, -100, 100);
        assertTrue(worldBorder.isInBounds(-200, -200));
        assertFalse(worldBorder.isInBounds(-201, -200));
        assertTrue(worldBorder.isInBounds(0, 0));
        assertFalse(!worldBorder.isInBounds(-1, 0));
    }
}
