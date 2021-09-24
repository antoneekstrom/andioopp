package andioopp.model.tower.attack;

import andioopp.common.transform.Vector3f;
import andioopp.model.enemy.Enemy;
import andioopp.model.tower.Tower;

import javax.swing.text.Position;
import java.util.Vector;

public interface AttackTargetArea {
    boolean enemyIsInRange(Vector3f towerPosition, Vector3f enemyPosition);
}
