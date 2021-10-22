package andioopp.model.domain.tower.attack.strategies;

import andioopp.common.math.vector.Vector3f;
import andioopp.model.domain.world.World;

/**
 * Targeting area that extends a certain range in a single lane infront of the tower.
 */
public class SingleLaneForward implements AttackTargetArea {

    float enemyPosOffset = 0.4f;
    float maxRange;

    public SingleLaneForward(float maxRange) {
        this.maxRange = maxRange;
    }

    @Override
    public boolean enemyIsInRange(Vector3f towerPosition, Vector3f enemyPosition, World world) {

        if (maxRange > world.getNumberOfCellsInLanes()) {
            maxRange = world.getNumberOfCellsInLanes();
        }
        // Makes sure the enemy and tower is in the same lane and enemy is infront of tower.
        // Offset makes sure that the tower wont attack a enemy that appears to be out of the map.
        return (enemyPosition.getY() == towerPosition.getY() && enemyPosition.getX() > towerPosition.getX() && enemyPosition.getX() < towerPosition.getX() + maxRange - enemyPosOffset);
    }
}
