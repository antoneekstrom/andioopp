package andioopp.domain.model.enemy;

import andioopp.common.transform.Vector3f;
import andioopp.domain.model.World;
import andioopp.domain.model.enemy.enemies.Goomba;

public class Enemies {

    public static Enemy goomba(World world, int row) {
        float worldEndX = world.getNumberOfCellsInLanes();
        return new Goomba(new Vector3f(worldEndX, row));
    }

}
