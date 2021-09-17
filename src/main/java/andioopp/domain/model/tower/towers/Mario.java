package andioopp.domain.model.tower.towers;

import andioopp.domain.model.enemy.Enemy;
import andioopp.domain.model.tower.Tower;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Arrays;
import java.util.List;



public class Mario extends Tower {

    private static final String SPRITE_PATH = "mario.png";
    private static final int BASE_HEALTH = 7;
    private static final int BASE_COST = 2;
    private static final int BASE_RANGE = -1;

    public Mario() {
        super(SPRITE_PATH, BASE_RANGE, BASE_COST, BASE_HEALTH, targetedLanes);
    }

    public boolean canAttack(Collection<Enemy> enemies){

        return true;
    }
}