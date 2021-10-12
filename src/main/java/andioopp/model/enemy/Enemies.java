package andioopp.model.enemy;

import andioopp.common.math.IntRange;
import andioopp.common.storage.ArrayListFactory;
import andioopp.common.transform.ConcreteTransform;
import andioopp.common.transform.Transform;
import andioopp.common.transform.Vector3f;
import andioopp.model.enemy.enemies.Blooper;
import andioopp.model.enemy.enemies.BuzzyBeetle;
import andioopp.model.enemy.enemies.Goomba;
import andioopp.model.enemy.enemies.KoopaTroopa;
import andioopp.model.world.World;

import java.util.List;

/**
 * Static enemy factory.
 */
public class Enemies {

    @FunctionalInterface
    private interface EnemySupplier {
        Enemy get(World world, int row);
    }

    private static final List<EnemySupplier> RANDOM_ENEMY_POOL = new ArrayListFactory().create(
            Enemies::goomba,
            Enemies::koopaTroopa
    );

    public Enemy randomEnemy(World world, int row) {
        return RANDOM_ENEMY_POOL.get(new IntRange(RANDOM_ENEMY_POOL).getRandom()).get(world, row);
    }


    public static Enemy goomba(World world, int row) {
        return new Goomba(getTransform(world, row));
    }

    public static Enemy koopaTroopa(World world, int row) {
        return new KoopaTroopa(getTransform(world, row));
    }

    public static Enemy blooper(World world, int row) {
        return new Blooper(getTransform(world, row));
    }

    public static Enemy buzzyBeetle(World world, int row) {
        return new BuzzyBeetle(getTransform(world, row));
    }

    private static Transform getTransform(World world, int row) {
        float worldEndX = world.getNumberOfCellsInLanes();
        return ConcreteTransform.getFactory().createWithPosition(new Vector3f(worldEndX, row));
    }
}
