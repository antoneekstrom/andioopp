package andioopp.model.system.systems;

import andioopp.common.math.range.IntRange;
import andioopp.common.time.Time;
import andioopp.model.Model;
import andioopp.model.domain.waves.WaveQueue;
import andioopp.model.system.System;

/**
 * A system that handles updating the Waves.
 */

public class UpdateWaveSystem implements System<Model> {

    public final IntRange spawnTimeRange;
    private final WaveQueue waves;

    private float timeSinceLastEnemy;

    public UpdateWaveSystem(WaveQueue waves, IntRange spawnTimeRange) {
        this.spawnTimeRange = spawnTimeRange;
        this.waves = waves;
    }

    @Override
    public void update(Model model, Time time) {
        updateWaves(time, model);
    }

    private void updateWaves(Time time, Model model) {
        if (canSpawnEnemy(time)) {
            spawnEnemy(time, model);
        }
    }

    /**
     * Delays enemies, so they don't appear on screen at the same time.
     */
    public boolean canSpawnEnemy(Time time) {
        float deltaSeconds = time.getTime() - timeSinceLastEnemy;
        return deltaSeconds >= getRandomDelay();
    }

    /**
     * Spawns the next enemy.
     *
     * @param time the time
     * @param model the model
     */
    private void spawnEnemy(Time time, Model model) {
        waves.spawnNextEnemy(model.getWorld());
        updateTimeSinceLastEnemy(time);
    }

    /**
     * Updates timeSinceLastEnemy with the current Time.
     */
    private void updateTimeSinceLastEnemy(Time time) {
        this.timeSinceLastEnemy = time.getTime();
    }

    /**
     * Returns a random delay between 30 and 45.
     */
    private double getRandomDelay() {
        return spawnTimeRange.getRandom();
    }
}
