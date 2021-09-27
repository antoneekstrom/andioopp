package andioopp.common.gfx.javafx;

import andioopp.common.gfx.Sprite;
import javafx.scene.image.Image;

/**
 * {@link Sprite} implementation for JavaFX.
 */
public class FxSprite implements Sprite<Image> {

    private final Image image;

    public FxSprite(Image image) {
        this.image = image;
    }

    public FxSprite(String path) {
        this(new Image(path));
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
