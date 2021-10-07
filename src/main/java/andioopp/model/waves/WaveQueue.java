package andioopp.model.waves;

import andioopp.common.time.Time;
import andioopp.model.world.World;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;

public class WaveQueue {

    float timeSinceLastEnemy;
    float deltaSeconds;
    private final Queue<Wave> queue;
    Random rand = new Random();


    public WaveQueue() {
        queue = new LinkedList<>();
    }
    //returns queue of Waves
    public Queue<Wave> getWaves() {
        return queue;
    }

    //Adds a number of Waves to the WaveQueue
    public void addWavesToWaveQueue(World world, int numWaves) {
        Wave wave = new Wave(rand.nextInt(8) + 3);
        for (int i = 0; i < numWaves; i++) {
            wave.addEnemyToWave(world);
        }
        queue.add(wave);
    }

    //returns wave at top of queue, does not remove it from queue
    public Wave getWave() {

        Wave wave = queue.peek();
        return wave;

    }
    //adds wave of enemies to the world
    public void addWaveToWorld(World world) {
        Wave wave = queue.peek();
        world.addEnemy(wave.enemyWave.remove());
    }
    //Delays enemies so they don't appear on screen at the same time
    public boolean delayEnemies(Time time, double delay) {
        this.deltaSeconds = time.getElapsedSeconds() - timeSinceLastEnemy;
        return (this.deltaSeconds > delay);

    }
    public void setDeltaSeconds(float deltaSeconds) {
        this.deltaSeconds = deltaSeconds;
    }

    public void updateTimeSinceLastEnemy(Time time) {
        this.timeSinceLastEnemy = time.getElapsedSeconds();
    }

    //returns a random delay
    public double getRandomDelay() {
        int randomDelay = rand.nextInt(12) + 3;

        return randomDelay; // * Math.pow(10,6.5);

    }

}



