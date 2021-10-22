package andioopp.common.math.range;

import java.util.List;
import java.util.Random;

/**
 * A range between two integers.
 * Can be used to generate a random number between the two bounds.
 *
 * @author Anton Ekström
 */
public class IntRange {

    private final int min;
    private final int max;

    /**
     * Creates and defines the range
     * @param min lower bound
     * @param max higher bound
     */
    public IntRange(int min, int max) {
        this.min = min;
        this.max = max;
    }

    /**
     * Creates a range where the length of a list is the upper bound
     * @param list
     */
    public IntRange(List<?> list) {
        this(0, list.size());
    }

    /**
     * Returns a random integer between the bounds
     * @return an integer
     */
    public int getRandom() {
        return getRandom(new Random());
    }

    public int getRandom(Random random) {
        return getMin() + random.nextInt(getMax() - getMin());
    }

    public int getMin() {
        return min;
    }

    public int getMax() {
        return max;
    }
}
