package andioopp.model.domain.tower.attack;

import andioopp.common.math.Vector3f;
import andioopp.common.storage.ArrayListFactory;
import andioopp.common.time.Time;
import andioopp.model.Model;
import andioopp.model.domain.damage.DamageSource;
import andioopp.model.domain.damage.DamageType;
import andioopp.model.domain.enemy.Enemy;

import java.util.Collection;
import java.util.List;

/**
 * An attack.
 */
public abstract class Attack implements DamageSource {
    private final float cooldown;
    private final AttackTargetArea targetArea;
    private final DamageSource damageSource;
    protected float timeOfLastAttack;

    public Attack(float cooldown, AttackTargetArea targetArea, DamageSource damageSource) {
        this.cooldown = cooldown;
        this.targetArea = targetArea;
        this.damageSource = damageSource;
    }

    @Override
    public List<DamageType> getTypes() {
        return damageSource.getTypes();
    }

    /**
     * Executes the attack. Can vary depending on the attack.
     * Usually spawns a projectile in the game model.
     *
     * @param model
     * @param position position of the tower, or wherever the attack is to be performed
     */
    public abstract void performAttack(Model model, Vector3f position);

    /**
     * Checks if the tower has waited long enough since its last attak.
     *
     * @param time the current time
     * @return true if enough time has passed since the last attack.
     */
    public boolean isAvailableForAttack(Time time) {
        float deltaSeconds = time.getElapsedSeconds() - timeOfLastAttack;
        return (deltaSeconds > this.cooldown);
    }

    /**
     * Sets the time of the last attack.
     *
     * @param time the current time
     */
    public void updateTimeOfLastAttack(Time time) {
        this.timeOfLastAttack = time.getElapsedSeconds();
    }

    /**
     * Uses the attacks targetArea to make a list of which towers can attack.
     *
     * @param world
     * @param position position of the attacking tower
     * @return a collection of enemies in range
     */
    public Collection<Enemy> getEnemiesInRange(World world, Vector3f position) {
        Collection<Enemy> enemiesInRange = new ArrayListFactory().create();
        for (Enemy enemy : world.getEnemies()) {

            if (targetArea.enemyIsInRange(position, enemy.getPosition(), world)) {
                enemiesInRange.add(enemy);
            }
        }
        return enemiesInRange;
    }
}
