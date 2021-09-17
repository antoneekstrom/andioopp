package andioopp.domain.model;

import andioopp.common.time.Time;
import andioopp.domain.model.enemy.Enemy;
import andioopp.domain.model.tower.Tower;

import java.util.Collection;
import java.util.List;
import java.util.Objects;

public class Lane implements Updateable {
    private final List<Cell> cells;
    private final Collection<Enemy> enemies;

    Lane(List<Cell> cells, Collection<Enemy> enemies) {
        this.cells = cells;
        this.enemies = enemies;
    }

    @Override
    public void update(Time time) {

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
