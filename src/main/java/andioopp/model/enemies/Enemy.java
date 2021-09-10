package andioopp.model.enemies;

public abstract class Enemy {
    private int hp;
    private int speed;

    private Transform transform;
    private S sprite;

    public Enemy(Transform transform, S sprite) {
        this.transform = transform;
        this.sprite = sprite;
    }

    public Enemy(S sprite) {
        this(new ConcreteTransform(), sprite);
    }

    public void update() {
        getTransform().translate(new Vector3f(10, 0, 0));
        System.out.println(getTransform().getPosition().getX());
    }

    public void render(Renderer<?> renderer) {

    }

    public void setSprite(S sprite) {
        this.sprite = sprite;
    }

    public void setTransform(Transform transform) {
        this.transform = transform;
    }

    public Transform getTransform() {
        return transform;
    }

    public S getSprite() {
        return sprite;
    }
}
