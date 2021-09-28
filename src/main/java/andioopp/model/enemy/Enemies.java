package andioopp.model.enemy;

import andioopp.common.transform.Vector3f;
import andioopp.model.enemy.enemies.BuzzyBeetle;
import andioopp.model.world.World;
import andioopp.model.enemy.enemies.Blooper;
import andioopp.model.enemy.enemies.Goomba;
import andioopp.model.enemy.enemies.KoopaTroopa;
import java.util.Random;

public class Enemies {
    Random rand = new Random();

    public static Enemy goomba(World world, int row) {
        float worldEndX = world.getNumberOfCellsInLanes();
        return new Goomba(new Vector3f(worldEndX, row));
    }
    public static Enemy koopaTroopa(World world, int row) {
        float worldEndX = world.getNumberOfCellsInLanes();
        return new KoopaTroopa(new Vector3f(worldEndX, row));
    }
    public static Enemy blooper(World world, int row) {
        float worldEndX = world.getNumberOfCellsInLanes();
        return new Blooper(new Vector3f(worldEndX, row));
    }
    public Enemy createRandomEnemy(World world, int row){

        float worldEndX = world.getNumberOfCellsInLanes();
        int random = rand.nextInt(2);

        if(random == 0){
            return new Goomba(new Vector3f(worldEndX, row));
        }

        return new KoopaTroopa(new Vector3f(worldEndX, row));


    }
    public static Enemy buzzyBeetle(World world, int row) {
        float worldEndX = world.getNumberOfCellsInLanes();
        return new BuzzyBeetle(new Vector3f(worldEndX, row));
    }

}

    

}