package andioopp.common.math.range;

import java.util.List;
import java.util.Random;
import java.util.function.Supplier;

/**
 * A range between two integers.
 * Can be used to generate a random number between the two bounds.
 *
 * @author Anton Ekström
 */
public class IntRange implements Supplier<Integer> {

    private final int min;
    private final int max;

    /**
     * Creates a range with the given max value and a minimum value of 0, both inclusive.
     *
     * @param max the higher bound
     */
    public IntRange(int max) {
        this.min = 0;
        this.max = max;
    }

    /**
     * Creates a range from the lower bound to the upper bound, both inclusive.
     *
     * @param min the lower bound
     * @param max the higher bound
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

    @Override
    public Integer get() {
        return getRandom();
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
