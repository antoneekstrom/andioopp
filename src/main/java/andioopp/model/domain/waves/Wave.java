package andioopp.model.domain.waves;

import andioopp.model.domain.enemy.EnemyFactory;
import andioopp.model.domain.enemy.Enemy;
import andioopp.model.domain.world.World;

import java.util.Random;

import java.util.LinkedList;

/**
 * A wave, which is a
 */
public class Wave {

    public LinkedList<Enemy> enemyWave;
    private final int numEnemies;
    Random rand = new Random();


    public Wave(int numEnemies) {
        this.numEnemies = numEnemies;
        this.enemyWave = new LinkedList<>();
    }

    /**
     * Adds same amount as numEnemies of random enemies to Wave
     */
    public void addEnemyToWave(World world) {
        EnemyFactory enemies = new EnemyFactory();
        for (int i = 0; i < numEnemies; i++) {
            Enemy enemy = enemies.randomEnemy(world, rand.nextInt(world.getNumberOfLanes()));
            Enemy enemy2 = enemies.randomEnemy(world, rand.nextInt(world.getNumberOfLanes()));
            Enemy enemy3 = enemies.randomEnemy(world, rand.nextInt(world.getNumberOfLanes()));
            Enemy enemy4 = enemies.randomEnemy(world, rand.nextInt(world.getNumberOfLanes()));
            enemyWave.add(enemy);
            enemyWave.add(enemy2);
            enemyWave.add(enemy3);
            enemyWave.add(enemy4);
        }
    }
}