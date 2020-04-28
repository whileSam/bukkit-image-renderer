/*
 * Bukkit Image Renderer - A simple plugin to display images in Minecraft via particles.
 * Copyright (c) 2020. Samuel Eichelmann
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see https://www.gnu.org/licenses/.
 */

package me.trysam.imagerenderer.util;

import me.trysam.imagerenderer.math.Vec3f;

public enum Axis {

    X(new Vec3f(1, 0, 0)),
    Y(new Vec3f(0, 1, 0)),
    Z(new Vec3f(0, 0, 1));

    private Vec3f vector;

    Axis(Vec3f vector) {
        this.vector = vector;
    }

    public Vec3f getVector() {
        return vector;
    }
}
