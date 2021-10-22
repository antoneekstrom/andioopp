package andioopp.model.domain.enemy.enemies;

import andioopp.common.math.dimension.Dimension;
import andioopp.common.math.rectangle.ImmutableRectangle;
import andioopp.common.storage.ArrayListFactory;
import andioopp.model.domain.damage.DamageFilter;
import andioopp.model.domain.damage.DamageFilterBase;
import andioopp.model.domain.damage.DamageType;
import andioopp.model.domain.damage.targets.FilterGrounded;
import andioopp.model.domain.enemy.Enemy;
import andioopp.model.domain.money.Money;
import andioopp.model.domain.stats.Health;
import andioopp.model.util.ModelCoordinate;

/**
 * Goomba enemy
 *
 * @author Jacob Bengtsson
 */
public class Goomba extends Enemy {

    private static final String SPRITE_PATH = "goomba.png";
    private static final int BASE_HEALTH = 5;
    private static final float BASE_SPEED = 0.2f;
    private static final float BASE_ATTACK_COOLDOWN = 0.3f;
    private static final DamageFilter DAMAGE_FILTER = new DamageFilterBase(
            new ArrayListFactory().create(DamageType.GROUND), new ArrayListFactory().create()
    );
    private static final Money REWARD = new Money(10);


    public Goomba(ModelCoordinate position) {
        super(SPRITE_PATH, new Health(BASE_HEALTH), position, BASE_SPEED, BASE_ATTACK_COOLDOWN, DAMAGE_FILTER, REWARD);
    }
}
