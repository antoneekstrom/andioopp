package andioopp.model.domain.tower.attack.strategies;

import andioopp.common.math.Vector3f;
import andioopp.model.domain.tower.attack.AttackTargetArea;
import andioopp.model.domain.world.World;

public class SingleLaneForward implements AttackTargetArea {

    float enemyPosOffset = 0.4f;

    /**
     * Checks if the enemy is in front of the tower in the same lane.
     * @param towerPosition the tower's cell's position
     * @param enemyPosition the position of the enemy on the game grid
     * @param world
     * @return
     */
    @Override
    public boolean enemyIsInRange(Vector3f towerPosition, Vector3f enemyPosition, World world) {
        // Makes sure the enemy and tower is in the same lane and enemy is infront of tower.
        // Offset makes sure that the tower wont attack a enemy that appears to be out of the map.
        return (enemyPosition.getY() == towerPosition.getY() && enemyPosition.getX() > towerPosition.getX() && enemyPosition.getX() < world.getNumberOfCellsInLanes() - enemyPosOffset);
    }
}
