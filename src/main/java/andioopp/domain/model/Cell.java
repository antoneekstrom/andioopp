package andioopp.domain.model;

import andioopp.domain.model.tower.Tower;

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
