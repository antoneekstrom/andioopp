package andioopp.model.domain.waves;

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



    public WaveQueue() {
        queue = new LinkedList<>();
    }

    /**
     * Returns queue of Waves
     */
    public Queue<Wave> getWaves() {
        return queue;
    }

    /**
     * Adds a number of Waves to the WaveQueue
     */
    public void addWavesToWaveQueue(World world, int numWaves) {

        for (int i = 0; i < numWaves; i++) {
            Wave wave = new Wave(i);
            wave.createWave(world);
            queue.add(wave);
        }
    }

    /**
     * Returns wave at top of queue, does not remove it from queue
     */
    public Wave getWave() {
        Wave wave = queue.peek();
        return wave;

    }

    /**
     * Adds wave of enemies to the world
     */
    public void addWaveToWorld(World world) {
        Wave wave = queue.peek();
        world.addEnemy(wave.enemyWave.remove());
    }


}


