package andioopp.controller.input;

import andioopp.common.math.vector.Vector3f;

public class MouseMoveEvent extends MouseInputEvent {

    private final Vector3f mouseDelta;

    public MouseMoveEvent(Vector3f mousePosition, MouseEventType type, Vector3f mouseDelta) {
        super(mousePosition, type);
        this.mouseDelta = mouseDelta;
    }

    /**
     * Returns the distance which the mouse moved.
     *
     * @return the mouse movement as a vector
     */
    public Vector3f getMouseDelta() {
        return mouseDelta;
    }

}
