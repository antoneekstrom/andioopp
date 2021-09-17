package andioopp.domain.model;

import andioopp.common.storage.ListFactory;
import andioopp.domain.model.enemy.Enemy;

import java.util.Collection;
import java.util.List;

public class LaneBuilder {

    private final ListFactory listFactory;

    private Collection<Enemy> enemies;
    private int numCells;

    public LaneBuilder(ListFactory listFactory) {
        this.listFactory = listFactory;
        this.enemies = getListFactory().create();
    }

    public Lane build() {
        return new Lane(getListFactory().create(getCells()));
    }

    public LaneBuilder setCells(int numCells) {
        this.numCells = numCells;
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

    private List<Cell> getCells() {
        return getListFactory().create(getNumCells(), this::getCell);
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

    private int getNumCells() {
        return numCells;
    }
}
