package andioopp.model.domain.enemy.enemies;

import andioopp.common.math.Rectangle;
import andioopp.common.math.transform.Transform;
import andioopp.model.domain.damage.DamageFilter;
import andioopp.model.domain.damage.targets.FilterGrounded;
import andioopp.model.domain.enemy.Enemy;
import andioopp.model.domain.stats.Health;
import andioopp.model.util.ModelCoordinate;

public class KoopaTroopa extends Enemy {

    private static final String SPRITE_PATH = "koopaTroopa.png";
    private static final int BASE_HEALTH = 8;
    private static final float BASE_SPEED = 0.0015f;
    private static final float BASE_ATTACK_COOLDOWN = 1f;
    private static final DamageFilter DAMAGE_FILTER = new FilterGrounded();

    public KoopaTroopa(Rectangle<ModelCoordinate> rectangle) {
        super(SPRITE_PATH,new Health(BASE_HEALTH),rectangle, BASE_SPEED, BASE_ATTACK_COOLDOWN, DAMAGE_FILTER, 12);
    }
}


