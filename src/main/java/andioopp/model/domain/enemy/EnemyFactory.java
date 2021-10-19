package andioopp.model.domain.enemy;

import andioopp.common.math.range.IntRange;
import andioopp.common.storage.ArrayListFactory;
import andioopp.model.domain.enemy.enemies.*;
import andioopp.model.domain.world.World;
import andioopp.model.util.ModelCoordinate;

import java.util.List;

/**
 * Static enemy factory.
 */
public class EnemyFactory {

    @FunctionalInterface
    private interface EnemySupplier {
        Enemy get(World world, int row);
    }

    private static final List<EnemySupplier> RANDOM_ENEMY_POOL = new ArrayListFactory().create(
            EnemyFactory::createGoomba,
            EnemyFactory::createKoopaTroopa,
            EnemyFactory::createBoo
    );

    public Enemy randomEnemy(World world, int row) {
        return RANDOM_ENEMY_POOL.get(new IntRange(RANDOM_ENEMY_POOL).getRandom()).get(world, row);
    }


    public static Enemy createGoomba(World world, int row) {
        return new Goomba(new ModelCoordinate(world.getNumberOfCellsInLanes(), row));
    }

    public static Enemy createKoopaTroopa(World world, int row) {
        return new KoopaTroopa(new ModelCoordinate(world.getNumberOfCellsInLanes(), row));
    }

    public static Enemy createBoo(World world, int row) {
        return new Boo(new ModelCoordinate(world.getNumberOfCellsInLanes(), row));
    }

    public static Enemy createBlooper(World world, int row) {
        return new Blooper(new ModelCoordinate(world.getNumberOfCellsInLanes(), row));
    }

    public static Enemy createBuzzyBeetle(World world, int row) {
        return new BuzzyBeetle(new ModelCoordinate(world.getNumberOfCellsInLanes(), row));
    }
}
