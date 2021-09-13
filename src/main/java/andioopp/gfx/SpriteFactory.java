package andioopp.gfx;

/**
 * Creates sprites.
 * @param <T> Describes the type of {@link Sprite}
 */
public class SpriteFactory<T extends Sprite<?>> implements SpriteSupplier<T> {

    private final SpriteSupplier<T> spriteSupplier;

    public SpriteFactory(SpriteSupplier<T> spriteSupplier) {
        this.spriteSupplier = spriteSupplier;
    }

    /**
     * Creates a {@link Sprite}.
     * @param path Location of an image file
     * @return The {@link Sprite}
     */
    @Override
    public T get(String path) {
        return spriteSupplier.get(path);
    }
}
