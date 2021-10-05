package andioopp.model.tower.attack.strategies;

import andioopp.common.transform.Vector3f;
import andioopp.model.tower.attack.AttackTargetArea;
import andioopp.model.world.World;

public class SingleLaneForward implements AttackTargetArea {

    float enemyPosOffset = 0.4f;


    @Override
    public boolean enemyIsInRange(Vector3f towerPosition, Vector3f enemyPosition, World world) {
        // Makes sure the enemy and tower is in the same lane and enemy is infront of tower.
        // Offset makes sure that the tower wont attack a enemy that appears to be out of the map.
        return (enemyPosition.getY() == towerPosition.getY() && enemyPosition.getX() > towerPosition.getX() && enemyPosition.getX() < world.getNumberOfCellsInLanes() - enemyPosOffset);
    }
}
