package andioopp.model.enemy;

import andioopp.common.transform.Vector3f;
import andioopp.model.World;
import andioopp.model.enemy.enemies.BuzzyBeetle;
import andioopp.model.enemy.enemies.Goomba;

public class Enemies {

    public static Enemy goomba(World world, int row) {
        float worldEndX = world.getNumberOfCellsInLanes();
        return new Goomba(new Vector3f(worldEndX, row));
    }

    public static Enemy buzzyBeetle(World world, int row) {
        float worldEndX = world.getNumberOfCellsInLanes();
        return new BuzzyBeetle(new Vector3f(worldEndX, row));
    }

}
