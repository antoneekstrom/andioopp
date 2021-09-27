package andioopp.common.gfx;

@FunctionalInterface
public interface SpriteFactory<T extends Sprite<?>> {

    /**
     * Creates a {@link Sprite}.
     * @param path Location of an image file
     * @return The {@link Sprite}
     */
    T get(String path);

}
