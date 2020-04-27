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
