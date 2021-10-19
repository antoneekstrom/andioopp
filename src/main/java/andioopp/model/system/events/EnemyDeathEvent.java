package andioopp.model.system.events;

import andioopp.model.domain.enemy.Enemy;

public class EnemyDeathEvent {
    private final Enemy enemy;

    public EnemyDeathEvent(Enemy enemy) {
        this.enemy = enemy;
    }

    public Enemy getEnemy() {
        return enemy;
    }
}
