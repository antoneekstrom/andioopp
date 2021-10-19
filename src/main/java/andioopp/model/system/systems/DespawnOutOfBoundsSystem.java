package andioopp.model.system.systems;

import andioopp.common.time.Time;
import andioopp.model.Model;
import andioopp.model.domain.world.World;
import andioopp.model.system.System;
import andioopp.model.util.ModelCoordinate;

public class DespawnOutOfBoundsSystem implements System<Model> {

    @Override
    public void update(Model model, Time time) {
        World world = model.getWorld();
        despawnOutOfBoundsProjectiles(world);
        despawnOutOfBoundsEnemies(world);
    }

    private void despawnOutOfBoundsProjectiles(World world) {
        //Checks if a projectile is out of bounds and removes it if true.
        world.getProjectiles().removeIf(projectile -> projectile.getPosition().getX() > world.getNumberOfCellsInLanes());
    }

    private void despawnOutOfBoundsEnemies(World world) {
        //Checks if a projectile is out of bounds and removes it if true.
        world.getEnemies().removeIf(enemy -> {
            boolean enemyOutOfOfBounds = enemy.getPosition().getX() < -1;

            if(enemyOutOfOfBounds) {
                //TODO notify observers
            }
            return enemyOutOfOfBounds;
        });
    }
}
