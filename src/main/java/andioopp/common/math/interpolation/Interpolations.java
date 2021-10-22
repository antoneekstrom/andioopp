package andioopp.common.math.interpolation;

public class Interpolations {

    public static float linear(float from, float to, float amount) {
        return (to - from) * amount;
    }

}
