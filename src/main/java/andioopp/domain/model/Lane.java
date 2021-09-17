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
        for (Cell cell : getCells()) {
            Tower tower = cell.getTower();
            if (tower != null) {
                // ge mario avståndet till närmaste fiende (eller kanske lista med fiender i lanen)
                // mario får svara om han kan attackera
                // om mario kan attackera så kallar lane på mario's attack metod

                // exempel:  if (mario.canAttack(enemies)) mario.attack()
            }
        }
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
