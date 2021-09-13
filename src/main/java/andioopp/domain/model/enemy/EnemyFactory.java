package andioopp.domain.model.enemy;

import andioopp.domain.model.enemy.enemies.Goomba;

public class EnemyFactory {

    public EnemyFactory() {
    }

    public Enemy goomba() {
        return new Goomba();
    }

}
