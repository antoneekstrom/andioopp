package andioopp.controller.input;

import andioopp.common.graphics.Window;
import andioopp.common.observer.Observable;
import andioopp.common.observer.Observer;

import java.util.Collection;

public class MouseInput implements Observable<MouseInputEvent, Observer<MouseInputEvent>> {

    private final Window<?> window;

    public MouseInput(Window<?> window) {
        this.window = window;
    }

    @Override
    public Collection<Observer<MouseInputEvent>> getObservers() {
        return window.getMouseObservable().getObservers();
    }
}
