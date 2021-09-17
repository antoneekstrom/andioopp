package andioopp.gfx;

@FunctionalInterface
public interface SpriteSupplier<T extends Sprite<?>> {

    T get(String path);

}
