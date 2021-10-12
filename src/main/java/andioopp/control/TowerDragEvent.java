package andioopp.control;

import andioopp.model.tower.Tower;
import andioopp.model.tower.Towers;

public class TowerDragEvent {

    private final Tower tower;

    public TowerDragEvent(Tower tower) {
        this.tower = tower;
    }

    public Tower getTower() {
        return tower;
    }
}
