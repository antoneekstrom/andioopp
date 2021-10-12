package andioopp.common.graphics;

/**
 * Describes a function that accepts any type of window.
 */
@FunctionalInterface
public interface GfxProgram {
    /**
     * Invokes a method which operates on a {@link Window}.
     * @param window The {@link Window}
     * @param <S> Describes the type of {@link Sprite} used by the {@link Renderer}
     * @param <R> Describes the {@link Renderer} used by the {@link Window}
     */
    <S extends Sprite<?>, R extends Renderer<S>, W extends Window<R>> void run(WindowBuilder<W> window);
}
