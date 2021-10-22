package andioopp.controller.input;

import andioopp.common.math.vector.Vector3f;
import andioopp.common.observer.Observable;
import andioopp.common.observer.ObservableCollection;
import andioopp.common.storage.ListFactory;

public abstract class MouseInput {

    private final Observable<MouseMoveEvent> mouseMoveObservable;
    private final Observable<MouseInputEvent> mouseClickObservable;

    private Vector3f previousMousePosition = Vector3f.ZERO;

    public MouseInput(ListFactory listFactory) {
        this.mouseMoveObservable = new ObservableCollection<>(listFactory.create());
        this.mouseClickObservable = new ObservableCollection<>(listFactory.create());
    }

    public Observable<MouseMoveEvent> getMouseMoveObservable() {
        return mouseMoveObservable;
    }

    public Observable<MouseInputEvent> getMouseClickObservable() {
        return mouseClickObservable;
    }

    protected void emitMouseClickEvent(MouseEventType type, Vector3f mousePosition) {
        mouseClickObservable.notifyObservers(new MouseInputEvent(mousePosition, type));
    }

    protected void emitMouseMoveEvent(MouseEventType type, Vector3f mousePosition) {
        Vector3f mouseDelta = getMouseDelta(mousePosition);
        mouseMoveObservable.notifyObservers(new MouseMoveEvent(mousePosition, type, mouseDelta));
    }

    protected Vector3f getMouseDelta(Vector3f mousePosition) {
        Vector3f delta = mousePosition.sub(previousMousePosition);
        previousMousePosition = mousePosition;
        return delta;
    }

}
