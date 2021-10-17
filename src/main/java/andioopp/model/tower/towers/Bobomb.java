package andioopp.model.tower.towers;

import andioopp.model.tower.Tower;
import andioopp.model.tower.attack.Attack;
import andioopp.model.tower.attack.attacks.Explosion;
import andioopp.model.tower.attack.attacks.FireballAttack;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author Arvid Svedberg
 */
public class Bobomb extends Tower {
    private static final String SPRITE_PATH = "bobomb-removebg-preview.png";

    public Bobomb() {
        super(SPRITE_PATH, "Bobomb", 200, 0);
    }

    @Override
    protected List<Attack> createAttacks() {
        return new ArrayList<>(Arrays.asList(new Explosion(0)));
    }
}