package andioopp.model.domain.waves;

import andioopp.common.time.Time;
import andioopp.model.domain.enemy.EnemyFactory;
import andioopp.model.domain.enemy.Enemy;
import andioopp.model.domain.world.World;

import java.util.Random;

import java.util.LinkedList;

/**
 * A Wave consists of {@link Enemy}.
 */
public class Wave {


    float timeSinceLastEnemy;
    float deltaSeconds;
    public LinkedList<Enemy> enemyWave;
    private int numEnemies;
    int difficulty;
    Random rand = new Random();


    public Wave(int difficulty) {
        this.difficulty = difficulty;
        this.enemyWave = new LinkedList<>();
    }

    public void createWave(World world){
        this.numEnemies = rand.nextInt(8) + 3;
        for(int i = 0; i< numEnemies; i++){
            addEnemyToWave(world);
        }

    }

    /**
     * Adds same amount as numEnemies of random enemies to Wave.
     */
    public void addEnemyToWave(World world) {
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

    /**
     * Updates timeSinceLastEnemy with the current Time.
     */
    public void updateTimeSinceLastEnemy(Time time) {
        this.timeSinceLastEnemy = time.getTime();
    }

    /**
     * Returns a random delay between 30 and 45, depending on a waves difficulty.
     */
    public double getRandomDelay() {
        int randomDelay = rand.nextInt(15) + (30 / (difficulty + 1));

        return randomDelay; // * Math.pow(10,6.5);

    }
}