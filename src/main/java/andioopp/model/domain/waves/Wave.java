package andioopp.model.domain.waves;

import andioopp.model.domain.enemy.EnemyFactory;
import andioopp.model.domain.enemy.Enemy;
import andioopp.model.domain.world.World;

import java.util.Random;

import java.util.LinkedList;

/**
 * A Wave consists of {@link Enemy}.
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
     * Adds same amount as numEnemies of random enemies to Wave.
     */
    public void addEnemyToWave(World world) {
        EnemyFactory enemyFactory = new EnemyFactory();
        for (int i = 0; i < numEnemies; i++) {
            Enemy enemy = enemyFactory.randomEnemy(world, rand.nextInt(world.getNumberOfLanes()));
            Enemy enemy2 = enemyFactory.randomEnemy(world, rand.nextInt(world.getNumberOfLanes()));
            Enemy enemy3 = enemyFactory.randomEnemy(world, rand.nextInt(world.getNumberOfLanes()));
            Enemy enemy4 = enemyFactory.randomEnemy(world, rand.nextInt(world.getNumberOfLanes()));
            enemyWave.add(enemy);
            enemyWave.add(enemy2);
            enemyWave.add(enemy3);
            enemyWave.add(enemy4);
        }
    }

}