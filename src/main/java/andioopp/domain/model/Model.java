package andioopp.domain.model;

import andioopp.common.time.Time;
import andioopp.domain.model.enemy.Enemy;

public class Model implements Updateable {

    private final World world;
    private final WaveQueue waves;
    private final Player player;
    private int money = 0;

    public Model(World world, WaveQueue waves, Player player) {
        this.world = world;
        this.waves = waves;
        this.player = player;
    }

    @Override
    public void update(Time time) {
        for (Lane lane : world.getLanes()) {
            for (Enemy enemy : lane.getEnemies()) {
                enemy.update(time);
            }
        }
    }

    public World getWorld() {
        return world;
    }
}
