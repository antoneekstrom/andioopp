package andioopp.common.transform;

@FunctionalInterface
public interface TransformSupplier {
    Transform create(Vector3f position, Vector3f rotation, Vector3f scale);
}
