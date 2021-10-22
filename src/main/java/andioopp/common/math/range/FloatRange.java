package andioopp.common.math.range;

import java.util.Random;

/**
 * A range between two floating point values.
 * Can be used to generate a random number between the two bounds.
 *
 * @author Anton Ekström
 */
public class FloatRange {

    private final float min;
    private final float max;

    /**
     * Creates and defines the range
     * @param min lower bound
     * @param max higher bound
     */
    public FloatRange(float min, float max) {
        this.min = min;
        this.max = max;
    }

    /**
     * Returns a random integer between the bounds
     * @return an integer
     */
    public float getRandom() {
        return getRandom(new Random());
    }

    public float getRandom(Random random) {
        return getMin() + random.nextFloat() * (getMax() - getMin());
    }

    public float getMin() {
        return min;
    }

    public float getMax() {
        return max;
    }
}
