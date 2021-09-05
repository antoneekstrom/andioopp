package andioopp.gfx;

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
}
