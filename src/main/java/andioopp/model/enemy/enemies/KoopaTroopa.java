package andioopp.model.enemy.enemies;

import andioopp.common.time.Time;
import andioopp.common.transform.ConcreteTransform;
import andioopp.common.transform.TransformFactory;
import andioopp.common.transform.Vector3f;
import andioopp.model.FilterRequirement;
import andioopp.model.stats.Health;
import andioopp.model.enemy.Enemy;

import java.util.ArrayList;

public class KoopaTroopa extends Enemy{

    private static final String SPRITE_PATH = "koopaTroopa.png";
    private static final int BASE_HEALTH = 4;
    private final float speed = 0.01f;
    private static final TransformFactory transformFactory = ConcreteTransform.getFactory();

    public KoopaTroopa(Vector3f position) {
        super(SPRITE_PATH, transformFactory.createWithPosition(position), new Health(BASE_HEALTH));
        requirements.add(FilterRequirement.GROUND);
    }

    @Override
    public void update(Time time) {
        getTransform().translate(new Vector3f(-speed * time.getDeltaSeconds(), 0, 0));
    }

}

