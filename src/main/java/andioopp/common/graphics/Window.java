package andioopp.common.graphics;

import andioopp.common.math.Dimension;
import andioopp.common.observer.Observable;
import andioopp.common.observer.Observer;
import andioopp.controller.service.input.MouseEvent;

/**
 * Represents the application window.
 *
 * @param <R> Describes the type of {@link Renderer} the window has
 */
public interface Window<R extends Renderer<?>> {
    /**
     * Creates a {@link Renderer}.
     *
     * @return The {@link Renderer}
     */
    R getRenderer();

    /**
     * Maximizes the window so that it fills the entire screen.
     *
     * @param isMaximized If the window is maximized
     */
    void setMaximized(boolean isMaximized);

    /**
     * Returns an observable which emits mouse events.
     *
     * @return the observable
     */
    Observable<MouseEvent, Observer<MouseEvent>> getMouseObservable();

    /**
     * Returns an observable that emits events when the window is resized.
     *
     * @return the observable
     */
    Observable<Dimension, Observer<Dimension>> getResizeObservable();

    /**
     * Returns the size of the window in pixels.
     * @return the size as a Dimension
     */
    Dimension getSize();

}
