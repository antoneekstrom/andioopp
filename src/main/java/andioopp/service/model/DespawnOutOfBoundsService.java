package andioopp.service.model;

import andioopp.common.time.Time;
import andioopp.model.Model;
import andioopp.model.world.World;

public class DespawnOutOfBoundsService extends ModelService {

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
        world.getEnemies().removeIf(enemy -> enemy.getPosition().getX() < 0);
    }
}
