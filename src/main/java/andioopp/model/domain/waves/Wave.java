package andioopp.model.domain.waves;

import andioopp.model.domain.enemy.Enemy;
import andioopp.model.domain.enemy.EnemyFactory;
import andioopp.model.domain.enemy.EnemySupplier;
import andioopp.model.domain.world.World;

import java.util.LinkedList;
import java.util.Objects;
import java.util.Queue;

/**
 * A Wave contains enemies which are to be spawned in the {@link World}.
 *
 * @author Elin Nilsson
 * @see WaveQueue
 * @see World
 */
public class Wave {

    private final Queue<EnemySupplier> enemies;

    /**
     * Creates a wave.
     *
     * @param numberOfEnemies number of enemies
     */
    public Wave(int numberOfEnemies) {
        this.enemies = createEnemies(numberOfEnemies);
    }

    /**
     * Returns true if the wave is done and there are no enemies left.
     *
     * @return if the wave is done
     */
    public boolean isDone() {
        return enemies.size() == 0;
    }

    /**
     * Returns the number of enemies left in the wave.
     *
     * @return the number of enemies left
     */
    public int getEnemiesRemaining() {
        return enemies.size();
    }

    /**
     * Removes the next enemy from the wave and returns it.
     *
     * @return the enemy
     */
    public Enemy spawnNextEnemy(World world, int row) {
        EnemySupplier supplier = enemies.poll();
        return Objects.requireNonNull(supplier).get(world, row);
    }

    /**
     * Adds the given amount of random enemies to the wave.
     *
     * @param count the number of enemies
     * @return the randomly created enemies
     */
    private Queue<EnemySupplier> createEnemies(int count) {
        Queue<EnemySupplier> queue = new LinkedList<>();

        for (int i = 0; i < count; i++) {
            queue.add(EnemyFactory.randomEnemySupplier());
        }
        return queue;
    }

}