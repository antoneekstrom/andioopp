package andioopp.service.infrastructure.input;

import andioopp.common.input.MouseData;
import andioopp.common.observer.Observable;
import andioopp.common.observer.ObservableWithList;
import andioopp.common.observer.Observer;
import andioopp.common.storage.ListFactory;

public class DragAndDropService<T> extends MouseInputService {

    private final Observable<MouseData, Draggable<T>> draggableObservable;
    private final Observable<T, Droppable<T>> droppableObservable;

    private boolean mouseDown = false;
    private T dragData;

    public DragAndDropService(Observable<MouseData, Observer<MouseData>> mouseDataObservable, ListFactory listFactory) {
        super(mouseDataObservable);
        this.draggableObservable = new ObservableWithList<>(listFactory.create());
        this.droppableObservable = new ObservableWithList<>(listFactory.create());
    }

    private void onMouseEvent(MouseData e) {
        if (e.getType().equals(MouseData.MouseEventType.RELEASE)) {
            mouseDown = false;
            onMouseRelease(e);
        } else if (e.getType().equals(MouseData.MouseEventType.PRESS)) {
            mouseDown = true;
            onMouseDown(e);
        }
    }

    private void onMouseDown(MouseData e) {
        for (Draggable<T> d : getDraggableObservable().getObservers()) {
            boolean isBeingClicked = d.getRectangle().contains(e.getPosition());
            if (isBeingClicked) {
                d.onEvent(e);
                setDragData(d.getDragData());
            }
        }
    }

    private void onMouseRelease(MouseData e) {
        for (Droppable<T> d : getDroppableObservable().getObservers()) {
            boolean isBeingClicked = d.getRectangle().contains(e.getPosition());
            if (isBeingClicked) {
                d.onEvent(getDragData());
            }
        }
    }

    private T getDragData() {
        return dragData;
    }

    private void setDragData(T dragData) {
        this.dragData = dragData;
    }

    public boolean isMouseDown() {
        return mouseDown;
    }

    public void register() {
        getMouseDataObservable().addObserver(this::onMouseEvent);
    }

    public void unregister() {
        getMouseDataObservable().removeObserver(this::onMouseEvent);
    }

    public Observable<MouseData, Draggable<T>> getDraggableObservable() {
        return draggableObservable;
    }

    public Observable<T, Droppable<T>> getDroppableObservable() {
        return droppableObservable;
    }
}
