package andioopp.domain.model;

import andioopp.domain.model.tower.Tower;

import java.util.List;

public class World {
    private final List<Lane> lanes;

    World(List<Lane> lanes) {
        this.lanes = lanes;
    }

    public void placeTower(Tower tower, int row, int col) {
        Lane.getCell(getLanes(), row, col).setTower(tower);
    }

    public List<Lane> getLanes() {
        return lanes;
    }
}
