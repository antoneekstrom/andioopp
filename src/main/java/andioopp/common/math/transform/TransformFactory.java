package andioopp.common.math.transform;

import andioopp.common.math.vector.Vector3f;

@FunctionalInterface
public interface TransformFactory {
    Transform create(Vector3f position, Vector3f rotation, Vector3f scale);

    default Transform create() {
        return create(Vector3f.zero(), Vector3f.zero(), Vector3f.zero());
    }

    default Transform createWithPosition(Vector3f position) {
        return create(position, Vector3f.zero(), Vector3f.zero());
    }

    default Transform createWithRotation(Vector3f rotation) {
        return create(Vector3f.zero(), rotation, Vector3f.zero());
    }

    default Transform createWithScale(Vector3f scale) {
        return create(Vector3f.zero(), Vector3f.zero(), scale);
    }
}
