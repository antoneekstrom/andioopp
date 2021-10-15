package andioopp.controller.service.input;

import andioopp.common.math.Vector3f;
import andioopp.common.observer.Observable;
import andioopp.common.observer.Observer;
import andioopp.common.storage.ListFactory;
import andioopp.common.observer.ConcreteObservable;
import andioopp.controller.service.MouseService;

public class DragAndDropService<T> {

    private final MouseService mouseService;

    private final Observable<MouseEvent, Draggable<T>> draggableObservable;
    private final Observable<T, Droppable<T>> droppableObservable;

    private T dragData;
    private boolean isDragging = false;
    private Vector3f mousePosition = Vector3f.zero();

    public DragAndDropService(MouseService mouseService, ListFactory listFactory) {
        this.mouseService = mouseService;
        this.draggableObservable = new ConcreteObservable<>(listFactory.create());
        this.droppableObservable = new ConcreteObservable<>(listFactory.create());

        this.mouseService.addObserver(this::onMouseEvent);
    }

    private void onMouseEvent(MouseEvent e) {
        if (e.getType().equals(MouseEvent.MouseEventType.RELEASE)) {
            onMouseRelease(e);
        } else if (e.getType().equals(MouseEvent.MouseEventType.PRESS)) {
            onMouseDown(e);
        } else if (e.getType().equals(MouseEvent.MouseEventType.DRAG)) {
            mousePosition = e.getPosition();
        } else if (e.getType().equals(MouseEvent.MouseEventType.MOVE)) {
            mousePosition = e.getPosition();
        }
    }

    private void onMouseDown(MouseEvent e) {
        for (Draggable<T> d : getDraggableObservable().getObservers()) {
            boolean isBeingClicked = d.getRectangle().contains(e.getPosition());
            if (isBeingClicked) {
                d.onEvent(e);
                beginDragging(d.getDragData());
            }
        }
    }

    private void onMouseRelease(MouseEvent e) {
        for (Droppable<T> d : getDroppableObservable().getObservers()) {
            boolean isBeingClicked = d.getRectangle().contains(e.getPosition());
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

    public Observable<MouseEvent, Draggable<T>> getDraggableObservable() {
        return draggableObservable;
    }

    public Observable<T, Droppable<T>> getDroppableObservable() {
        return droppableObservable;
    }
}
