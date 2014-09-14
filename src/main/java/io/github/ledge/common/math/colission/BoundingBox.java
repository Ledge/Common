package io.github.ledge.common.math.colission;

import com.google.common.base.Objects;

import javax.vecmath.Vector3f;

public class BoundingBox {

    private Vector3f min;
    private Vector3f max;

    private Vector3f[] vertices;

    private BoundingBox(Vector3f min, Vector3f max) {
        this.min = min;
        this.max = max;
    }

    public static BoundingBox createEmpty() {
        return new BoundingBox(new Vector3f(), new Vector3f());
    }

    public static BoundingBox create(Vector3f min, Vector3f max) {
        return new BoundingBox(min, max);
    }

    public Vector3f getMin() {
        return new Vector3f(this.min);
    }

    public Vector3f getMax() {
        return new Vector3f(this.max);
    }

    public Vector3f getCenter() {
        Vector3f center = new Vector3f(this.max);
        center.add(this.min);
        center.scale(0.5f);
        return center;
    }

    public Vector3f getExtents() {
        Vector3f extents = new Vector3f(this.max);
        extents.sub(this.min);
        extents.scale(0.5f);
        return extents;
    }

    public BoundingBox move(Vector3f to) {
        Vector3f newMin = getMin();
        newMin.add(to);
        Vector3f newMax = getMax();
        newMax.add(to);
        return create(newMin, newMax);
    }

    public float minX() {
        return this.min.x;
    }

    public float minY() {
        return this.min.y;
    }

    public float minZ() {
        return this.min.z;
    }

    public float maxX() {
        return this.max.x;
    }

    public float maxY() {
        return this.max.y;
    }

    public float maxZ() {
        return this.max.z;
    }

    public Vector3f[] getVertices() {
        if (this.vertices == null) {

            this.vertices = new Vector3f[8];

            // The front
            this.vertices[0] = new Vector3f(this.min.x, this.min.y, this.max.z);
            this.vertices[1] = new Vector3f(this.max.x, this.min.y, this.max.z);
            this.vertices[2] = new Vector3f(this.max.x, this.max.y, this.max.z);
            this.vertices[3] = new Vector3f(this.min.x, this.max.y, this.max.z);

            // The Back
            this.vertices[4] = new Vector3f(this.min.x, this.min.y, this.min.z);
            this.vertices[5] = new Vector3f(this.max.x, this.min.y, this.min.z);
            this.vertices[6] = new Vector3f(this.max.x, this.max.y, this.min.z);
            this.vertices[7] = new Vector3f(this.min.x, this.max.y, this.min.z);
        }

        return this.vertices;
    }

    @Override
    public String toString() {
        return "{max=" + this.max + ", min=" + this.min + "}";
    }

    @Override
    public boolean equals(Object other) {
        if (!(other instanceof BoundingBox))
            return false;

        if (other == this)
            return true;

        BoundingBox otherBB = (BoundingBox) other;

        return Objects.equal(otherBB.min, this.min) && Objects.equal(otherBB.max, this.max);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(this.min, this.max);
    }
}
