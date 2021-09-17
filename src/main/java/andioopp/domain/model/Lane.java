package andioopp.domain.model;

import andioopp.domain.model.enemy.Enemy;

import java.util.Collection;
import java.util.List;

public class Lane {
    private final List<Cell> cells;
    private final Collection<Enemy> enemies;

    Lane(List<Cell> cells, Collection<Enemy> enemies) {
        this.cells = cells;
        this.enemies = enemies;
    }

    public static Cell getCell(List<Lane> lanes, int row, int col) {
        return lanes.get(row).getCells().get(col);
    }

    public Collection<Enemy> getEnemies() {
        return enemies;
    }

    public List<Cell> getCells() {
        return cells;
    }
}
