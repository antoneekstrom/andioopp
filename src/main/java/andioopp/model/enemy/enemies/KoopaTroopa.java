package andioopp.model.enemy.enemies;

import andioopp.common.transform.Transform;
import andioopp.model.damage.DamageFilter;
import andioopp.model.damage.targets.FilterGrounded;
import andioopp.model.enemy.Enemy;
import andioopp.model.stats.Health;

public class KoopaTroopa extends Enemy {

    private static final String SPRITE_PATH = "koopaTroopa.png";
    private static final int BASE_HEALTH = 8;
    private static final float BASE_SPEED = 0.0015f;
    private static final float BASE_ATTACK_COOLDOWN = 1f;
    private static final DamageFilter DAMAGE_FILTER = new FilterGrounded();

    public KoopaTroopa(Transform transform) {
        super(SPRITE_PATH, transform, new Health(BASE_HEALTH), BASE_SPEED, BASE_ATTACK_COOLDOWN, DAMAGE_FILTER);
    }
}


