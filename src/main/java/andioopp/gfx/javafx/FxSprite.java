package andioopp.gfx.javafx;

import andioopp.gfx.Sprite;
import javafx.geometry.Point2D;
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

    @Override
    public Image getImage() {
        return image;
    }
}
