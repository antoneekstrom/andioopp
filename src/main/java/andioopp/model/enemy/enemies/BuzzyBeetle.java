package andioopp.model.enemy.enemies;

import andioopp.common.time.Time;
import andioopp.common.transform.ConcreteTransform;
import andioopp.common.transform.TransformFactory;
import andioopp.common.transform.Vector3f;
import andioopp.model.damage.DamageSourceType;
import andioopp.model.damage.DamageTargetType;
import andioopp.model.enemy.Enemy;
import andioopp.model.stats.Health;

import java.util.ArrayList;

public class BuzzyBeetle extends Enemy {

    private static final String SPRITE_PATH = "buzzybeetle.png";
    private static final int BASE_HEALTH = 100;
    private static final TransformFactory transformFactory = ConcreteTransform.getFactory();
    private final ArrayList<DamageTargetType> damageTargetTypes = new ArrayList<>();
    private final ArrayList<DamageSourceType> damageSourceType = new ArrayList<>();

    public BuzzyBeetle(Vector3f position) {
        super(SPRITE_PATH, transformFactory.createWithPosition(position), new Health(BASE_HEALTH), 0.01f, 0.3f);
        damageTargetTypes.add(DamageTargetType.GROUND);
        damageSourceType.add(DamageSourceType.FIRE);
    }

    @Override
    public void update(Time time) {
        getTransform().translate(new Vector3f(-0.005f, 0, 0));
    }

}
