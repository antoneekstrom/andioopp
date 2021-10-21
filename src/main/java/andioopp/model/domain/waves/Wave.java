package andioopp.model.domain.waves;

import andioopp.common.time.Time;
import andioopp.model.domain.enemy.EnemyFactory;
import andioopp.model.domain.enemy.Enemy;
import andioopp.model.domain.world.World;

import java.util.Random;

import java.util.LinkedList;

public class Wave {
    float timeSinceLastEnemy;
    float deltaSeconds;
    private LinkedList<Enemy> enemyWave;
    int difficulty;

    Random rand = new Random();


    public Wave() {

        this.enemyWave = new LinkedList<>();
    }
    public void createWave(World world, int numEnemies, int difficulty){
        for(int i = 0; i< numEnemies; i++) {
            addEnemyToWave(world, numEnemies);
        }
        setDifficulty(difficulty);
    }
    public LinkedList<Enemy> getEnemyWave(){
        return enemyWave;
    }
    private void setDifficulty(int difficulty){
        this.difficulty = difficulty;
    }
    /**
     * Adds same amount as numEnemies of random enemies to Wave
     */
    public void addEnemyToWave(World world, int numEnemies) {
        EnemyFactory enemies = new EnemyFactory();
        for (int i = 0; i < numEnemies; i++) {
            Enemy enemy = enemies.randomEnemy(world, rand.nextInt(world.getNumberOfLanes()));
            enemyWave.add(enemy);

        }
    }
    /**
     * Delays enemies so they don't appear on screen at the same time.
     */
    public boolean delayEnemies(Time time, double delay) {
        this.deltaSeconds = time.getTime() - timeSinceLastEnemy;
        return (this.deltaSeconds > delay);
    }
    public void setDeltaSeconds(float deltaSeconds) {
        this.deltaSeconds = deltaSeconds;
    }

    public void updateTimeSinceLastEnemy(Time time) {
        this.timeSinceLastEnemy = time.getTime();
    }

    /**
     * Returns a random delay between 30 and 45.
     */
    public double getRandomDelay() {
        int randomDelay = (rand.nextInt(10) + 10) / (difficulty +1);

        return randomDelay; // * Math.pow(10,6.5);

    }
    /**
     * Adds wave of enemies to the world
     */
    public void addEnemyFromWaveToWorld(World world) {
        world.addEnemy(enemyWave.remove());
    }
}