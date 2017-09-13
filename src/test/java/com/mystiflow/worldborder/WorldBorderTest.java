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
package com.mystiflow.worldborder;

import com.mystiflow.worldborder.api.WorldBorder;
import com.mystiflow.worldborder.impl.BasicWorldBorder;
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
