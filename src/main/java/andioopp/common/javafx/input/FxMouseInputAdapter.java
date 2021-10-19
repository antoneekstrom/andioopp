package andioopp.common.javafx.input;

import andioopp.common.math.vector.Vector3f;
import andioopp.common.storage.ListFactory;
import andioopp.controller.input.MouseEventType;
import andioopp.controller.input.MouseInput;
import javafx.event.EventType;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class FxMouseInputAdapter extends MouseInput {

    FxMouseInputAdapter(ListFactory listFactory) {
        super(listFactory);
    }

    public static MouseInput fromStage(Stage stage, ListFactory listFactory) {
        FxMouseInputAdapter adapter = new FxMouseInputAdapter(listFactory);
        stage.addEventHandler(javafx.scene.input.MouseEvent.MOUSE_MOVED, adapter::send);
        stage.addEventHandler(javafx.scene.input.MouseEvent.MOUSE_DRAGGED, adapter::send);
        stage.addEventHandler(javafx.scene.input.MouseEvent.MOUSE_PRESSED, adapter::send);
        stage.addEventHandler(javafx.scene.input.MouseEvent.MOUSE_RELEASED, adapter::send);
        return adapter;
    }

    public void send(MouseEvent e) {
        EventType<? extends MouseEvent> eventType = e.getEventType();
        if (eventType == MouseEvent.MOUSE_MOVED) {
            emitMouseMoveEvent(MouseEventType.MOVE, getMousePositionFromEvent(e));
        }
        else if (eventType == MouseEvent.MOUSE_DRAGGED) {
            emitMouseMoveEvent(MouseEventType.DRAG, getMousePositionFromEvent(e));
        }
        else if (eventType == MouseEvent.MOUSE_PRESSED) {
            emitMouseClickEvent(MouseEventType.PRESS, getMousePositionFromEvent(e));
        }
        else if (eventType == MouseEvent.MOUSE_RELEASED) {
            emitMouseClickEvent(MouseEventType.RELEASE, getMousePositionFromEvent(e));
        }
    }

    private Vector3f getMousePositionFromEvent(MouseEvent e) {
        return new Vector3f((float) e.getX(), (float) e.getY());
    }

}
