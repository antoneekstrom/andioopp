package andioopp.model.domain.tower.attack.attacks;

import andioopp.common.javafx.time.FxClock;
import andioopp.common.math.vector.Vector3f;
import andioopp.common.storage.ArrayListFactory;
import andioopp.common.time.Time;
import andioopp.model.Model;
import andioopp.model.domain.damage.DamageSource;
import andioopp.model.domain.damage.DamageType;
import andioopp.model.domain.enemy.Enemy;
import andioopp.model.domain.tower.attack.strategies.AttackTargetArea;
import andioopp.model.domain.world.World;
import andioopp.model.util.ModelCoordinate;

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

    /**
     * Constructor for Attack.
     * @param cooldown time you have to wait before an attack will be performed.
     * @param targetArea area where the attack will hit.
     * @param damageSource which DamageTypes the attack will attack.
     */
    public Attack(float cooldown, AttackTargetArea targetArea, DamageSource damageSource) {
        this.cooldown = cooldown;
        this.targetArea = targetArea;
        this.damageSource = damageSource;
        timeOfLastAttack = FxClock.nanosToSeconds(FxClock.getNowTimeNanos());
    }

    @Override
    public List<DamageType> getTypes() {
        return damageSource.getTypes();
    }

    public void perform(Time time, Model model, ModelCoordinate position) {
        if (isAvailableForAttack(time)) {
            onAttack(model, position);
            updateTimeOfLastAttack(time);
        }
    }

    /**
     * Executes the attack. Can vary depending on the attack.
     * Usually spawns a projectile in the game model.
     *
     * @param model
     * @param position position of the tower, or wherever the attack is to be performed
     */
    protected abstract void onAttack(Model model, Vector3f position);

    /**
     * Checks if the tower has waited long enough since its last attack.
     *
     * @param time the current time
     * @return true if enough time has passed since the last attack.
     */
    public boolean isAvailableForAttack(Time time) {
        float deltaSeconds = time.getTime() - timeOfLastAttack;
        return (deltaSeconds > this.cooldown);
    }

    /**
     * Sets the time of the last attack.
     *
     * @param time the current time
     */
    private void updateTimeOfLastAttack(Time time) {
        this.timeOfLastAttack = time.getTime();
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

    /**
     * Returns the tower's targeting strategy
     *
     * @return the targeting strategy of the tower
     */
    public AttackTargetArea getTargetArea() {
        return targetArea;
    }
}
