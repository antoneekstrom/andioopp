package andioopp.model.domain.waves;

import andioopp.common.math.range.IntRange;
import andioopp.model.domain.enemy.EnemyFactory;
import andioopp.model.domain.enemy.Enemy;
import andioopp.model.domain.world.World;

import java.util.Queue;
import java.util.Random;

import java.util.LinkedList;

/**
 * A Wave contains enemies which are to be spawned in the {@link World}.
 *
 * @author Elin Nilsson
 * @see WaveQueue
 * @see World
 */
public class Wave {

    private final Queue<Enemy> enemies;

    /**
     * Creates a wave.
     *
     * @param enemyCount number of enemies
     */
    public Wave(World world, int enemyCount) {
        this.enemies = createEnemies(world, enemyCount);
    }

    /**
     * Adds same amount as numEnemies of random enemies to Wave.
     *
     * @param count the number of enemies
     * @return the enemies
     */
    private Queue<Enemy> createEnemies(World world, int count) {
        for (int i = 0; i < count; i++) {
            Enemy enemy = EnemyFactory.randomEnemy(world, new IntRange(0, world.getNumberOfLanes()).getRandom());
            enemies.add(enemy);
        }

        return enemies;
    }

    /**
     * Returns the enemies in the wave.
     *
     * @return the enemies
     */
    public Queue<Enemy> getEnemies() {
        return enemies;
    }
}