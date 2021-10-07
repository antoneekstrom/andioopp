package andioopp.model;

import andioopp.common.storage.ArrayListFactory;
import andioopp.common.storage.ListFactory;
import andioopp.common.time.Time;
import andioopp.model.tower.Towers;
import andioopp.model.waves.WaveQueue;
import andioopp.model.world.LaneBuilder;
import andioopp.model.world.World;
import andioopp.model.world.WorldBuilder;

public class Model implements Updateable {

    private final World world;
    private final WaveQueue waves;
    private final ListFactory listFactory = new ArrayListFactory();
    private double delay = 1;
    private final Money money = new Money(100);

    public Model(WaveQueue waves) {
        this.waves = waves;
        this.world = createWorld();
    }

    @Override
    public void update(Time time) {
        world.update(time);

        if (waves.delayEnemies(time, delay)) {
            if (waves.getWave().enemyWave.size() != 0) {

                waves.addWaveToWorld(world);
                System.out.println("new enemy");
                waves.setDeltaSeconds(0);
                this.delay = waves.getRandomDelay();
                waves.updateTimeSinceLastEnemy(time);


            }
        }


    }

    private World createWorld() {
        LaneBuilder laneBuilder = new LaneBuilder(listFactory).setCells(9);
        WorldBuilder builder = new WorldBuilder(laneBuilder, listFactory, money).setLanes(5);

        World world = builder.build();

        world.getCell(0, 6).setTower(Towers.toad());
        world.getCell(1, 3).setTower(Towers.mario());
        world.getCell(2, 4).setTower(Towers.toad());
        world.getCell(3, 7).setTower(Towers.toad());
        world.getCell(4, 6).setTower(Towers.mario());
        waves.addWavesToWaveQueue(world, 1);




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



