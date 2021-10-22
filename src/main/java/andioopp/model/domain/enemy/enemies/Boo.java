package andioopp.model.domain.enemy.enemies;

import andioopp.common.storage.ArrayListFactory;
import andioopp.model.domain.damage.DamageFilter;
import andioopp.model.domain.damage.DamageFilterBase;
import andioopp.model.domain.damage.DamageType;
import andioopp.model.domain.enemy.Enemy;
import andioopp.model.domain.money.Money;
import andioopp.model.domain.stats.Health;
import andioopp.model.util.ModelCoordinate;

/**
 * Boo enemy
 *
 * @author Jacob Bengtsson
 */
public class Boo extends Enemy {

    private static final String SPRITE_PATH = "boo.png";
    private static final int BASE_HEALTH = 3;
    private static final float BASE_SPEED = 0.1f;
    private static final float BASE_ATTACK_COOLDOWN = 0.3f;
    private static final DamageFilter DAMAGE_FILTER = new DamageFilterBase(
            new ArrayListFactory().create(DamageType.LIGHT), new ArrayListFactory().create()
    );
    private static final Money REWARD = new Money(20);

    public Boo(ModelCoordinate position) {
        super(SPRITE_PATH, new Health(BASE_HEALTH), position, BASE_SPEED, BASE_ATTACK_COOLDOWN, DAMAGE_FILTER, REWARD);
    }
}
