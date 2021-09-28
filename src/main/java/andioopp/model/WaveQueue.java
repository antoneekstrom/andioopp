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
   float timeSinceLastEnemy;
    float deltaSeconds;

    private final Queue<Wave> queue;
    Random rand = new Random();


    public WaveQueue() {

        queue = new LinkedList<>();
    }
    public void setDeltaSeconds(float deltaSeconds){
        this.deltaSeconds = deltaSeconds;
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
    public boolean delayEnemies(Time time, double delay){
        //int randomDelay = rand.nextInt(100) + 1;
        this.deltaSeconds = time.getElapsedSeconds() - timeSinceLastEnemy;
        System.out.println(delay);


        return(this.deltaSeconds > delay);

    }
    public void updateTimeSinceLastEnemy(Time time) {
        this.timeSinceLastEnemy = time.getElapsedSeconds();
    }

    public double getRandomDelay(){
        int randomDelay = rand.nextInt(11) + 4;

        return randomDelay * Math.pow(10,6.5);

    }

    }



