package andioopp.common.input;

import andioopp.common.transform.Vector3f;

/**
 * Event which is emitted when the user interacts with their mouse.
 */
public class MouseEvent {

    /**
     * Type of mouse event.
     */
    public enum MouseEventType {
        PRESS, RELEASE, MOVE
    }

    private final Vector3f position;
    private final MouseEventType type;

    public MouseEvent(Vector3f position, MouseEventType type) {
        this.position = position;
        this.type = type;
    }

    /**
     * Returns the current position of the mouse cursor.
     * @return the position of the cursor
     */
    public Vector3f getPosition() {
        return position;
    }

    /**
     * Returns the type of the event
     * @return the type
     */
    public MouseEventType getType() {
        return type;
    }
}
