package andioopp.model.enemies;

import andioopp.common.ConcreteTransform;
import andioopp.gfx.Sprite;
import andioopp.gfx.SpriteFactory;
import andioopp.gfx.javafx.FxSprite;
import javafx.scene.image.Image;

public class Goomba<S extends Sprite<?>> extends Enemy<S> {

    private static String IMAGE_PATH = "goomba.png";

    public Goomba(SpriteFactory<S> sf) {
        super(new ConcreteTransform(), sf.create(IMAGE_PATH));
    }

}
