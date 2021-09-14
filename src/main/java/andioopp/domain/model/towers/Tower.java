package andioopp.domain.model.towers;

public abstract class Tower {
    private int cost;
    private int hp;
    private String sprite;

    public Tower(String spritePath) {
        this.sprite = spritePath;

    }
}
