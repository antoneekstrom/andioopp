package andioopp.model.system.systems;

import andioopp.common.time.Time;
import andioopp.model.Model;
import andioopp.model.domain.waves.WaveQueue;
import andioopp.model.system.System;

/**
 * A system that handles updating the Waves.
 */

public class UpdateWaveSystem implements System<Model> {

    private final WaveQueue waves;
    private double delay = 1;

    public UpdateWaveSystem(WaveQueue waves) {
        this.waves = waves;
    }

    @Override
    public void update(Model model, Time time) {
        updateWaves(time, model);
    }

    private void updateWaves(Time time, Model model) {
        if (waves.delayEnemies(time, delay)) {
            if (waves.getWave().enemyWave.size() != 0) {
                waves.addWaveToWorld(model.getWorld());
                waves.setDeltaSeconds(0);
                this.delay = waves.getRandomDelay();
                waves.updateTimeSinceLastEnemy(time);
            }
        }
    }
}
