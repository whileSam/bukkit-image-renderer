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

package me.trysam.imagerenderer.math;

import org.bukkit.Location;
import org.bukkit.util.Vector;

public class Vec3d {

    double x;
    double y;
    double z;

    public Vec3d(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    /**
     * Converts the given {@link org.bukkit.Location} coordinates into a {@link me.trysam.imagerenderer.math.Vec3d}.
     * @param location The {@link org.bukkit.Location} to be converted.
     * @return The {@link me.trysam.imagerenderer.math.Vec3d} with the coordinates of the given parameter.
     */
    public static Vec3d fromLocation(Location location) {
        return new Vec3d(location.getX(), location.getY(), location.getZ());
    }

    /**
     * Converts the given {@link org.bukkit.util.Vector} coordinates into a {@link me.trysam.imagerenderer.math.Vec3d}.
     * @param vector The {@link org.bukkit.util.Vector} to be converted.
     * @return The {@link me.trysam.imagerenderer.math.Vec3d} with the coordinates of the given parameter.
     */
    public static Vec3d fromBukkitVector(Vector vector) {
        return new Vec3d(vector.getX(), vector.getY(), vector.getZ());
    }

    /**
     * @param vec The other {@link me.trysam.imagerenderer.math.Vec3d} to be calculated with.
     * @return The distance between this vector and the given parameter.
     */
    public double distanceTo(Vec3d vec) {
        return Math.sqrt(Math.pow(x-vec.x,2) + Math.pow(y-vec.y,2) + Math.pow(z-vec.z,2));
    }

    /**
     * Doesn't change anything in this instance.
     * @param number The number to be multiplied with.
     * @return The product of this vector and the given parameter.
     */
    public Vec3d multiplied(float number) {
        return new Vec3d(x * number, y * number, z * number);
    }

    /**
     * Doesn't change anything in this instance.
     * @return The unit vector of this vector. The length of the new vector is 1.
     */
    public Vec3d normalized() {
        double length = getLength();
        return new Vec3d(x / length, y / length, z / length);
    }

    /**
     * Doesn't change anything in this instance.
     * @param vec The other vector to be calculated with.
     * @return The dot product of this vector and the given parameter.
     */
    public double dot(Vec3d vec) {
        return x * vec.x + y * vec.y + z * vec.z;
    }


    /**
     * Doesn't change anything in this instance.
     * @param vec The other vector to be calculated with.
     * @return The cross product of this vector and the given parameter.
     */
    public Vec3d cross(Vec3d vec) {
        double newX = y*vec.z - z*vec.y;
        double newY = z*vec.x - x*vec.z;
        double newZ = x*vec.y - y*vec.x;
        return new Vec3d(newX, newY, newZ);
    }

    /**
     * Doesn't change anything in this instance.
     * @param vec The other vector to be calculated with.
     * @return The sum of this vector and the given parameter.
     */
    public Vec3d added(Vec3d vec) {
        return  new Vec3d(x+vec.x, y+vec.y, z+vec.z);
    }

    /**
     * Doesn't change anything in this instance.
     * @return The length of this vector.
     */
    public double getLength() {
        return Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2) + Math.pow(z, 2));
    }

    public void setX(double x) {
        this.x = x;
    }

    public void setY(double y) {
        this.y = y;
    }

    public void setZ(double z) {
        this.z = z;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public double getZ() {
        return z;
    }

    @Override
    public String toString() {
        return "Vec3d{" +
                "x=" + x +
                ", y=" + y +
                ", z=" + z +
                '}';
    }
}
