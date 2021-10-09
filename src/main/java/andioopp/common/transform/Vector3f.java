package andioopp.common.transform;

import java.util.Objects;

public class Vector3f {

    private final float x;
    private final float y;
    private final float z;

    public Vector3f(float x, float y, float z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public Vector3f(float x, float y) {
        this(x, y, 0);
    }

    public Vector3f(float x) {
        this(x, 0, 0);
    }

    public Vector3f(Vector3f other) { this(other.getX(), other.getY(), other.getZ()); }

    public static Vector3f withX(float x) {
        return new Vector3f(x, 0, 0);
    }

    public static Vector3f withY(float y) {
        return new Vector3f(0, y, 0);
    }

    public static Vector3f withZ(float z) {
        return new Vector3f(0, 0, z);
    }

    public static Vector3f one() {
        return new Vector3f(1, 1, 1);
    }

    public static Vector3f zero() {
        return new Vector3f(0, 0, 0);
    }

    public static Vector3f all(float v) {
        return new Vector3f(v, v, v);
    }

    public Vector3f negate() { return new Vector3f(-getX(), -getY(), -getZ()); }

    public Vector3f onlyX() {
        return Vector3f.withX(getX());
    }

    public Vector3f onlyY() {
        return Vector3f.withY(getY());
    }

    public Vector3f onlyZ() {
        return Vector3f.withZ(getZ());
    }

    public Vector3f scale(Vector3f other) {
        return new Vector3f(getX() * other.getX(), getY() * other.getY(), getZ() * other.getZ());
    }

    public Vector3f scale(float s) {
        return scale(Vector3f.all(s));
    }

    public Vector3f add(Vector3f other) {
        return new Vector3f(getX() + other.getX(), getY() + other.getY(), getZ() + other.getZ());
    }

    public Vector3f sub(Vector3f other) {
        return add(other.negate());
    }

    public Vector3f setX(float x) {
        return new Vector3f(x, getY(), getZ());
    }

    public Vector3f setY(float y) {
        return new Vector3f(getX(), y, getZ());
    }

    public Vector3f setZ(float z) {
        return new Vector3f(getX(), getY(), z);
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

    @Override
    public String toString() {
        return "Vector3f{" +
                "x=" + x +
                ", y=" + y +
                ", z=" + z +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Vector3f)) return false;
        Vector3f vector3f = (Vector3f) o;
        return Float.compare(vector3f.getX(), getX()) == 0 && Float.compare(vector3f.getY(), getY()) == 0 && Float.compare(vector3f.getZ(), getZ()) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(getX(), getY(), getZ());
    }
}
