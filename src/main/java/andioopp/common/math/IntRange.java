package andioopp.common.math;

import java.util.List;
import java.util.Random;

public class IntRange {

    private final int min;
    private final int max;

    public IntRange(int min, int max) {
        this.min = min;
        this.max = max;
    }

    public IntRange(List<?> list) {
        this(0, list.size());
    }

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
