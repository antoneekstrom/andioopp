package andioopp.model.system.systems;

import andioopp.common.time.Time;
import andioopp.game.MarioGame;
import andioopp.model.Model;
import andioopp.model.domain.world.World;
import andioopp.model.system.System;
import andioopp.model.util.ModelCoordinate;

/**
 * A class that handles when Objects move out of bounds.
 */
public class DespawnOutOfBoundsSystem implements System<Model> {

    private final MarioGame marioGame;

    public DespawnOutOfBoundsSystem(MarioGame marioGame) {
        this.marioGame = marioGame;
    }

    @Override
    public void update(Model model, Time time) {
        World world = model.getWorld();
        despawnOutOfBoundsProjectiles(world);
        despawnOutOfBoundsEnemies(world);
    }

    /**
     * Checks if a projectile is out of bounds and removes it if true.
     */
    private void despawnOutOfBoundsProjectiles(World world) {
        world.getProjectiles().removeIf(projectile -> projectile.getPosition().getX() > world.getNumberOfCellsInLanes());
    }

    /**
     * Checks if a enemy is out of bounds and removes it if true.
     */
    private void despawnOutOfBoundsEnemies(World world) {
        //Checks if a projectile is out of bounds and removes it if true.
        world.getEnemies().removeIf(enemy -> {
            boolean enemyOutOfBounds = enemy.getPosition().getX() < -1;

            if(enemyOutOfBounds) {
                marioGame.setGameOver(true);
            }

            return enemyOutOfBounds;
        });
    }
}
