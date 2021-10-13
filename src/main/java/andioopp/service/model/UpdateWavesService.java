package andioopp.service.model;

import andioopp.common.time.Time;
import andioopp.model.Model;
import andioopp.model.waves.WaveQueue;

public class UpdateWavesService extends ModelService {

    private final WaveQueue waves;
    private double delay = 1;

    public UpdateWavesService() {
        this.waves = new WaveQueue();
    }

    @Override
    public void onSetup(Model model) {
        waves.addWavesToWaveQueue(model.getWorld(), 3);
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
