package andioopp.model.system.events;

import andioopp.model.domain.enemy.Enemy;

public class EnemyEscapeEvent {
    private final Enemy enemy;

    public EnemyEscapeEvent(Enemy enemy) {
        this.enemy = enemy;
    }

    public Enemy getEnemy() {
        return enemy;
    }
}
