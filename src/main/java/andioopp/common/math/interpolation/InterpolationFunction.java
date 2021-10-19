package andioopp.common.math.interpolation;

/**
 * A function which interpolates from one value to another.
 *
 * @author Anton Ekström
 */
@FunctionalInterface
public interface InterpolationFunction {

    /**
     * Interpolates between two values for the given amount.
     *
     * @param from   the first value
     * @param to     the second value
     * @param amount the amount to interpolate by
     * @return the resulting value
     */
    float interpolate(float from, float to, float amount);

}
