package andioopp.gfx;

public interface Renderer<S extends Sprite<?>> {
    void drawSprite(S sprite);
}
