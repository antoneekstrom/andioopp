package andioopp.domain.model;

import andioopp.common.storage.ListFactory;
import andioopp.domain.model.enemy.Enemy;

import java.util.Collection;
import java.util.List;

public class LaneBuilder {

    private final ListFactory listFactory;

    private Collection<Enemy> enemies;
    private List<Cell> cells;

    public LaneBuilder(ListFactory listFactory) {
        this.listFactory = listFactory;
        this.enemies = getListFactory().create();
    }

    public Lane build() {
        return new Lane(getListFactory().create(getCells()), getListFactory().create(getEnemies()));
    }

    public LaneBuilder setCells(int numCells) {
        return setCells(getListFactory().create(numCells, this::getCell));
    }

    public LaneBuilder setCells(List<Cell> cells) {
        this.cells = cells;
        return this;
    }

    public LaneBuilder addEnemy(Enemy enemy) {
        getEnemies().add(enemy);
        return this;
    }

    public LaneBuilder setEnemies(Collection<Enemy> enemies) {
        this.enemies = enemies;
        return this;
    }

    public List<Cell> getCells() {
        return cells;
    }

    public Collection<Enemy> getEnemies() {
        return enemies;
    }

    public ListFactory getListFactory() {
        return listFactory;
    }

    private Cell getCell() {
        return new Cell();
    }
}
