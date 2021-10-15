package andioopp.model.domain.tower.towers;

import andioopp.model.domain.tower.attack.Attack;
import andioopp.model.domain.tower.attack.attacks.FireballAttack;
import andioopp.model.domain.tower.Tower;

import java.util.*;
import java.util.Arrays;

public class Luigi extends Tower{
    private static final String SPRITE_PATH = "luigi.png";
    private static final Attack[] attackList = {new FireballAttack(0.5f)};

    public Luigi() {
        super(SPRITE_PATH, "Luigi", 200, 5);
    }

    @Override
    protected List<Attack> createAttacks() {
        return Arrays.asList(new FireballAttack(4f));
    }
}
