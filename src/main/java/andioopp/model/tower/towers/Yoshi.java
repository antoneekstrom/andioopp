package andioopp.model.tower.towers;

import andioopp.model.tower.Tower;
import andioopp.model.tower.attack.Attack;
import andioopp.model.tower.attack.attacks.FireballAttack;

import java.util.ArrayList;
import java.util.Arrays;

public class Yoshi extends Tower {

    private static final String SPRITE_PATH = "yoshi-removebg-preview.png";
    private static final Attack[] attackList = {new FireballAttack(0.5f)};



    public Yoshi() {
        super(SPRITE_PATH, "Yoshi", 83, 9, new ArrayList<>(Arrays.asList(new FireballAttack(4f))));

    }
}
