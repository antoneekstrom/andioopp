package andioopp.common.transform;

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

    public static Vector3f withX(float x) {
        return new Vector3f(x, 0, 0);
    }

    public static Vector3f withY(float y) {
        return new Vector3f(0, y, 0);
    }

    public static Vector3f withZ(float z) {
        return new Vector3f(0, 0, z);
    }

    public static Vector3f zero() {
        return new Vector3f(0, 0, 0);
    }

    public Vector3f scale(Vector3f other) {
        return new Vector3f(getX() * other.getX(), getY() * other.getY(), getZ() * other.getZ());
    }

    public Vector3f add(Vector3f other) {
        return new Vector3f(getX() + other.getX(), getY() + other.getY(), getZ() + other.getZ());
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
}
