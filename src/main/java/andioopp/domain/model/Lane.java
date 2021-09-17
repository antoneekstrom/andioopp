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

    public int getNumberOfCells() {
        return getCells().size();
    }

    public Cell getCell(int col) {
        return getCells().get(col);
    }

    public Collection<Enemy> getEnemies() {
        return enemies;
    }

    private List<Cell> getCells() {
        return cells;
    }
}
