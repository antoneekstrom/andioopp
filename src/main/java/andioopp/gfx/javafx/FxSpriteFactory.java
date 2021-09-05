package andioopp.gfx.javafx;

import andioopp.gfx.SpriteFactory;
import javafx.geometry.Point2D;

public class FxSpriteFactory implements SpriteFactory<FxSprite> {
    @Override
    public FxSprite create(String path, Point2D position) {
        return FxSprite.load(path, position);
    }
}
