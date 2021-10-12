package andioopp.model.tower.towers;

import andioopp.model.tower.Tower;
import andioopp.model.tower.attack.Attack;
import andioopp.model.tower.attack.attacks.FireballAttack;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Yoshi extends Tower {

    private static final String SPRITE_PATH = "yoshi-removebg-preview.png";

    public Yoshi() {
        super(SPRITE_PATH, "Yoshi", 83, 9);
    }

    @Override
    protected List<Attack> createAttacks() {
        return Arrays.asList(new FireballAttack(4f));
    }
}
