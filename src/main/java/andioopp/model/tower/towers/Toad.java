package andioopp.model.tower.towers;

import andioopp.common.math.FloatRange;
import andioopp.model.tower.Tower;
import andioopp.model.tower.attack.Attack;
import andioopp.model.tower.attack.attacks.DigCoinAttack;

import java.util.Arrays;
import java.util.List;

public class Toad extends Tower {
    private static final String SPRITE_PATH = "toad.png";

    public Toad() {
        super(SPRITE_PATH, "Toad", 40, 3);
    }

    @Override
    protected List<Attack> createAttacks() {
        return Arrays.asList(new DigCoinAttack(15f, new FloatRange(15, 25)));
    }
}
