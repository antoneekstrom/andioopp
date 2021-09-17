package andioopp.common.transform;

public class ConcreteTransform implements Transform {

    private Vector3f position, rotation, scale;

    ConcreteTransform(Vector3f position, Vector3f rotation, Vector3f scale) {
        this.position = position;
        this.rotation = rotation;
        this.scale = scale;
    }

    public static ConcreteTransformFactory getFactory() {
        return new ConcreteTransformFactory(ConcreteTransform::new);
    }

    @Override
    public Vector3f getPosition() {
        return position;
    }

    @Override
    public Vector3f getRotation() {
        return rotation;
    }

    @Override
    public Vector3f getScale() {
        return scale;
    }

    public void setRotation(Vector3f rotation) {
        this.rotation = rotation;
    }

    public void setScale(Vector3f scale) {
        this.scale = scale;
    }

    @Override
    public void setPosition(Vector3f position) {
        this.position = position;
    }

    @Override
    public void translate(Vector3f translation) {
        position = position.add(translation);
    }
}
