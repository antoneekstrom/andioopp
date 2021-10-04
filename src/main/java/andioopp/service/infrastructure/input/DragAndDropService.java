package andioopp.service.infrastructure.input;

import andioopp.common.input.MouseData;
import andioopp.common.observer.Observable;
import andioopp.common.observer.ObservableWithList;
import andioopp.common.observer.Observer;
import andioopp.common.storage.ListFactory;

public class DragAndDropService extends MouseInputService {

    private final Observable<MouseData, DraggableObserver> draggableObservable;
    private final Observable<MouseData, DroppableObserver> droppableObservable;

    private boolean mouseDown = false;

    public DragAndDropService(Observable<MouseData, Observer<MouseData>> mouseDataObservable, ListFactory listFactory) {
        super(mouseDataObservable);
        this.draggableObservable = new ObservableWithList<>(listFactory.create());
        this.droppableObservable = new ObservableWithList<>(listFactory.create());
    }

    private void onMouseEvent(MouseData e) {
        if (e.getType().equals(MouseData.MouseEventType.PRESS)) {
            mouseDown = false;
            onMouseRelease(e);
        } else if (e.getType().equals(MouseData.MouseEventType.RELEASE)) {
            mouseDown = true;
            onMouseDown(e);
        }
    }

    private void onMouseDown(MouseData e) {
        for (DraggableObserver d : getDraggableObservable().getObservers()) {
            boolean isBeingClicked = d.getRectangle().contains(e.getPosition());
            if (isBeingClicked) {
                d.onEvent(e);
            }
        }
    }

    private void onMouseRelease(MouseData e) {
        for (DroppableObserver d : getDroppableObservable().getObservers()) {
            boolean isBeingClicked = d.getRectangle().contains(e.getPosition());
            if (isBeingClicked) {
                d.onEvent(e);
            }
        }
    }

    public void register() {
        getMouseDataObservable().addObserver(this::onMouseEvent);
    }

    public void unregister() {
        getMouseDataObservable().removeObserver(this::onMouseEvent);
    }

    public Observable<MouseData, DraggableObserver> getDraggableObservable() {
        return draggableObservable;
    }

    public Observable<MouseData, DroppableObserver> getDroppableObservable() {
        return droppableObservable;
    }
}
