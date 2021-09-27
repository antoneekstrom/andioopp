package andioopp.model;

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
