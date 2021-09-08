package andioopp.gfx.javafx;

import andioopp.gfx.SpriteFactory;
import javafx.geometry.Point2D;

/**
 * Creates {@link FxSprite} objects.
 */
public class FxSpriteFactory implements SpriteFactory<FxSprite> {
    @Override
    public FxSprite create(String path) {
        return FxSprite.load(path);
    }
}
