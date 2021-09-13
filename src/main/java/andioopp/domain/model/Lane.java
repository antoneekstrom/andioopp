package andioopp.domain.model;

import andioopp.common.transform.Transform;
import andioopp.domain.model.enemy.Enemy;

import java.util.ArrayList;
import java.util.Collection;

public class Lane {

    private final Collection<Cell> cells = new ArrayList<>();
    private final Collection<Enemy> enemies = new ArrayList<>();

    private final Transform transform;

    public Lane(Transform transform) {
        this.transform = transform;
    }

    public Transform getTransform() {
        return transform;
    }

    public Collection<Enemy> getEnemies() {
        return enemies;
    }
}
