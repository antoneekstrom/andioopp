package andioopp.model.tower.attack;

import andioopp.common.time.Time;
import andioopp.common.storage.ArrayListFactory;
import andioopp.common.transform.Vector3f;
import andioopp.model.World;
import andioopp.model.enemy.Enemy;
import andioopp.model.tower.Tower;

import java.util.Collection;

public abstract class Attack {
    private final World world;
    private final float coolDown;
    private float timeSinceLastAttack;

    public abstract AttackTargetArea getTargetArea();
    //TODO Hej jag fattar inte varför denna heter getTargetArea, det är väl ingen getter tack tack //Jacob

    private final Vector3f position;

    public Attack(World world, float coolDown, Vector3f position) {
        this.world = world;
        this.coolDown = coolDown;
        this.position = position;
    }

    public abstract void performAttack(Tower tower);

    public boolean isAvailableForAttack(Time time){
        float deltaSeconds = time.getElapsedSeconds() - timeSinceLastAttack;
        return(deltaSeconds > this.coolDown);
    }

    public void updateTimeSinceLastAttack(Time time) {
        this.timeSinceLastAttack = time.getElapsedSeconds();
    }

    public Collection<Enemy> getEnemiesInRange(World world) {
        Collection<Enemy> enemiesInRange = new ArrayListFactory().create();
        for ( Enemy enemy : world.getEnemies() ) {

            if ( getTargetArea().enemyIsInRange( position, enemy.getPosition()) ){
                enemiesInRange.add(enemy);
            }
        }
        return enemiesInRange;
    }

    public World getWorld(){
        return world;
    }

    public Vector3f getPosition(){
        return position;
    }
}
