package andioopp.model.system.systems;

import andioopp.common.time.Time;
import andioopp.model.Model;
import andioopp.model.domain.waves.Wave;
import andioopp.model.domain.waves.WaveQueue;
import andioopp.model.system.System;


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
        Wave wave = waves.getWave();

        if (wave.delayEnemies(time, delay)) {
            if (wave.getEnemyWave().size() != 0) {
                wave.addEnemyFromWaveToWorld(model.getWorld());
                wave.setDeltaSeconds(0);
                this.delay = wave.getRandomDelay();
                wave.updateTimeSinceLastEnemy(time);
            }
        }
    }
}
