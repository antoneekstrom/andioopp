package andioopp.domain.model;

import andioopp.common.storage.ArrayListFactory;
import andioopp.common.storage.ListFactory;
import andioopp.common.time.Time;
import andioopp.domain.model.enemy.Enemies;
import andioopp.domain.model.tower.Towers;

public class Model implements Updateable {

    private final World world;
    private final WaveQueue waves;
    private final Player player;

    private final ListFactory listFactory = new ArrayListFactory();

    public Model(WaveQueue waves, Player player) {
        this.world = createWorld();
        this.waves = waves;
        this.player = player;
    }

    @Override
    public void update(Time time) {
        world.update(time);
    }

    private World createWorld() {
        LaneBuilder laneBuilder = new LaneBuilder(listFactory).setCells(9);
        WorldBuilder builder = new WorldBuilder(laneBuilder, listFactory).setLanes(5);

        World world = builder.build();
        world.getCell(1, 3).setTower(Towers.mario());
        world.addEnemy(Enemies.goomba(world, 0));
        world.addEnemy(Enemies.goomba(world, 2));
        world.addEnemy(Enemies.goomba(world, 4));

        return world;
    }

    public World getWorld() {
        return world;
    }
}
