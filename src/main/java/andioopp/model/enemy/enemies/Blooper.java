package andioopp.model.enemy.enemies;

import andioopp.common.time.Time;
import andioopp.common.transform.ConcreteTransform;
import andioopp.common.transform.TransformFactory;
import andioopp.common.transform.Vector3f;
import andioopp.model.FilterRequirement;
import andioopp.model.stats.Health;
import andioopp.model.enemy.Enemy;
import java.util.ArrayList;

public class Blooper extends Enemy {

    private static final String SPRITE_PATH = null;
    private static final int BASE_HEALTH = 5;
    private static final ArrayList<Enum> requirements = new ArrayList<>();
    private static final TransformFactory transformFactory = ConcreteTransform.getFactory();

    public Blooper(Vector3f position) {
        super(SPRITE_PATH, transformFactory.createWithPosition(position), new Health(BASE_HEALTH));
        requirements.add(FilterRequirement.WATER);
    }

    @Override
    public void update(Time time) {
        getTransform().translate(new Vector3f(-0.005f, 0, 0));
    }

}

