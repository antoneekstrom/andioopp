package andioopp.model;

import andioopp.model.enemies.Enemy;

public class Model {

    private final World world;
    private final WaveQueue waves;
    private final Player player;
    private int money = 0;

    public Model(World world, WaveQueue waves, Player player) {
        this.world = world;
        this.waves = waves;
        this.player = player;
    }

    public void update() {
        for (Lane lane : world.getLanes()) {
            for (Enemy<?> enemy : lane.getEnemies()) {
                enemy.update();
            }
        }
    }

    public World getWorld() {
        return world;
    }
}
