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

package me.trysam.imagerenderer.api;

import me.trysam.imagerenderer.math.Quaternion;
import me.trysam.imagerenderer.math.Vec3d;
import me.trysam.imagerenderer.math.Vec3f;
import me.trysam.imagerenderer.particle.ImageRenderer;
import me.trysam.imagerenderer.util.Axis;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Collection;

public class ParticleImageRenderingAPI {

    private BufferedImage image;
    private Vec3d location;
    private Quaternion rotation = new Quaternion(new Vec3f(1, 0, 0),0);

    /**
     *
     * @param file The file of the image.
     * @param location The location where the image is rendered Represented as a vector.
     * @throws IOException when the image could not be found
     */
    public ParticleImageRenderingAPI(File file, Vec3d location) throws IOException {
        this.image = ImageIO.read(file);
        this.location = location;
    }

    /**
     *
     * @param file The file of the image.
     * @param location The location where the image is rendered. Represented as a location.
     * @throws IOException when the image could not be found
     */
    public ParticleImageRenderingAPI(File file, Location location) throws IOException {
        this.image = ImageIO.read(file);
        this.location = Vec3d.fromLocation(location);
    }


    /**
     * Renders an image with the far attribute set to <em>false</em>.
     * @param players Zhe players the image is visible to.
     */
    public void renderImage(Collection<? extends Player> players) {
        renderImage(false, players);
    }

    /**
     *
     * @param far Whether or not the image should be visible from far
     * @param players The players the image is visible to.
     */
    public void renderImage(boolean far, Collection<? extends Player> players) {
        ImageRenderer renderer = new ImageRenderer(image, location, far, new Vec3f(0, 0, 0), 0f, 0);
        renderer.setRotation(rotation);
        renderer.render(players);
    }

    /**
     * Rotates the image along a local axis by <em>n</em> degrees.
     * @param axis Local rotation axis.
     * @param angle Angle of the rotation in degrees.
     * @return this
     */
    public ParticleImageRenderingAPI rotate(Axis axis, float angle) {
        rotation = rotation.multiplied(new Quaternion(axis.getVector(), angle));
        return this;
    }

    public BufferedImage getImage() {
        return image;
    }

    public void setImage(BufferedImage image) {
        this.image = image;
    }

    public Vec3d getLocation() {
        return location;
    }

    public void setLocation(Vec3d location) {
        this.location = location;
    }

    public Quaternion getRotation() {
        return rotation;
    }

    public void setRotation(Quaternion rotation) {
        this.rotation = rotation;
    }
}
