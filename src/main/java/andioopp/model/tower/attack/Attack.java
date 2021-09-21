package andioopp.model.tower.attack;

import andioopp.common.time.Time;
import andioopp.common.storage.ArrayListFactory;
import andioopp.model.World;
import andioopp.model.enemy.Enemy;
import andioopp.model.tower.Tower;

import java.util.Collection;

public abstract class Attack {
    private final float coolDown;
    private float timeSinceLastAttack;

    public Attack(float coolDown) {
        this.coolDown = coolDown;
    }

    public abstract void performAttack(Tower tower);

    public boolean isAvailableForAttack(Time time){
        float deltaSeconds = time.getElapsedSeconds() - timeSinceLastAttack;
        return(deltaSeconds > this.coolDown);
    }

    public void updateTimeSinceLastAttack(Time time) {
        this.timeSinceLastAttack = time.getElapsedSeconds();
    }

    public abstract AttackTargetArea getTargetArea();
    private int row;
    private int col;

    public Attack(int row, int col) {
        this.row = row;
        this.col = col;
    }

    public Collection<Enemy> getEnemiesInRange(World world) {
        Collection<Enemy> enemiesInRange = new ArrayListFactory().create();
        for ( Enemy enemy : world.getEnemies() ) {
            if ( getTargetArea().enemyIsInRange(row, col, enemy.getPosition()) ){
                enemiesInRange.add(enemy);
            }
        }
        return enemiesInRange;
    }
}
