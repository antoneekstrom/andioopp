package andioopp.model.domain.tower.towers;

import andioopp.model.domain.money.Money;
import andioopp.model.domain.stats.Health;
import andioopp.model.domain.tower.Tower;
import andioopp.model.domain.tower.attack.Attack;
import andioopp.model.domain.tower.attack.attacks.FireballAttack;
import andioopp.model.util.ModelCoordinate;

import java.util.Arrays;
import java.util.List;

public class Luigi extends Tower {
    private static final String SPRITE_PATH = "luigi.png";

    public Luigi(ModelCoordinate position) {
        super(position, SPRITE_PATH, "Luigi", new Money(200), new Health(5));
    }

    @Override
    protected List<Attack> createAttacks() {
        return Arrays.asList(new FireballAttack(4f));
    }
}
