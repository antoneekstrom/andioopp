package andioopp.common.math.range;

import java.util.Random;

public class FloatRange {

    private final float min;
    private final float max;

    public FloatRange(float min, float max) {
        this.min = min;
        this.max = max;
    }

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
