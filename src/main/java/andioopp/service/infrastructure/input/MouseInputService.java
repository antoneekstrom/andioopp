package andioopp.service.infrastructure.input;

import andioopp.common.observer.Observable;
import andioopp.common.observer.Observer;

/**
 * Middleman which communicates with {@link andioopp.common.graphics.Window}.
 */
public class MouseInputService {

    private final Observable<MouseEvent, Observer<MouseEvent>> mouseDataObservable;

    public MouseInputService(Observable<MouseEvent, Observer<MouseEvent>> mouseDataObservable) {
        this.mouseDataObservable = mouseDataObservable;
    }

    /**
     * Returns an observable which emits mouse events
     * @return the observable
     */
    public Observable<MouseEvent, Observer<MouseEvent>> getMouseDataObservable() {
        return mouseDataObservable;
    }
}
