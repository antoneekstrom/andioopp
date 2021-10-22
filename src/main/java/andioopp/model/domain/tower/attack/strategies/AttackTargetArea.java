package andioopp.model.domain.tower.attack.strategies;

import andioopp.common.math.vector.Vector3f;
import andioopp.model.domain.world.World;

public interface AttackTargetArea {
    /**
     * Calculates if an enemy is within the attack range of a tower's attack.
     * @param towerPosition the tower's cell's position
     * @param enemyPosition the position of the enemy on the game grid
     * @param world
     * @return true if the enemy can be hit with the current attack
     */
    boolean enemyIsInRange(Vector3f towerPosition, Vector3f enemyPosition, World world);
}
