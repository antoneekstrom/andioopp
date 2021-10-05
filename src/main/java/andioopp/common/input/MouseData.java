package andioopp.common.input;

import andioopp.common.transform.Vector3f;

public class MouseData {

    public enum MouseEventType {
        PRESS, RELEASE, MOVE
    }

    private final Vector3f position;
    private final MouseEventType type;

    public MouseData(Vector3f position, MouseEventType type) {
        this.position = position;
        this.type = type;
    }

    public Vector3f getPosition() {
        return position;
    }

    public MouseEventType getType() {
        return type;
    }
}
