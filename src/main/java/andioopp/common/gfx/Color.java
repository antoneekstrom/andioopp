package andioopp.common.gfx;

import andioopp.common.transform.Vector3f;

/**
 * Represents a color.
 */
public class Color {

    public final static Color WHITE = new Color(255, 255, 255);

    private final Vector3f rgb;
    private final float alpha;

    public Color(float value) {
        this(value, value, value);
    }

    public Color(float r, float g, float b) {
        this(new Vector3f(r, g, b), 1.0f);
    }

    public Color(float r, float g, float b, float a) {
        this(new Vector3f(r, g, b), a);
    }

    private Color(Vector3f rgb, float alpha) {
        this.rgb = rgb;
        this.alpha = alpha;
    }

    public float getRed() {
        return rgb.getX();
    }

    public float getGreen() {
        return rgb.getY();
    }

    public float getBlue() {
        return rgb.getZ();
    }

    public float getAlpha() {
        return alpha;
    }
}
