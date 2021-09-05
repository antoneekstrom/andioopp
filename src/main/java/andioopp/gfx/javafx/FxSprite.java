package andioopp.gfx.javafx;

import andioopp.gfx.Sprite;
import javafx.geometry.Point2D;
import javafx.scene.image.Image;

/**
 * {@link Sprite} implementation for JavaFX.
 */
public class FxSprite implements Sprite<Image> {

    private final Image image;
    private final Point2D position;

    public FxSprite(Image image, Point2D position) {
        this.image = image;
        this.position = position;
    }

    public static FxSprite load(String path, Point2D position) {
        return new FxSprite(new Image(path), position);
    }

    @Override
    public Image getImage() {
        return image;
    }

    @Override
    public Point2D getPosition() {
        return position;
    }
}
