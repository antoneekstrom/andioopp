package andioopp.model.domain.enemy.enemies;

import andioopp.common.math.dimension.Dimension;
import andioopp.common.math.rectangle.ImmutableRectangle;
import andioopp.model.domain.damage.DamageFilter;
import andioopp.model.domain.damage.targets.FilterGrounded;
import andioopp.model.domain.enemy.Enemy;
import andioopp.model.domain.money.Money;
import andioopp.model.domain.stats.Health;
import andioopp.model.util.ModelCoordinate;

public class Goomba extends Enemy {

    private static final String SPRITE_PATH = "goomba.png";
    private static final int BASE_HEALTH = 10;
    private static final float BASE_SPEED = 0.2f;
    private static final float BASE_ATTACK_COOLDOWN = 0.3f;
    private static final DamageFilter DAMAGE_FILTER = new FilterGrounded();
    private static final Money REWARD = new Money(10);
    private static final Dimension size = new Dimension(new ModelCoordinate(1,1));


    public Goomba(ModelCoordinate position) {
        super(SPRITE_PATH, new Health(BASE_HEALTH), new ImmutableRectangle(position, size), BASE_SPEED, BASE_ATTACK_COOLDOWN, DAMAGE_FILTER, REWARD);
    }
}
