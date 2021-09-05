package andioopp.gfx;

@FunctionalInterface
public interface GfxProgram {
    <S extends Sprite<?>, R extends Renderer<S>> void run(Window<R> window);
}
