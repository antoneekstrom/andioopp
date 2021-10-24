package andioopp.model.domain.waves;

import andioopp.common.math.range.IntRange;
import andioopp.model.domain.world.World;

import java.util.LinkedList;
import java.util.Queue;
import java.util.function.Supplier;

/**
 * A WaveQueue consists of {@link Wave} and adds them to the world.
 */
public class WaveQueue {

    private final Queue<Wave> queue;

    /**
     * Creates an empty wave queue.
     */
    public WaveQueue() {
        queue = new LinkedList<>();
    }

    /**
     * Adds the next enemy to the world at a random location.
     *
     * @param world the world
     */
    public void spawnNextEnemy(World world) {
        spawnNextEnemy(world, new IntRange(world.getNumberOfLanes()).getRandom());
    }

    /**
     * Adds the next enemy to the world.
     *
     * @param world the world
     * @param row the on which to spawn the enemy
     */
    public void spawnNextEnemy(World world, int row) {
        Wave wave = getCurrentWave();

        while (wave.isDone()) {
             wave = nextWave();
        }

        world.addEnemy(wave.spawnNextEnemy(world, row));
    }

    /**
     * Returns the number of remaining waves.
     *
     * @return the number of waves left
     */
    public int getWavesRemaining() {
        return queue.size();
    }

    /**
     * Adds a number of Waves to the WaveQueue
     * @param numberOfWaves the number of waves to add
     * @param enemyCountSupplier the random number of enemies in each wave
     */
    public WaveQueue addWaves(int numberOfWaves, Supplier<Integer> enemyCountSupplier) {
        for (int i = 0; i < numberOfWaves; i++) {
            int enemyCount = enemyCountSupplier.get();
            Wave wave = new Wave(enemyCount);
            queue.add(wave);
        }
        return this;
    }

    /**
     * Adds a number of Waves to the WaveQueue
     * @param numberOfWaves the number of waves to add
     * @param enemyCount the number of enemies in each wave
     */
    public WaveQueue addWaves(int numberOfWaves, int enemyCount) {
        addWaves(numberOfWaves, () -> enemyCount);
        return this;
    }

    /**
     * Removes the current wave and returns the next.
     *
     * @return the next wave
     */
    private Wave nextWave() {
        queue.poll();
        return getCurrentWave();
    }

    /**
     * Returns wave at top of queue, does not remove it from queue
     */
    private Wave getCurrentWave() {
        return queue.peek();
    }
}



