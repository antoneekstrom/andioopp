package andioopp.model.domain.enemy;

import andioopp.common.math.range.IntRange;
import andioopp.common.storage.ArrayListFactory;
import andioopp.model.domain.enemy.enemies.Blooper;
import andioopp.model.domain.enemy.enemies.BuzzyBeetle;
import andioopp.model.domain.enemy.enemies.Goomba;
import andioopp.model.domain.enemy.enemies.KoopaTroopa;
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
            EnemyFactory::createKoopaTroopa
    );

    /**
     * Returns a random Enemy out of all enemies
     */
    public Enemy randomEnemy(World world, int row) {
        return RANDOM_ENEMY_POOL.get(new IntRange(RANDOM_ENEMY_POOL).getRandom()).get(world, row);
    }

    /**
     * Creates enemy Goomba
     * @param row the lane where enemy will be
     */
    public static Enemy createGoomba(World world, int row) {
        return new Goomba(new ModelCoordinate(world.getNumberOfCellsInLanes(), row));
    }
    /**
     * Creates enemy KoopaTroopa
     * @param row the lane where enemy will be
     */
    public static Enemy createKoopaTroopa(World world, int row) {
        return new KoopaTroopa(new ModelCoordinate(world.getNumberOfCellsInLanes(), row));
    }
    /**
     * Creates enemy Blooper
     * @param row the lane where enemy will be
     */
    public static Enemy createBlooper(World world, int row) {
        return new Blooper(new ModelCoordinate(world.getNumberOfCellsInLanes(), row));
    }

    /**
     * Creates enemy BuzzyBeetle
     * @param row the lane where enemy will be
     */
    public static Enemy createBuzzyBeetle(World world, int row) {
        return new BuzzyBeetle(new ModelCoordinate(world.getNumberOfCellsInLanes(), row));
    }
}
