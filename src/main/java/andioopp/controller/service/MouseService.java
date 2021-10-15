package andioopp.controller.service;

import andioopp.common.graphics.Window;
import andioopp.common.observer.Observable;
import andioopp.common.observer.Observer;
import andioopp.controller.service.input.MouseEvent;

import java.util.Collection;

public class MouseService implements Observable<MouseEvent, Observer<MouseEvent>> {

    private final Window<?> window;

    public MouseService(Window<?> window) {
        this.window = window;
    }

    @Override
    public Collection<Observer<MouseEvent>> getObservers() {
        return window.getMouseObservable().getObservers();
    }
}
