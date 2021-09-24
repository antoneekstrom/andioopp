package andioopp.model;

import andioopp.common.storage.ArrayListFactory;
import andioopp.common.storage.ListFactory;
import andioopp.common.time.Time;
import andioopp.model.enemy.Enemies;
import andioopp.model.tower.Towers;

public class Model implements Updateable {

    private final World world;
    private final WaveQueue waves;
    private final Player player;

    private final ListFactory listFactory = new ArrayListFactory();

    public Model(WaveQueue waves, Player player) {
        this.waves = waves;
        this.world = createWorld();
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
        waves.addWavesToWaveQueue(world,1);
        waves.addWaveToWorld(world);

        /*world.addEnemy(Enemies.goomba(world, 0));
        world.addEnemy(Enemies.goomba(world, 1));
        world.addEnemy(Enemies.goomba(world, 2));
        world.addEnemy(Enemies.goomba(world, 3));
        world.addEnemy(Enemies.goomba(world, 4));*/

        return world;
    }

    public World getWorld() {
        return world;
    }
}



