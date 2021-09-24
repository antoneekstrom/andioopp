package andioopp.model.enemy.enemies;

import andioopp.common.time.Time;
import andioopp.common.transform.ConcreteTransform;
import andioopp.common.transform.TransformFactory;
import andioopp.common.transform.Vector3f;
import andioopp.model.Health;
import andioopp.model.enemy.Enemy;

import java.util.ArrayList;

public class Goomba extends Enemy {

    private static final String SPRITE_PATH = "goomba.png";
    private static final int BASE_HEALTH = 5;
    private static final ArrayList<Enum> REQUIREMENTS = new ArrayList<>();
    private static final ArrayList<Enum> IMMUNITY = new ArrayList<>();
    private static final TransformFactory transformFactory = ConcreteTransform.getFactory();

    public Goomba(Vector3f position) {
        super(SPRITE_PATH, transformFactory.createWithPosition(position), new Health(BASE_HEALTH));
        requirements.add(REQUIREMENT.GROUND);
    }

    @Override
    public void update(Time time) {
        getTransform().translate(new Vector3f(-0.005f, 0, 0));
    }

}
