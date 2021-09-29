package andioopp.service.infrastructure.input;

import andioopp.common.observer.Observable;

public class MouseInputService {

    private final Observable<MouseEvent> mouseEventObservable;

    public MouseInputService(Observable<MouseEvent> mouseEventObservable) {
        this.mouseEventObservable = mouseEventObservable;
    }

    private void register() {
        mouseEventObservable.addObserver(this::onMouseEvent);
    }

    private void unregister() {
        mouseEventObservable.removeObserver(this::onMouseEvent);
    }

    private void onMouseEvent(MouseEvent e) {

    }
}
