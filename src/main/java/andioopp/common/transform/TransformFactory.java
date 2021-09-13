package andioopp.common.transform;

public class TransformFactory implements TransformSupplier {

    private final TransformSupplier transformSupplier;

    public TransformFactory(TransformSupplier transformSupplier) {
        this.transformSupplier = transformSupplier;
    }

    @Override
    public Transform create(Vector3f position, Vector3f rotation, Vector3f scale) {
        return transformSupplier.create(position, rotation, scale);
    }

    public Transform create() {
        return create(Vector3f.zero(), Vector3f.zero(), Vector3f.zero());
    }

    public Transform createWithPosition(Vector3f position) {
        return create(position, Vector3f.zero(), Vector3f.zero());
    }

    public Transform createWithRotation(Vector3f rotation) {
        return create(Vector3f.zero(), rotation, Vector3f.zero());
    }

    public Transform createWithScale(Vector3f scale) {
        return create(Vector3f.zero(), Vector3f.zero(), scale);
    }

}
