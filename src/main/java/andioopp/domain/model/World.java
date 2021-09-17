package andioopp.domain.model;

import andioopp.common.time.Time;
import andioopp.domain.model.enemy.Enemy;
import andioopp.domain.model.tower.TargetingStrategy;
import andioopp.domain.model.tower.Tower;
import javafx.util.Pair;

import java.util.Collection;
import java.util.List;
import java.util.ArrayList;

public class World implements Updateable {
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

    @Override
    public void update(Time time) {
        for (int i = 0; i < lanes.size(); i++){
            for (Cell cell : lanes.get(i).getCells()) {
                Tower tower = cell.getTower();
                if (tower != null) {
                    if (tower.canAttack(getEnemiesFromStrategy(tower.getTargetingStrategy()))) {
                        tower.attack();
                    }
                    // ge mario avståndet till närmaste fiefacnde (eller kanske lista med fiender i lanen)
                    // mario får svara om han kan attackera
                    // om mario kan attackera så kallar lane på mario's attack metod

                    // exempel:  if (mario.canAttack(enemies)) mario.attack()
                }
            }
        }
    }

    private Collection<Enemy> getEnemiesFromStrategy(TargetingStrategy strat) {
        return null;
    }
}
