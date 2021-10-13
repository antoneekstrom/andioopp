package andioopp.model.enemy.enemies;

import andioopp.common.math.transform.Transform;
import andioopp.model.damage.DamageFilter;
import andioopp.model.damage.targets.FilterGrounded;
import andioopp.model.enemy.Enemy;
import andioopp.model.stats.Health;

public class Blooper extends Enemy {

    private static final String SPRITE_PATH = null;
    private static final int BASE_HEALTH = 5;
    private static final float BASE_SPEED = 0.01f;
    private static final float BASE_ATTACK_COOLDOWN = 0.3f;
    private static final DamageFilter DAMAGE_FILTER = new FilterGrounded();

    public Blooper(Transform transform) {
        super(SPRITE_PATH, transform, new Health(BASE_HEALTH), BASE_SPEED, BASE_ATTACK_COOLDOWN, DAMAGE_FILTER);
    }
}

