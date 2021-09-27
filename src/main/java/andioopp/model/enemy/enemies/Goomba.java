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
    private static final ArrayList<Enum> requirements = new ArrayList<>();
    private static final TransformFactory transformFactory = ConcreteTransform.getFactory();

    public Goomba(Vector3f position) {
        super(SPRITE_PATH, transformFactory.createWithPosition(position), new Health(BASE_HEALTH));
        this.requirements.add(REQUIREMENT.GROUND);
        this.requirements.add(REQUIREMENT.EAT);
    }

    @Override
    public void update(Time time) {
        getTransform().translate(new Vector3f(-0.5f * time.getDeltaSeconds(), 0, 0));
    }

}
