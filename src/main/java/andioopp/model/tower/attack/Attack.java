package andioopp.model.tower.attack;

import andioopp.common.storage.ArrayListFactory;
import andioopp.model.World;
import andioopp.model.enemy.Enemy;
import andioopp.model.tower.Tower;

import java.util.Collection;

public abstract class Attack {
    public abstract void performAttack(Tower tower);
    public abstract boolean isAvailable();
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
