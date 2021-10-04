package andioopp.service.infrastructure.input;

import andioopp.common.input.MouseData;
import andioopp.common.observer.Observable;
import andioopp.common.observer.Observer;

/**
 * Middleman which communicates with {@link andioopp.common.gfx.Window}.
 */
public class MouseInputService {

    private final Observable<MouseData, Observer<MouseData>> mouseDataObservable;

    public MouseInputService(Observable<MouseData, Observer<MouseData>> mouseDataObservable) {
        this.mouseDataObservable = mouseDataObservable;
    }

    /**
     * Returns an observable which emits mouse events
     * @return the observable
     */
    public Observable<MouseData, Observer<MouseData>> getMouseDataObservable() {
        return mouseDataObservable;
    }
}
