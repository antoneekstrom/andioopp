package andioopp.model.tower.towers;

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

    private static final ArrayList<Enum> requirements = new ArrayList<>();
    private static final ArrayList<Enum> immunity = new ArrayList<>();


    public Mario() {
        super(SPRITE_PATH, 60, 5, new ArrayList<>(Arrays.asList(attackList)));
        requirements.add(REQUIREMENT.GROUND);
        immunity.add(IMMUNITY.FIREBALL);
    }

    public boolean canAttack(Collection<Enemy> enemies){
        return true;
    }

}
