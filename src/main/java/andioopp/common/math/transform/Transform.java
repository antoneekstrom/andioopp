package andioopp.common.math.transform;

import andioopp.common.math.Vector3f;

public interface Transform {
    Vector3f getPosition();
    Vector3f getScale();
    Vector3f getRotation();
    void setRotation(Vector3f rotation);
    void setScale(Vector3f scale);
    void setPosition(Vector3f position);

    default void translate(Vector3f translation) {
        setPosition(getPosition().add(translation));
    }
}
