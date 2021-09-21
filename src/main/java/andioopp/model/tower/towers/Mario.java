package andioopp.model.tower.towers;

import andioopp.model.enemy.Enemy;
import andioopp.model.tower.TargetingStrategy;
import andioopp.model.tower.Tower;
import andioopp.model.tower.attack.Attack;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Arrays;


public class Mario extends Tower {

    ArrayList<Attack> list = new ArrayList();

    private static final String SPRITE_PATH = "mario.png";
    private static final int BASE_HEALTH = 7;
    private static final int BASE_COST = 2;
    private static final int BASE_RANGE = -1;
    private static ArrayList<Integer> targetedLanes = new ArrayList<>(Arrays.asList(0));

    private static final ArrayList<Enum> requirements = new ArrayList<>();
    private static final ArrayList<Enum> immunity = new ArrayList<>();

    public Mario() {
        super(SPRITE_PATH, BASE_RANGE, BASE_COST, BASE_HEALTH, targetedLanes);
        requirements.add(REQUIREMENT.GROUND);
        immunity.add(IMMUNITY.FIREBALL);
    }

    public boolean canAttack(Collection<Enemy> enemies){
        return true;
    }

}
