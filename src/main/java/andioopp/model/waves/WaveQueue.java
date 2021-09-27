package andioopp.model.waves;

import andioopp.model.world.World;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;

public class WaveQueue {
   /* Tasks:

            - [ ]  Create wavequeue
- [ ]  Make enemies come onto the gamefield at different times
- [ ]  and in different lanes
- [ ]  Make a wave consist of different enemies
- [ ]  Fiender kan ha en svårighetsgrad? Så att de svåraste inte kommer i början kanske
- [ ]*/

    private final Queue<Wave> queue;
    Random rand = new Random();


    public WaveQueue() {

        queue = new LinkedList<>();
    }

    public Queue<Wave> getWaves() {
        return queue;
    }

    public void addWavesToWaveQueue(World world, int numWaves) {
        Wave wave = new Wave(8);
        for (int i = 0; i < numWaves; i++){
            wave.addEnemyToWave(world);
        }
        queue.add(wave);

    }
    public Wave getWave(World world){

        Wave wave = queue.peek();
        return wave;

    }
    public void addWaveToWorld(World world, Wave wave) {

        wave = queue.peek();
        world.addEnemy(wave.enemyWave.remove());
    }

    }



