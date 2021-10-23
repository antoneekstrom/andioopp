package andioopp.model.domain.waves;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class WaveTest {

    @Test
    public void isWaveDoneWhenThereAreNoEnemies() {
        assertTrue(new Wave(0).isDone());
    }

    @Test
    public void doesWaveContainCorrectNumberOfEnemies() {
        assertEquals(7, new Wave(7).getEnemiesRemaining());
    }

}
