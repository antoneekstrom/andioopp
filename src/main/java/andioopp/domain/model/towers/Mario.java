package andioopp.domain.model.towers;

import andioopp.common.transform.ConcreteTransform;
import andioopp.gfx.Sprite;
import andioopp.gfx.SpriteFactory;

public class Mario extends Tower {

    private static final String SPRITE_PATH = "mario.png";

    public Mario() {
        super(SPRITE_PATH);
    }

}
