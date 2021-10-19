package andioopp.common.math.vector;

import andioopp.common.math.interpolation.InterpolationFunction;
import andioopp.common.math.interpolation.Interpolations;

import java.util.Objects;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 * Represents a three-dimensional floating-point vector.
 */
public class Vector3f {

    public static final Vector3f ONE = new Vector3f(1, 1, 0);
    public static final Vector3f ZERO = new Vector3f(0, 0, 0);

    private final float x;
    private final float y;
    private final float z;

    /**
     * Creates a vector.
     *
     * @param x the value of x
     * @param y the value of y
     * @param z the value of z
     */
    public Vector3f(float x, float y, float z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    /**
     * Creates a vector with only the x and y components, and z set to zero.
     *
     * @param x the value of x
     * @param y the value of y
     */
    public Vector3f(float x, float y) {
        this(x, y, 0);
    }

    /**
     * Creates a vector with only the x component, and the rest set to zero.
     *
     * @param x the value of x
     */
    public Vector3f(float x) {
        this(x, 0, 0);
    }

    /**
     * Copies the values from another vector.
     *
     * @param other the other vector
     */
    public Vector3f(Vector3f other) {
        this(other.getX(), other.getY(), other.getZ());
    }


    /**
     * Creates a vector with only the x component, and the rest set to zero.
     *
     * @param x the value of x
     */
    public static Vector3f fromX(float x) {
        return new Vector3f(x, 0, 0);
    }

    /**
     * Creates a vector with only the y component, and the rest set to zero.
     *
     * @param y the value of y
     */
    public static Vector3f fromY(float y) {
        return new Vector3f(0, y, 0);
    }

    /**
     * Creates a vector with only the z component, and the rest set to zero.
     *
     * @param z the value of z
     */
    public static Vector3f fromZ(float z) {
        return new Vector3f(0, 0, z);
    }


    /**
     * Creates a vector with all components set to one.
     *
     * @return the vector
     * @deprecated use constant {@link #ONE} instead
     */
    public static Vector3f one() {
        return Vector3f.all(1);
    }

    /**
     * Creates a vector with all components set to zero.
     *
     * @return the vector
     * @deprecated use constant {@link #ZERO} instead
     */
    public static Vector3f zero() {
        return Vector3f.all(0);
    }

    /**
     * Creates a vector with all components set to the given value.
     *
     * @param v the value
     * @return the vector
     */
    public static Vector3f all(float v) {
        return new Vector3f(v, v, v);
    }


    /**
     * Creates another vector with the value of x from this one, and the other components set to zero.
     *
     * @return the vector
     */
    public Vector3f fromX() {
        return Vector3f.fromX(getX());
    }

    /**
     * Creates another vector with the value of y from this one, and the other components set to zero.
     *
     * @return the vector
     */
    public Vector3f fromY() {
        return Vector3f.fromY(getY());
    }

    /**
     * Creates another vector with the value of z from this one, and the other components set to zero.
     *
     * @return the vector
     */
    public Vector3f fromZ() {
        return Vector3f.fromZ(getZ());
    }


    public float sum() {
        return getX() + getY() + getZ();
    }

    public float magnitudeSquared() {
        return scale(this).sum();
    }

    public float magnitude() {
        return (float) Math.sqrt(magnitudeSquared());
    }

    /**
     * Rounds the value of each component.
     *
     * @return the resulting vector
     */
    public Vector3f round() {
        return new Vector3f(Math.round(getX()), Math.round(getY()), Math.round(getZ()));
    }

    /**
     * Negates the value of each component.
     *
     * @return the resulting vector
     */
    public Vector3f negate() {
        return new Vector3f(-getX(), -getY(), -getZ());
    }

    /**
     * Returns a vector such that multiplying the result with this one yields a vector with the value of one for each component.
     *
     * @return the resulting vector
     */
    public Vector3f inverse() {
        return new Vector3f(getX() == 0 ? 0 : 1.0f / getX(), getY() == 0 ? 0 : 1.0f / getY(), getZ() == 0 ? 0 : 1.0f / getZ());
    }

    /**
     * Linearly interpolate from this vector to the given vector.
     *
     * @param to the vector to interpolate to
     * @param amount the amount to interpolate by
     * @return the resulting vector
     */
    public Vector3f lerp(Vector3f to, float amount) {
        return interpolate(to, Interpolations::linear, amount);
    }

    /**
     * Interpolate from this vector to the given vector.
     *
     * @param to the vector to interpolate to
     * @param amount the amount to interpolate by
     * @return the resulting vector
     */
    public Vector3f interpolate(Vector3f to, InterpolationFunction f, float amount) {
        float x = f.interpolate(getX(), to.getX(), amount);
        float y = f.interpolate(getY(), to.getY(), amount);
        float z = f.interpolate(getZ(), to.getZ(), amount);
        return new Vector3f(x, y, z);
    }


    /**
     * Multiplies each component of this vector and the other vector.
     *
     * @param other the other vector
     * @return the result
     */
    public Vector3f scale(Vector3f other) {
        return new Vector3f(getX() * other.getX(), getY() * other.getY(), getZ() * other.getZ());
    }

    /**
     * Scales each component by a scalar.
     *
     * @param scalar the scalar
     * @return the result
     */
    public Vector3f scale(float scalar) {
        return scale(Vector3f.all(scalar));
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
