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

    public String name = "Mario";
    public int cost = 100;


    //TODO se toad, attacklistan kan inte vara statisk typ, för då finns bara en instans av attacken


    public Mario() {
        super(SPRITE_PATH, 60, 5, new ArrayList<>(Arrays.asList(new FireballAttack(4f))));

    }

    public boolean canAttack(Collection<Enemy> enemies){
        return true;
    }
    public String getName(){
        return name;
    }
    public int getCost(){ return cost; }

}
