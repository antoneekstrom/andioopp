package andioopp.controller.input;

import andioopp.common.math.vector.Vector3f;
import andioopp.common.observer.CollectionObservable;
import andioopp.common.observer.Observable;
import andioopp.common.observer.Observer;
import andioopp.common.storage.ListFactory;

public class DragAndDrop<T> implements Observer<MouseInputEvent> {

    private final Observable<MouseInputEvent, Draggable<T>> draggableObservable;
    private final Observable<T, Droppable<T>> droppableObservable;

    private T dragData;
    private boolean isDragging = false;
    private Vector3f mousePosition = Vector3f.zero();

    public DragAndDrop(ListFactory listFactory) {
        this.draggableObservable = new CollectionObservable<>(listFactory.create());
        this.droppableObservable = new CollectionObservable<>(listFactory.create());
    }

    @Override
    public void onEvent(MouseInputEvent e) {
        if (e.getType().equals(MouseInputEvent.MouseEventType.RELEASE)) {
            onMouseRelease(e);
        } else if (e.getType().equals(MouseInputEvent.MouseEventType.PRESS)) {
            onMouseDown(e);
        } else if (e.getType().equals(MouseInputEvent.MouseEventType.DRAG)) {
            mousePosition = e.getPosition();
        } else if (e.getType().equals(MouseInputEvent.MouseEventType.MOVE)) {
            mousePosition = e.getPosition();
        }
    }

    private void onMouseDown(MouseInputEvent e) {
        for (Draggable<T> d : getDraggableObservable().getObservers()) {
            boolean isBeingClicked = d.getArea().contains(e.getPosition());
            if (isBeingClicked) {
                d.onEvent(e);
                beginDragging(d.getDragData());
            }
        }
    }

    private void onMouseRelease(MouseInputEvent e) {
        for (Droppable<T> d : getDroppableObservable().getObservers()) {
            boolean isBeingClicked = d.getArea().contains(e.getPosition());
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

    public Observable<MouseInputEvent, Draggable<T>> getDraggableObservable() {
        return draggableObservable;
    }

    public Observable<T, Droppable<T>> getDroppableObservable() {
        return droppableObservable;
    }
}
