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
    public boolean enemyIsInRange(int row, int col, Vector3f enemyPosition) {
        return ( enemyPosition.getY() == row && enemyPosition.getX() > col );
    }
}
