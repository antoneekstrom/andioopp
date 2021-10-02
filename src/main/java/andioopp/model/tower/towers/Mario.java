package andioopp.model.tower.towers;

import andioopp.model.FilterImmunity;
import andioopp.model.FilterRequirement;
import andioopp.model.enemy.Enemy;
import andioopp.model.tower.TargetingStrategy;
import andioopp.model.tower.Tower;
import andioopp.model.tower.attack.Attack;
import andioopp.model.tower.attack.attacks.FireballAttack;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Arrays;


public class Mario extends Tower {

    private static final String SPRITE_PATH = "mario.png";
    private static final Attack[] attackList = {new FireballAttack(0.5f)};
    private String name = "Mario";


    public Mario() {
        super(SPRITE_PATH, 60, 5, new ArrayList<>(Arrays.asList(attackList)));

    }

    public boolean canAttack(Collection<Enemy> enemies){
        return true;
    }
    public String getName(){
        return name;
    }
}
