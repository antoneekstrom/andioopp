package andioopp.model.tower.attack;

import andioopp.common.storage.ArrayListFactory;
import andioopp.common.time.Time;
import andioopp.common.transform.Vector3f;
import andioopp.model.FilterImmunity;
import andioopp.model.FilterRequirement;
import andioopp.model.enemy.Enemy;
import andioopp.model.world.World;

import java.util.ArrayList;
import java.util.Collection;

/**
 * An attack.
 * Belongs to a tower.
 */
public abstract class Attack {
    private final float coolDown;
    private final AttackTargetArea targetArea;
    //Enums
    public ArrayList<FilterRequirement> requirements = new ArrayList<>();
    public ArrayList<FilterImmunity> immunty = new ArrayList<>();
    protected float timeOfLastAttack;

    public Attack(float coolDown, AttackTargetArea targetArea) {
        this.coolDown = coolDown;
        this.targetArea = targetArea;

    }

    /**
     * Executes the attack. Can vary depending on the attack.
     * Usually spawns a projectile in the game world.
     *
     * @param world
     * @param position position of the tower, or wherever the attack is to be performed
     */
    public abstract void performAttack(World world, Vector3f position);

    /**
     * Checks if the tower has waited long enough since its last attak.
     *
     * @param time the current time
     * @return true if enough time has passed since the last attack.
     */
    public boolean isAvailableForAttack(Time time) {
        float deltaSeconds = time.getElapsedSeconds() - timeOfLastAttack;
        return (deltaSeconds > this.coolDown);
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

    /**
     * Checks if the attack can hit the current enemy.
     *
     * @param enemy the enemy in question.
     * @return true if it can attack the current enemy.
     */
    public boolean hasMatchingRequirements(Enemy enemy) {
        for (int i = 0; i < requirements.size(); i++) {
            FilterRequirement r = requirements.get(i);
            for (int j = 0; j < enemy.requirements.size(); j++) {
                FilterRequirement e = enemy.requirements.get(j);
                if (r.equals(e)) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Checks if the enemy is immune to the current attack.
     *
     * @param enemy the enemy in question.
     * @return true if it is immune.
     */
    public boolean isImmune(Enemy enemy) {
        if (enemy.immunity.isEmpty()) { //if enemy immunity list is empty => Its not immune.
            return false;
        } else {
            for (int i = 0; i < immunty.size(); i++) {
                FilterImmunity imm = immunty.get(i);

                for (int j = 0; j < enemy.immunity.size(); j++) {
                    FilterImmunity EnemyImm = enemy.immunity.get(j);

                    if (imm.equals(EnemyImm)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }
}
