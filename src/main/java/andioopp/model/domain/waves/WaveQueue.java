package andioopp.model.domain.waves;

import andioopp.common.math.range.IntRange;
import andioopp.common.time.Time;
import andioopp.model.domain.world.World;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;

/**
 * A WaveQueue consists of {@link Wave} and adds them to the world.
 */
public class WaveQueue {

    private final Queue<Wave> queue;

    float timeSinceLastEnemy;
    float deltaSeconds;

    public WaveQueue() {
        queue = new LinkedList<>();
    }

    /**
     * Adds a number of Waves to the WaveQueue
     */
    public void addWaves(World world, int numWaves) {
        for (int i = 0; i < numWaves; i++) {
            int enemyCount = new IntRange(5, 10).getRandom();
            Wave wave = new Wave(world, enemyCount);
            queue.add(wave);
        }
    }

    /**
     * Adds wave of enemies to the world
     */
    public void releaseWave(World world) {
        world.addWave(getWave());
    }

    /**
     * Delays enemies, so they don't appear on screen at the same time.
     */
    public boolean delayEnemies(Time time, double delay) {
        this.deltaSeconds = time.getTime() - timeSinceLastEnemy;
        return (this.deltaSeconds > delay);
    }

    public void setDeltaSeconds(float deltaSeconds) {
        this.deltaSeconds = deltaSeconds;
    }

    /**
     * Updates timeSinceLastEnemy with the current Time.
     */
    public void updateTimeSinceLastEnemy(Time time) {
        this.timeSinceLastEnemy = time.getTime();
    }

    /**
     * Returns a random delay between 30 and 45.
     */
    public double getRandomDelay() {
        return new IntRange(20, 35).getRandom();
    }

    /**
     * Returns wave at top of queue, does not remove it from queue
     */
    public Wave getWave() {
        return queue.peek();
    }
}



