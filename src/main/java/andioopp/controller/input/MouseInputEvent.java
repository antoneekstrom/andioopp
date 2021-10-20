package andioopp.controller.input;

import andioopp.common.math.vector.Vector3f;

/**
 * Event which is emitted when the user interacts with their mouse.
 */
public class MouseInputEvent {

    private final Vector3f mousePosition;
    private final MouseEventType type;

    public MouseInputEvent(Vector3f mousePosition, MouseEventType type) {
        this.mousePosition = mousePosition;
        this.type = type;
    }

    /**
     * Returns the current position of the mouse cursor.
     *
     * @return the position of the cursor
     */
    public Vector3f getMousePosition() {
        return mousePosition;
    }

    /**
     * Returns the type of the event
     *
     * @return the type
     */
    public MouseEventType getType() {
        return type;
    }
}
