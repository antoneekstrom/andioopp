package andioopp.service.infrastructure.input;

import andioopp.common.observer.Observable;
import andioopp.common.observer.ObservableWithList;
import andioopp.common.observer.Observer;
import andioopp.common.storage.ListFactory;

public class DragAndDropService<T> extends MouseInputService {

    private final Observable<MouseEvent, Draggable<T>> draggableObservable;
    private final Observable<T, Droppable<T>> droppableObservable;

    private T dragData;

    public DragAndDropService(Observable<MouseEvent, Observer<MouseEvent>> mouseDataObservable, ListFactory listFactory) {
        super(mouseDataObservable);
        this.draggableObservable = new ObservableWithList<>(listFactory.create());
        this.droppableObservable = new ObservableWithList<>(listFactory.create());
    }

    private void onMouseEvent(MouseEvent e) {
        if (e.getType().equals(MouseEvent.MouseEventType.RELEASE)) {
            onMouseRelease(e);
        } else if (e.getType().equals(MouseEvent.MouseEventType.PRESS)) {
            onMouseDown(e);
        }
    }

    private void onMouseDown(MouseEvent e) {
        for (Draggable<T> d : getDraggableObservable().getObservers()) {
            boolean isBeingClicked = d.getRectangle().contains(e.getPosition());
            if (isBeingClicked) {
                d.onEvent(e);
                setDragData(d.getDragData());
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
        setDragData(null);
    }

    private T getDragData() {
        return dragData;
    }

    private void setDragData(T dragData) {
        this.dragData = dragData;
    }

    public void register() {
        getMouseDataObservable().addObserver(this::onMouseEvent);
    }

    public void unregister() {
        getMouseDataObservable().removeObserver(this::onMouseEvent);
    }

    public Observable<MouseEvent, Draggable<T>> getDraggableObservable() {
        return draggableObservable;
    }

    public Observable<T, Droppable<T>> getDroppableObservable() {
        return droppableObservable;
    }
}
