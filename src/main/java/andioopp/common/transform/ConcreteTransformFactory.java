package andioopp.common.transform;

public class ConcreteTransformFactory implements TransformFactory {

    private final TransformFactory transformSupplier;

    ConcreteTransformFactory(TransformFactory transformSupplier) {
        this.transformSupplier = transformSupplier;
    }

    @Override
    public Transform create(Vector3f position, Vector3f rotation, Vector3f scale) {
        return transformSupplier.create(position, rotation, scale);
    }

}
