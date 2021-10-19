package andioopp.model.stats;

import andioopp.model.domain.stats.Health;
import org.junit.Test;

import static org.junit.Assert.*;

public class HealthTest {

    @Test
    public void isDeadShouldReturnTrueWhenZero() {
        assertTrue(new Health(0).isDead());
    }

    @Test
    public void isDeadShouldReturnTrueWhenBelowZero() {
        assertTrue(new Health(-1).isDead());
    }

    @Test
    public void increaseShouldAddToHealth() {
        assertEquals(new Health(3), new Health(1).increase(2));
    }

    @Test
    public void decreaseShouldSubtractFromHealth() {
        assertEquals(new Health(1), new Health(3).decrease(2));
    }

    @Test
    public void negativeHealthShouldBecomeZero() {
        assertEquals(new Health(0), new Health(1).decrease(2));
    }

}
