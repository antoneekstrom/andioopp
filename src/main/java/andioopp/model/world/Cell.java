package andioopp.model.world;

import andioopp.model.tower.Tower;

public class Cell {
    private Tower tower;

    public Cell() {}

    public void setTower(Tower tower) {
        this.tower = tower;
    }

    public Tower getTower() {
        return tower;
    }
}
