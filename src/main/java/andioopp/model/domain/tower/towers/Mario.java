package andioopp.model.domain.tower.towers;

import andioopp.model.domain.stats.Health;
import andioopp.model.domain.tower.Tower;
import andioopp.model.domain.tower.attack.attacks.Attack;
import andioopp.model.domain.tower.attack.attacks.FireballAttack;
import andioopp.model.util.ModelCoordinate;

import java.util.Arrays;
import java.util.List;

/**
 * A Mario tower that attacks with a simple fireball attack.
 */
public class Mario extends Tower {

    private static final String SPRITE_PATH = "mario.png";

    public Mario(ModelCoordinate position) {
        super(position, SPRITE_PATH, "Mario", new Health(5));
    }

    @Override
    protected List<Attack> createAttacks() {
        return Arrays.asList(new FireballAttack(2.5f));
    }
}
