package andioopp.controller.input;

import andioopp.common.math.vector.Vector3f;
import andioopp.common.observer.StrictObservableCollection;
import andioopp.common.observer.Observer;
import andioopp.common.observer.StrictObservable;
import andioopp.common.storage.ListFactory;

public class DragAndDrop<T> implements Observer<MouseInputEvent> {

    private final StrictObservable<MouseInputEvent, Draggable<T>> draggableObservable;
    private final StrictObservable<T, Droppable<T>> droppableObservable;

    private T dragData;
    private boolean isDragging = false;
    private Vector3f mousePosition = Vector3f.ZERO;

    public DragAndDrop(ListFactory listFactory) {
        this.draggableObservable = new StrictObservableCollection<>(listFactory.create());
        this.droppableObservable = new StrictObservableCollection<>(listFactory.create());
    }

    @Override
    public void onEvent(MouseInputEvent e) {
        if (e.getType().equals(MouseEventType.RELEASE)) {
            onMouseRelease(e);
        } else if (e.getType().equals(MouseEventType.PRESS)) {
            onMouseDown(e);
        } else if (e.getType().equals(MouseEventType.DRAG)) {
            mousePosition = e.getMousePosition();
        } else if (e.getType().equals(MouseEventType.MOVE)) {
            mousePosition = e.getMousePosition();
        }
    }

    private void onMouseDown(MouseInputEvent e) {
        for (Draggable<T> d : getDraggableObservable().getObservers()) {
            boolean isBeingClicked = d.getArea().contains(e.getMousePosition());
            if (isBeingClicked) {
                d.onEvent(e);
                beginDragging(d.getDragData());
            }
        }
    }

    private void onMouseRelease(MouseInputEvent e) {
        for (Droppable<T> d : getDroppableObservable().getObservers()) {
            boolean isBeingClicked = d.getArea().contains(e.getMousePosition());
            if (isBeingClicked && getDragData() != null) {
                d.onEvent(getDragData());
            }
        }
        stopDragging();
    }

    private void beginDragging(T data) {
        dragData = data;
        isDragging = true;
    }

    private void stopDragging() {
        dragData = null;
        isDragging = false;
    }

    public Vector3f getMousePosition() {
        return mousePosition;
    }

    public boolean isDragging() {
        return isDragging;
    }

    public T getDragData() {
        return dragData;
    }

    public StrictObservable<MouseInputEvent, Draggable<T>> getDraggableObservable() {
        return draggableObservable;
    }

    public StrictObservable<T, Droppable<T>> getDroppableObservable() {
        return droppableObservable;
    }
}
