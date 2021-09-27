package andioopp.model.tower.attack.strategies;

import andioopp.common.transform.Vector3f;
import andioopp.model.tower.attack.AttackTargetArea;

public class SingleLaneForward implements AttackTargetArea {

    @Override
    public boolean enemyIsInRange(Vector3f towerPosition, Vector3f enemyPosition) {
        return ( enemyPosition.getY() == towerPosition.getY() && enemyPosition.getX() > towerPosition.getX() );
    }
}
