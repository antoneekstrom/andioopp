package andioopp.model.enemy.enemies;

import andioopp.common.time.Time;
import andioopp.common.transform.ConcreteTransform;
import andioopp.common.transform.TransformFactory;
import andioopp.common.transform.Vector3f;
import andioopp.model.damage.DamageTargetType;
import andioopp.model.stats.Health;
import andioopp.model.enemy.Enemy;

public class Goomba extends Enemy {

    private static final String SPRITE_PATH = "goomba.png";
    private static final int BASE_HEALTH = 3;
    private static final float INIT_SPEED = 0.01f;
    private static final TransformFactory transformFactory = ConcreteTransform.getFactory();

    public Goomba(Vector3f position) {
        super(SPRITE_PATH, transformFactory.createWithPosition(position), new Health(BASE_HEALTH), INIT_SPEED, 0.3f);
        damageTargetTypes.add(DamageTargetType.GROUND);
    }

    @Override
    public void update(Time time) {
        move();
    }

}
