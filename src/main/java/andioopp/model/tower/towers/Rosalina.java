package andioopp.model.tower.towers;

import andioopp.model.tower.Tower;
import andioopp.model.tower.attack.Attack;
import andioopp.model.tower.attack.attacks.FireballAttack;

import java.util.ArrayList;
import java.util.Arrays;

public class Rosalina extends Tower {

    private static final String SPRITE_PATH = "rosalina-removebg-preview.png";
    private static final Attack[] attackList = {new FireballAttack(0.5f)};



    public Rosalina() {
        super(SPRITE_PATH, "Rosalina", 500, 10, new ArrayList<>(Arrays.asList(new FireballAttack(4f))));

    }
}
