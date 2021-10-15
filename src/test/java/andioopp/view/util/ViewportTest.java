package andioopp.view.util;

import andioopp.common.math.Dimension;
import andioopp.common.math.Vector3f;
import andioopp.view.util.Viewport;
import org.junit.Test;

import static org.junit.Assert.*;

public class ViewportTest {

    private final Dimension<Vector3f> SCREEN_DIMENSION = new Dimension<>(new Vector3f(1280, 720));
    private final Dimension<Vector3f> HALF_SCREEN_DIMENSION = SCREEN_DIMENSION.halved();
    private final Vector3f HALF_SCREEN_OFFSET = SCREEN_DIMENSION.halved().toVector();
    private final Vector3f NO_OFFSET = Vector3f.zero();

    private final Viewport TRIVIAL_VIEWPORT = new Viewport(SCREEN_DIMENSION, SCREEN_DIMENSION, NO_OFFSET);
    private final Viewport HALF_INNER_VIEWPORT = new Viewport(SCREEN_DIMENSION, HALF_SCREEN_DIMENSION, NO_OFFSET);
    private final Viewport TRIVIAL_WITH_OFFSET_VIEWPORT = new Viewport(SCREEN_DIMENSION, SCREEN_DIMENSION, HALF_SCREEN_OFFSET);

    @Test
    public void offsetShouldAddToPosition() {
        Vector3f input = new Vector3f(0, 0);
        Vector3f expected = HALF_SCREEN_OFFSET;
        Vector3f result = TRIVIAL_WITH_OFFSET_VIEWPORT.getPositionOutside(input);
        assertEquals(expected, result);
    }

    @Test
    public void halfSizeInnerShouldHalvePosition() {
        Vector3f input = new Vector3f(4, 4);
        Vector3f expected = input.scale(0.5f);
        Vector3f result = HALF_INNER_VIEWPORT.getPositionOutside(input);
        assertEquals(expected, result);
    }

    @Test
    public void equalInsideAndOutsideShouldNotChangePosition() {
        Vector3f expected = new Vector3f(3, 4);
        Vector3f result = TRIVIAL_VIEWPORT.getPositionOutside(expected);
        assertEquals(expected, result);
    }
}
