package andioopp.model.tower.towers;

import andioopp.model.enemy.Enemy;
import andioopp.model.tower.Tower;
import andioopp.model.tower.attack.Attack;
import andioopp.model.tower.attack.attacks.FireballAttack;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Arrays;


public class Mario extends Tower {

    private static final String SPRITE_PATH = "mario.png";
    //private static final Attack[] attackList = {new FireballAttack(0.5f)};


    //TODO se toad, attacklistan kan inte vara statisk typ, för då finns bara en instans av attacken


    public Mario() {
        super(SPRITE_PATH, "Mario", 60, 5, new ArrayList<>(Arrays.asList(new FireballAttack(500f))));

    }
}
