package andioopp.model.world;

import andioopp.model.tower.Tower;

/**
 * A cell in a {@link Lane}. One can only place one tower at a time in a cell.
 * Is the width and height of one unit in the model.
 */
public class Cell {
    private Tower tower;

    public void setTower(Tower tower) {
        this.tower = tower;
    }

    public boolean hasTower() {
        return tower != null;
    }

    public Tower getTower() {
        return tower;
    }
}
