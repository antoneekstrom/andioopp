package andioopp.model.tower.attack;

import andioopp.common.transform.Vector3f;
import andioopp.model.enemy.Enemy;
import andioopp.model.tower.Tower;

import javax.swing.text.Position;
import java.util.Vector;

public interface AttackTargetArea {
    boolean enemyIsInRange(int row, int col, Vector3f enemyPosition);
}
