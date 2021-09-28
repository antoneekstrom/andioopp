package andioopp.model.tower.attack.strategies;

import andioopp.common.transform.Vector3f;
import andioopp.model.World;
import andioopp.model.enemy.Enemy;
import andioopp.model.tower.Tower;
import andioopp.model.tower.attack.AttackTargetArea;

import java.util.Collection;
import java.util.Vector;

public class SingleLaneForward implements AttackTargetArea {

    @Override
    public boolean enemyIsInRange(Vector3f towerPosition, Vector3f enemyPosition) {
        return ( enemyPosition.getY() == towerPosition.getY() && enemyPosition.getX() > towerPosition.getX() );
    }
}
