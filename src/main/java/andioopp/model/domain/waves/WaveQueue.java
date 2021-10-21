package andioopp.model.domain.waves;


import andioopp.model.domain.world.World;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;

public class WaveQueue {


    private final Queue<Wave> queue;
    Random rand = new Random();


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
            Wave wave = new Wave();
            wave.createWave(world, rand.nextInt(5)+5, i);
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




}



