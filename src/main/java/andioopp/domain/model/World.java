package andioopp.domain.model;

import andioopp.common.time.Time;
import andioopp.domain.model.enemy.Enemy;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class World implements Updateable {

    private final List<Lane> lanes;

    World(List<Lane> lanes) {
        this.lanes = lanes;
    }

    @Override
    public void update(Time time) {
        getEnemies().forEach((enemy) -> enemy.update(time));
    }

    public Collection<Enemy> getEnemies() {
        return getLanes()
            .stream()
            .map(Lane::getEnemies)
            .flatMap(Collection::stream)
            .collect(Collectors.toList());
    }

    public Lane getLane(int row) {
        return getLanes().get(row);
    }

    public Cell getCell(int row, int col) {
        return getLane(row).getCell(col);
    }

    public int getNumberOfLanes() {
        return lanes.size();
    }

    public int getNumberOfCellsInLanes() {
        return lanes.get(0).getNumberOfCells();
    }

    public List<Lane> getLanes() {
        return lanes;
    }
}
