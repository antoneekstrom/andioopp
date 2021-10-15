package andioopp.model.system;

import andioopp.common.time.Time;
import andioopp.model.Model;
import andioopp.model.domain.waves.WaveQueue;

public class UpdateWavesService implements System<Model> {

    private final WaveQueue waves;
    private double delay = 1;

    public UpdateWavesService() {
        this.waves = new WaveQueue();
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
