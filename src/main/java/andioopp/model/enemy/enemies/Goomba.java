package andioopp.model.enemy.enemies;

import andioopp.common.time.Time;
import andioopp.common.transform.ConcreteTransform;
import andioopp.common.transform.TransformFactory;
import andioopp.common.transform.Vector3f;
import andioopp.model.FilterImmunity;
import andioopp.model.FilterRequirement;
import andioopp.model.stats.Health;
import andioopp.model.enemy.Enemy;

import java.util.ArrayList;

public class Goomba extends Enemy {

    private static final String SPRITE_PATH = "goomba.png";
    private static final int BASE_HEALTH = 3;
    private final float speed = 0.01f;
    private static final TransformFactory transformFactory = ConcreteTransform.getFactory();

    public Goomba(Vector3f position) {
        super(SPRITE_PATH, transformFactory.createWithPosition(position), new Health(BASE_HEALTH));
        requirements.add(FilterRequirement.GROUND);
    }

    @Override
    public void update(Time time) {
        getTransform().translate(new Vector3f(-speed * time.getDeltaSeconds(), 0, 0));
    }

}
