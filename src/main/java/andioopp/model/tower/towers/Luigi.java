package andioopp.model.tower.towers;

import andioopp.model.enemy.Enemy;
import andioopp.model.tower.attack.Attack;
import andioopp.model.tower.attack.attacks.FireballAttack;
import andioopp.model.enemy.Enemy;
import andioopp.model.tower.Tower;
import andioopp.model.tower.attack.Attack;
import andioopp.model.tower.attack.attacks.FireballAttack;

import java.util.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

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
