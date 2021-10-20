package andioopp.common.graphics;

import andioopp.common.math.dimension.Dimension;
import andioopp.common.observer.Observable;
import andioopp.controller.input.MouseInput;

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
    MouseInput getMouseInput();

    /**
     * Returns an observable that emits events when the window is resized.
     *
     * @return the observable
     */
    Observable<Dimension> getResizeObservable();

    /**
     * Returns the size of the window in pixels.
     * @return the size as a Dimension
     */
    Dimension getSize();

}
