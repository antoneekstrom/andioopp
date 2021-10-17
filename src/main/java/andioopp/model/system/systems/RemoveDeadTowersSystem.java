package andioopp.model.system.systems;

import andioopp.common.time.Time;
import andioopp.model.Model;
import andioopp.model.domain.enemy.Enemy;
import andioopp.model.domain.world.Cell;
import andioopp.model.system.System;

public class RemoveDeadTowersSystem implements System<Model> {

    @Override
    public void update(Model model, Time time) {
        for (Cell cell : model.getWorld().getCells()) {
            if(cell.hasTower() && cell.getTower().getHealth().isZero()) {
                //This removes the tower.
                cell.setTower(null);
            }
        }
    }
}
