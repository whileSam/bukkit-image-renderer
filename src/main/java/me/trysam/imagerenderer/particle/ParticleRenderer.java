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

package me.trysam.imagerenderer.particle;

import me.trysam.imagerenderer.math.Vec3d;
import me.trysam.imagerenderer.math.Vec3f;
import net.minecraft.server.v1_15_R1.PacketPlayOutWorldParticles;
import net.minecraft.server.v1_15_R1.ParticleParam;
import org.bukkit.Location;
import org.bukkit.craftbukkit.v1_15_R1.entity.CraftPlayer;
import org.bukkit.entity.Player;

/**
 * Particle rendering tool with high configurability.
 */
public class ParticleRenderer implements Renderer{

    /**
     * Particle type. Refer to {@link net.minecraft.server.v1_15_R1.Particles}
     */
    private ParticleParam particleParam;

    /**
     * Determines whether or not the particle will be visible from far
     */
    private boolean far;

    /**
     * Location, where the particle will be rendered;
     */
    private Vec3d location;

    /**
     * The spread or particle direction
     */
    private Vec3f size;

    private float speed;
    private int amount;

    /**
     *
     * @param particleParam Particle type. Refer to {@link net.minecraft.server.v1_15_R1.Particles}
     * @param far Determines whether or not the particle will be visible from far.
     * @param location Location, where the particle will be rendered. Represented as a {@link org.bukkit.Location}
     * @param size Particle scale/movement direction.
     * @param speed Speed of the particle.
     * @param amount Amount of particles that will be rendered.
     */
    public ParticleRenderer(ParticleParam particleParam, boolean far, Location location, Vec3f size, float speed, int amount) {
        this(particleParam, far, Vec3d.fromLocation(location), size, speed, amount);
    }

    /**
     * @param particleParam Particle type. Refer to {@link net.minecraft.server.v1_15_R1.Particles}
     * @param far Determines whether or not the particle will be visible from far.
     * @param location Location, where the particle will be rendered. Represented as a {@link me.trysam.imagerenderer.math.Vec3d}.
     * @param size Particle scale/movement direction.
     * @param speed Speed of the particle.
     * @param amount Amount of particles that will be rendered.
     */
    public ParticleRenderer(ParticleParam particleParam, boolean far, Vec3d location, Vec3f size, float speed, int amount) {
        this.particleParam = particleParam;
        this.far = far;
        this.location = location;
        this.size = size;
        this.speed = speed;
        this.amount = amount;
    }

    /**
     *
     * @param particleParam Particle type. Refer to {@link net.minecraft.server.v1_15_R1.Particles}
     * @param far Determines whether or not the particle will be visible from far.
     * @param x X-Coordinate of the location, where the particle will be rendered.
     * @param y Y-Coordinate of the location, where the particle will be rendered.
     * @param z Z-Coordinate of the location, where the particle will be rendered.
     * @param sx X-Element of the particle scale/movement direction.
     * @param sy Y-Element of the particle scale/movement direction.
     * @param sz Z-Element of the particle scale/movement direction.
     * @param speed Speed of the particle.
     * @param amount Amount of particles that will be rendered.
     */
    public ParticleRenderer(ParticleParam particleParam, boolean far, double x, double y, double z, float sx, float sy, float sz, float speed, int amount) {
        this(particleParam, far, new Vec3d(x, y, z), new Vec3f(sx, sy, sz), speed, amount);
    }


    /**
     *
     * @param player The players where the particle will be sent to
     */
    @Override
    public void render(Player player) {
        PacketPlayOutWorldParticles particles =
                new PacketPlayOutWorldParticles(particleParam, far, location.getX(), location.getY(), location.getZ(),
                        size.getX(), size.getY(), size.getZ(), speed, amount);

        //Gets the NMS player representation and sends the particle packet.
        ((CraftPlayer)player).getHandle().playerConnection.sendPacket(particles);
    }


    public ParticleParam getParticleParam() {
        return particleParam;
    }

    public void setParticleParam(ParticleParam particleParam) {
        this.particleParam = particleParam;
    }

    public boolean isFar() {
        return far;
    }

    public void setFar(boolean far) {
        this.far = far;
    }

    public Vec3d getLocation() {
        return location;
    }
    public void setLocation(Vec3d location) {
        this.location = location;
    }

    public void setLocation(Location location) {
        this.location = Vec3d.fromLocation(location);
    }

    public Vec3f getSize() {
        return size;
    }

    public void setSize(Vec3f size) {
        this.size = size;
    }

    public float getSpeed() {
        return speed;
    }

    public void setSpeed(float speed) {
        this.speed = speed;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
}
