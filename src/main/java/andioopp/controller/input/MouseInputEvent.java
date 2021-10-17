package andioopp.controller.input;

import andioopp.common.math.vector.Vector3f;

/**
 * Event which is emitted when the user interacts with their mouse.
 */
public class MouseInputEvent {

    /**
     * Type of mouse event.
     */
    public enum MouseEventType {
        PRESS, RELEASE, MOVE, DRAG
    }

    private final Vector3f position;
    private final MouseEventType type;

    public MouseInputEvent(Vector3f position, MouseEventType type) {
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
