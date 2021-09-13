package andioopp.common.transform;

public interface Transform {
    Vector3f getPosition();
    Vector3f getScale();
    Vector3f getRotation();
    void setPosition(Vector3f position);
    void translate(Vector3f translation);
}
