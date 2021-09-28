package andioopp.model.enemy.enemies;

import andioopp.common.time.Time;
import andioopp.common.transform.ConcreteTransform;
import andioopp.common.transform.Transform;
import andioopp.common.transform.TransformFactory;
import andioopp.common.transform.Vector3f;
import andioopp.model.FilterImmunity;
import andioopp.model.FilterRequirement;
import andioopp.model.Health;
import andioopp.model.enemy.Enemy;

import java.util.ArrayList;

public class BuzzyBeetle extends Enemy {

    private static final String SPRITE_PATH = "buzzybeetle.png";
    private static final int BASE_HEALTH = 100;
    private static final TransformFactory transformFactory = ConcreteTransform.getFactory();
    private final ArrayList<FilterRequirement> requirements = new ArrayList<>();
    private final ArrayList<FilterImmunity> immunity = new ArrayList<>();

    public BuzzyBeetle(Vector3f position) {
        super(SPRITE_PATH, transformFactory.createWithPosition(position), new Health(BASE_HEALTH));
        requirements.add(FilterRequirement.GROUND);
        immunity.add(FilterImmunity.FIREBALL);
    }

    @Override
    public void update(Time time) {
        getTransform().translate(new Vector3f(-0.005f, 0, 0));
    }

}
