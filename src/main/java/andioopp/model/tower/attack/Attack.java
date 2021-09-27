package andioopp.model.tower.attack;

import andioopp.common.time.Time;
import andioopp.common.storage.ArrayListFactory;
import andioopp.common.transform.Vector3f;
import andioopp.model.world.World;
import andioopp.model.enemy.Enemy;

import java.util.Collection;

public abstract class Attack {
    private final float coolDown;
    private float timeSinceLastAttack;
    private final AttackTargetArea targetArea;

    public Attack(float coolDown, AttackTargetArea targetArea) {
        this.coolDown = coolDown;
        this.targetArea = targetArea;
    }

    public abstract void performAttack(World world, Vector3f position);

    public boolean isAvailableForAttack(Time time){
        float deltaSeconds = time.getElapsedSeconds() - timeSinceLastAttack;
        return(deltaSeconds > this.coolDown);
    }

    public void updateTimeSinceLastAttack(Time time) {
        this.timeSinceLastAttack = time.getElapsedSeconds();
    }

    public Collection<Enemy> getEnemiesInRange(World world, Vector3f position) {
        Collection<Enemy> enemiesInRange = new ArrayListFactory().create();
        for ( Enemy enemy : world.getEnemies() ) {

            if (targetArea.enemyIsInRange(position, enemy.getPosition())) {
                enemiesInRange.add(enemy);
            }
        }
        return enemiesInRange;
    }
}
