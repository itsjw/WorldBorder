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
