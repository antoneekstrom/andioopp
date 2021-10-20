package andioopp.model.domain.world;

import andioopp.model.domain.tower.Tower;

/**
 * A cell in a {@link Lane}. One can only place one tower at a time in a cell.
 * Is the width and height of one unit in the model.
 */
public class Cell {
    private Tower tower;

    /**
     * Adds a Tower to a cell.
     */
    public void setTower(Tower tower) {
        this.tower = tower;
    }

    /**
     * Checks if Cell has a Tower.
     */
    public boolean hasTower() {
        return tower != null;
    }

    /**
     * Returns a Tower from a Cell.
     */
    public Tower getTower() {
        return tower;
    }
}
