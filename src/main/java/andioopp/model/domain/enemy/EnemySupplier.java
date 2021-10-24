package andioopp.model.domain.enemy;

import andioopp.model.domain.world.World;

/**
 * Creates enemies.
 *
 * @author Anton Ekström
 */
@FunctionalInterface
public interface EnemySupplier {
    /**
     * Returns an enemy on the specified lane in the {@link World}. Does not actually spawn the enemy into world.
     *
     * @param world the world
     * @param row the row in which to place the enemy
     * @return the enemy
     */
    Enemy get(World world, int row);
}
