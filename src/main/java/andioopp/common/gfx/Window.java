package andioopp.common.gfx;

import andioopp.common.observer.Observable;
import andioopp.common.input.MouseEvent;
import andioopp.common.observer.Observer;
import andioopp.common.transform.Dimension;

/**
 * Represents the application window.
 * @param <R> Describes the type of {@link Renderer} the window has
 */
public interface Window<R extends Renderer<?>> {
    /**
     * Creates a {@link Renderer}.
     * @return The {@link Renderer}
     */
    R getRenderer();

    /**
     * Maximizes the window so that it fills the entire screen.
     * @param isMaximized If the window is maximized
     */
    void setMaximized(boolean isMaximized);

    /**
     * Returns an observable which emits mouse events.
     * @return the observable
     */
    Observable<MouseEvent, Observer<MouseEvent>> getMouseObservable();

    /**
     * Returns an observable that emits events when the window is resized.
     * @return the observable
     */
    Observable<Dimension, Observer<Dimension>> getResizeObservable();

    /**
     * Returns the width of the window in pixels.
     * @return the width
     */
    int getWidth();

    /**
     * Returns the height of the window in pixels.
     * @return the height
     */
    int getHeight();

}
