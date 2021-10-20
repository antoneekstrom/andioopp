package andioopp.model.system.events;

import andioopp.common.math.vector.Vector3f;
import andioopp.model.domain.enemy.Enemy;

/**
 * Emitted by {@link andioopp.model.system.systems.RemoveDeadEnemiesSystem} when an enemy dies.
 *
 * @author Anton Ekström
 * @see andioopp.model.system.systems.RemoveDeadEnemiesSystem
 * @see andioopp.model.services.EnemyDropCoinService
 */
public class EnemyDeathEvent {

    private final Enemy enemy;

    public EnemyDeathEvent(Enemy enemy) {
        this.enemy = enemy;
    }

    public Vector3f getPosition() {
        return enemy.getPosition();
    }

    public Enemy getEnemy() {
        return enemy;
    }
}
