package andioopp.model;

import andioopp.model.enemy.Enemies;
import andioopp.model.enemy.Enemy;
import andioopp.model.enemy.enemies.*;

import java.util.ArrayList;
import java.util.Random;

import java.util.LinkedList;
import java.util.List;

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
            Enemy enemy = enemies.createRandomEnemy(world, randLane);
            enemyWave.add(enemy);
        }
    }
}