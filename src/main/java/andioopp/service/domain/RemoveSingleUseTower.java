package andioopp.service.domain;

import andioopp.common.time.Time;
import andioopp.model.Model;
import andioopp.model.enemy.Enemy;
import andioopp.model.tower.Tower;
import andioopp.model.tower.towers.Mario;
import andioopp.model.world.Cell;
import andioopp.model.world.World;

public class RemoveSingleUseTower extends DomainService{

    @Override
    public void update(Model model, Time time) {
        World world = model.getWorld();
        checkHealthStates(world);
    }

    private void checkHealthStates(World world) {
        checkEnemiesHealthState(world);
        checkTowersHealthState(world);
    }


    private void checkTowersHealthState(World world) {
        //Goes through all cells and its towers and removes the tower if its health is zero.
        for (Cell cell : world.getCells()) {
            if( cell.hasTower()  &&  cell.getTower().getHealth().isZero() ) {

                //This removes the tower.
                cell.setTower(null);

            }


        }
    }



    private void checkEnemiesHealthState(World world) {

        //removes enemy if its health is zero
        world.getEnemies().removeIf(enemy -> enemy.getHealth().isZero());

    }
}
