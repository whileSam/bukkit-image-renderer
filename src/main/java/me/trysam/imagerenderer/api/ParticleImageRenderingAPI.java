package me.trysam.imagerenderer.api;

import me.trysam.imagerenderer.math.Quaternion;
import me.trysam.imagerenderer.math.Vec3d;
import me.trysam.imagerenderer.math.Vec3f;
import me.trysam.imagerenderer.particle.ImageRenderer;
import me.trysam.imagerenderer.particle.ParticleRenderer;
import me.trysam.imagerenderer.util.Axis;
import org.bukkit.entity.Player;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collection;

public class ParticleImageRenderingAPI {

    private BufferedImage image;
    private Vec3d location;
    private Quaternion rotation = new Quaternion(new Vec3f(1, 0, 0),0);

    public ParticleImageRenderingAPI(File file, Vec3d location) throws IOException {
        this.image = ImageIO.read(file);
        this.location = location;
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
