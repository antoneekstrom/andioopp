package andioopp.model;

import andioopp.common.time.Time;
import andioopp.model.enemy.Enemy;

import java.util.Collection;
import java.util.List;

public class World implements Updateable {

    private final List<Lane> lanes;
    private final Collection<Enemy> enemies;

    World(List<Lane> lanes, Collection<Enemy> enemies) {
        this.lanes = lanes;
        this.enemies = enemies;
    }

    @Override
    public void update(Time time) {
        getEnemies().forEach((enemy) -> enemy.update(time));
    }

    public void addEnemy(Enemy enemy) {
        enemies.add(enemy);
    }

    public Collection<Enemy> getEnemies() {
        return enemies;
    }

    public Lane getLane(int row) {
        return getLanes().get(row);
    }

    public Cell getCell(int row, int col) {
        return getLane(row).getCell(col);
    }

    public int getNumberOfLanes() {
        return lanes.size();
    }

    public int getNumberOfCellsInLanes() {
        return lanes.get(0).getNumberOfCells();
    }

    public List<Lane> getLanes() {
        return lanes;
    }
}
