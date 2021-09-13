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

    public static Vector3f zero() {
        return new Vector3f(0, 0, 0);
    }

    public Vector3f add(Vector3f other) {
        return new Vector3f(getX() + other.getX(), getY() + other.getY(), getZ() + other.getZ());
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
