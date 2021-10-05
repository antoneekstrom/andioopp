package andioopp.service.infrastructure.input;

import andioopp.common.input.MouseData;
import andioopp.common.observer.Observable;

/**
 * Middleman which communicates with {@link andioopp.common.gfx.Window}.
 */
public class MouseInputService {

    private final Observable<MouseData> mouseDataObservable;

    public MouseInputService(Observable<MouseData> mouseDataObservable) {
        this.mouseDataObservable = mouseDataObservable;
    }

    /**
     * Returns an observable which emits mouse events
     * @return the observable
     */
    public Observable<MouseData> getMouseDataObservable() {
        return mouseDataObservable;
    }
}
