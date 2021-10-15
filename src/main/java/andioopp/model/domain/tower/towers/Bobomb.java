package andioopp.model.domain.tower.towers;

import andioopp.model.domain.tower.attack.Attack;
import andioopp.model.domain.tower.attack.attacks.FireballAttack;
import andioopp.model.domain.tower.Tower;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Bobomb extends Tower {
    private static final String SPRITE_PATH = "bobomb-removebg-preview.png";

    public Bobomb() {
        super(SPRITE_PATH, "Bobomb", 200, 5);
    }

    @Override
    protected List<Attack> createAttacks() {
        return new ArrayList<>(Arrays.asList(new FireballAttack(8f)));
    }
}
