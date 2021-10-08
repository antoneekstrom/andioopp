package andioopp.control;

import andioopp.model.tower.Tower;

public class TowerDragEvent {

    private final Tower tower;

    public TowerDragEvent(Tower tower) {
        this.tower = tower;
    }

    public Tower getTower() {
        return tower;
    }
}
