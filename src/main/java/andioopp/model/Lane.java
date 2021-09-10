package andioopp.model;

import andioopp.common.ConcreteTransform;
import andioopp.common.Transform;
import andioopp.gfx.Renderer;
import andioopp.gfx.Sprite;
import andioopp.model.enemies.Enemy;

import java.util.ArrayList;
import java.util.Collection;

public class Lane {

    private Collection<Cell> cells = new ArrayList<>();
    private Collection<Enemy<?>> enemies = new ArrayList<>();

    private Transform transform;

    public Lane(Transform transform) {
        this.transform = transform;
    }

    private Transform getTransform() {
        return transform;
    }

    public void addEnemy(Enemy<?> e) {
        enemies.add(e);
    }

    public Collection<Enemy<?>> getEnemies() {
        return enemies;
    }
}
