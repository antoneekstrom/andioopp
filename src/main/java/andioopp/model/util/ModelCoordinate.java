package andioopp.model.util;

import andioopp.common.math.vector.Vector3f;

/**
 * Represents a position in the model.
 *
 * @author Jacob Bengtsson, Elin Nilsson
 */
public class ModelCoordinate extends Vector3f {
    public ModelCoordinate(float x, float y, float z) {
        super(x, y, z);
    }
    public ModelCoordinate(float x, float y) {
        super(x, y);
    }
    public ModelCoordinate(float x) {
        super(x);
    }
    public ModelCoordinate(Vector3f other) {
        super(other);
    }
}
