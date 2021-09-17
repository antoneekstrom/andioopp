package andioopp.domain.model.enemy.enemies;

import andioopp.common.transform.ConcreteTransform;
import andioopp.domain.model.enemy.Enemy;

public class Goomba extends Enemy {

    private static final String SPRITE_PATH = "goomba.png";

    public Goomba() {
        super(ConcreteTransform.getFactory().create(), SPRITE_PATH);
    }

}
