package andioopp.model.domain.tower.towers;

import andioopp.model.domain.money.Money;
import andioopp.model.domain.stats.Health;
import andioopp.model.domain.tower.attack.Attack;
import andioopp.model.domain.tower.attack.attacks.FireballAttack;
import andioopp.model.domain.tower.Tower;
import andioopp.model.util.ModelCoordinate;

import java.util.Arrays;
import java.util.List;

/**
 * Tower Yoshi with specific properties.
 */
public class Yoshi extends Tower {

    private static final String SPRITE_PATH = "yoshi-removebg-preview.png";

    public Yoshi(ModelCoordinate position) {
        super(position, SPRITE_PATH, "Yoshi", new Money(83), new Health(9));
    }

    @Override
    protected List<Attack> createAttacks() {
        return Arrays.asList(new FireballAttack(4f));
    }
}
