package andioopp.common.gfx;

import andioopp.common.observer.Observable;
import andioopp.common.input.MouseData;

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

    Observable<MouseData> getMouseObservable();

    Observable<Object> getResizeObservable();

    int getWidth();

    int getHeight();

}
