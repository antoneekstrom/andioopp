package andioopp.model.domain.enemy.enemies;

import andioopp.common.math.dimension.Dimension;
import andioopp.common.math.rectangle.ImmutableRectangle;
import andioopp.model.domain.damage.DamageFilter;
import andioopp.model.domain.damage.targets.FilterGrounded;
import andioopp.model.domain.enemy.Enemy;
import andioopp.model.domain.money.Money;
import andioopp.model.domain.stats.Health;
import andioopp.model.util.ModelCoordinate;
/**
 * An enemy of type KoopaTroopa
 */
public class KoopaTroopa extends Enemy {

    private static final String SPRITE_PATH = "koopaTroopa.png";
    private static final int BASE_HEALTH = 8;
    private static final float BASE_SPEED = 0.15f;
    private static final float BASE_ATTACK_COOLDOWN = 1f;
    private static final DamageFilter DAMAGE_FILTER = new FilterGrounded();
    private static final Money REWARD = new Money(12);


    public KoopaTroopa(ModelCoordinate position) {
        super(SPRITE_PATH, new Health(BASE_HEALTH), position, BASE_SPEED, BASE_ATTACK_COOLDOWN, DAMAGE_FILTER, REWARD);
    }
}


