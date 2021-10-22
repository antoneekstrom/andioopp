package andioopp.model.domain.world;

import andioopp.common.storage.ListFactory;
import andioopp.model.domain.enemy.Enemy;

import java.util.Collection;
import java.util.List;

/**
 * Creates lanes.
 */
public class LaneBuilder {

    private final ListFactory listFactory;

    private Collection<Enemy> enemies;
    private int numCells;

    public LaneBuilder(ListFactory listFactory) {
        this.listFactory = listFactory;
        this.enemies = getListFactory().create();
    }

    /**
     * Builds a Lane.
     */
    public Lane build() {
        return new Lane(getListFactory().create(getCells()));
    }

    /**
     * Returns LaneBuilder itself and sets the amount of Cells for the Lanes.
     * @param numCells the amount of Cells.
     */
    public LaneBuilder setCells(int numCells) {
        this.numCells = numCells;
        return this;
    }

    /**
     * Returns LaneBuilder itself and adds an enemy to the Collection enemies.
     * @param enemy the enemy added to the Collection.
     */
    public LaneBuilder addEnemy(Enemy enemy) {
        getEnemies().add(enemy);
        return this;
    }

    /**
     * Returns LaneBuilder itself and updates the Collection enemies.
     * @param enemies
     */
    public LaneBuilder setEnemies(Collection<Enemy> enemies) {
        this.enemies = enemies;
        return this;
    }

    private List<Cell> getCells() {
        return getListFactory().create(getNumCells(), this::getCell);
    }

    /**
     * Returns the Collection enemies consisting of Enemies.
     * @return
     */
    public Collection<Enemy> getEnemies() {
        return enemies;
    }

    /**
     * Returns a ListFactory.
     */
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
