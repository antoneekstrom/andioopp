package andioopp.model.tower.towers;

import andioopp.model.enemy.Enemy;
import andioopp.model.tower.Tower;
import andioopp.model.tower.attack.Attack;
import andioopp.model.tower.attack.attacks.DigCoinAttack;
import andioopp.model.tower.attack.attacks.FireballAttack;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

public class Toad extends Tower {
    private static final String SPRITE_PATH = "toad.png";

    public Toad() {
        super(SPRITE_PATH, "Toad",40, 3);
    }

    @Override
    protected List<Attack> createAttacks() {
        return Arrays.asList(new DigCoinAttack(3f));
    }
}
