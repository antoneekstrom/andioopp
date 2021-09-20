package andioopp.model.tower.towers;

import andioopp.model.enemy.Enemy;
import andioopp.model.tower.TargetingStrategy;
import andioopp.model.tower.Tower;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Arrays;


public class Mario extends Tower {

    private static final String SPRITE_PATH = "mario.png";
    private static final int BASE_HEALTH = 7;
    private static final int BASE_COST = 2;
    private static final int BASE_RANGE = -1;
    private static ArrayList<Integer> targetedLanes = new ArrayList<>(Arrays.asList(0));

    public Mario() {
        super(SPRITE_PATH, BASE_RANGE, BASE_COST, BASE_HEALTH, targetedLanes);
    }

    @Override
    public TargetingStrategy getTargetingStrategy() {
        return null;
    }

    public boolean canAttack(Collection<Enemy> enemies){

        return true;
    }
}
