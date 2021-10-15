package andioopp.model.domain.enemy.enemies;

import andioopp.common.math.Rectangle;
import andioopp.common.math.transform.Transform;
import andioopp.common.time.Time;
import andioopp.model.domain.damage.DamageFilter;
import andioopp.model.domain.damage.targets.FilterGrounded;
import andioopp.model.domain.enemy.Enemy;
import andioopp.model.domain.stats.Health;
import andioopp.model.util.ModelCoordinate;

public class Blooper extends Enemy {

    private static final String SPRITE_PATH = null;
    private static final int BASE_HEALTH = 5;
    private static final float BASE_SPEED = 0.01f;
    private static final float BASE_ATTACK_COOLDOWN = 0.3f;
    private static final DamageFilter DAMAGE_FILTER = new FilterGrounded();

    public Blooper(Rectangle<ModelCoordinate> rectangle) {
        super(SPRITE_PATH, new Health(BASE_HEALTH), rectangle ,BASE_SPEED, BASE_ATTACK_COOLDOWN, DAMAGE_FILTER, 8);
    }


}

