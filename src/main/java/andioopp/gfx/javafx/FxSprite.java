package andioopp.gfx.javafx;

import andioopp.gfx.Sprite;
import andioopp.gfx.SpriteFactory;
import javafx.scene.image.Image;

/**
 * {@link Sprite} implementation for JavaFX.
 */
public class FxSprite implements Sprite<Image> {

    private final Image image;

    public FxSprite(Image image) {
        this.image = image;
    }

    public static FxSprite load(String path) {
        return new FxSprite(new Image(path));
    }

    public static SpriteFactory<FxSprite> getFactory() {
        return new SpriteFactory<>(FxSprite::load);
    }

    @Override
    public Image getImage() {
        return image;
    }

    @Override
    public int getWidth() {
        return (int) image.getWidth();
    }

    @Override
    public int getHeight() {
        return (int) image.getHeight();
    }
}
