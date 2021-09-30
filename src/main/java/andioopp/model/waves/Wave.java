package andioopp.model.waves;

import andioopp.model.enemy.Enemies;
import andioopp.model.enemy.Enemy;
import andioopp.model.world.World;

import java.util.Random;

import java.util.LinkedList;

public class Wave {

    public LinkedList<Enemy> enemyWave;
    public int numEnemies;
    Random rand = new Random();


    public Wave(int numEnemies) {
        this.numEnemies = numEnemies;
        this.enemyWave = new LinkedList<>();
    }

    public void addEnemyToWave(World world) {
        Enemies enemies = new Enemies();
        int randLane;
        for (int i = 0; i < numEnemies; i++) {
            randLane = rand.nextInt(5);
            Enemy enemy = enemies.createRandomEnemy(world, 1);
            enemyWave.add(enemy);
        }
    }
}