package andioopp.domain.model;

import andioopp.common.storage.ArrayListFactory;
import andioopp.common.time.Time;
import andioopp.domain.model.enemy.Enemies;
import andioopp.domain.model.enemy.Enemy;
import andioopp.domain.model.tower.Towers;

public class Model implements Updateable {

    private final World world;
    private final WaveQueue waves;
    private final Player player;

    private final ArrayListFactory listFactory = new ArrayListFactory();

    public Model(WaveQueue waves, Player player) {
        this.world = createWorld();
        this.waves = waves;
        this.player = player;
    }

    private World createWorld() {
        LaneBuilder laneBuilder = new LaneBuilder(listFactory).setCells(9).addEnemy(Enemies.goomba());
        WorldBuilder builder = new WorldBuilder(laneBuilder, listFactory).setLanes(5);

        World world = builder.build();
        world.getCell(1, 3).setTower(Towers.mario());

        return world;
    }

    @Override
    public void update(Time time) {
        world.update(time);
    }

    public World getWorld() {
        return world;
    }

    public Player getPlayer() {
        return player;
    }

    public WaveQueue getWaves() {
        return waves;
    }
}
