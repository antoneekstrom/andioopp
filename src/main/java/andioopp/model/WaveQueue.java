package andioopp.model;

import andioopp.common.time.Time;

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
    public void addWaveToWorld(World world){

        Wave wave = queue.remove();
        while(wave.enemyWave.size() != 0){
            world.addEnemy(wave.enemyWave.remove());

        }

    }

}
