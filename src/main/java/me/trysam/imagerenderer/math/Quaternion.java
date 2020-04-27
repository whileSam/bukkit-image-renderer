package me.trysam.imagerenderer.math;

import static java.lang.Math.*;

/**
 * Used to rotate points along an axis.
 * Use axis_quaternion * point_quaternion * axis_quaternion^(-1) for rotation. The xyz-variables of the new quaternion are the new coordinates.
 */
public class Quaternion {

    private final Vec3f n;
    private float w;
    private float x;
    private float y;
    private float z;

    /**
     * Used to create a quaternion representing a certain axis in a 3-dimensional, cartesian coordinate system.
     * @param n The rotation axis
     * @param theta_degrees Rotation in degrees.
     */
    public Quaternion(Vec3f n, double theta_degrees) {
        Vec3f n2 = n.normalized();
        float theta = (float) toRadians(theta_degrees);
        Vec3f n_multiplied = n2.multiplied((float) sin(theta / 2));
        w = (float) cos(theta / 2);
        x = n_multiplied.getX();
        y = n_multiplied.getY();
        z = n_multiplied.getZ();
        this.n = new Vec3f(x, y, z);
    }

    /**
     * Used to create a point in 3-dimensional space.
     * @param w The angle of the quaternion (needs to be the cosine of the half of an angle in radians). If used to create a point, set this to 0.
     * @param x The x variable of the quaternion.
     * @param y The y variable of the quaternion.
     * @param z The z variable of the quaternion.
     */
    public Quaternion(float w, float x, float y, float z) {
        n = new Vec3f(x,y,z);
        this.w = w;
        this.x = x;
        this.y = y;
        this.z = z;
    }

    /**
     * Doesn't change anything to this quaternion.
     * @return The inverse of the current quaternion.
     */
    public Quaternion getInverse() {
        Vec3f inverse = n.multiplied(-1);
        return new Quaternion(w, inverse.getX(), inverse.getY(), inverse.getZ());
    }

    /**
     * Doesn't change anything to this quaternion.
     * @param quat The other quaternion to be calculated with.
     * @return The quaternion product of this quaternion and the given parameter
     */
    public Quaternion multiplied(Quaternion quat) {
        float newW = w * quat.w - n.dot(quat.n);
        Vec3f newN = n.multiplied(quat.w).added(quat.n.multiplied(w)).added(n.cross(quat.n));
        return new Quaternion(newW, newN.getX(), newN.getY(), newN.getZ());
    }


    public Vec3f getN() {
        return n;
    }

    public float getW() {
        return w;
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public float getZ() {
        return z;
    }
}
