package andioopp.model.domain.tower.towers;

import andioopp.common.math.range.FloatRange;
import andioopp.model.domain.stats.Health;
import andioopp.model.domain.tower.attack.Attack;
import andioopp.model.domain.tower.Tower;
import andioopp.model.domain.tower.attack.attacks.DigCoinAttack;
import andioopp.model.util.ModelCoordinate;

import java.util.Arrays;
import java.util.List;

public class Toad extends Tower {
    private static final String SPRITE_PATH = "toad.png";

    public Toad(ModelCoordinate position) {
        super(position, SPRITE_PATH, "Toad", new Health(3));
    }

    @Override
    protected List<Attack> createAttacks() {
        return Arrays.asList(new DigCoinAttack(15f, new FloatRange(15, 25)));
    }
}
