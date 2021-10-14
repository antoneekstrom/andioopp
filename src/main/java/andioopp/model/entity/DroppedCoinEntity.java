package andioopp.model.entity;

import andioopp.common.graphics.Sprite;
import andioopp.common.graphics.SpriteFactory;
import andioopp.common.transform.Dimension;
import andioopp.common.transform.Rectangle;
import andioopp.common.transform.Vector3f;
import andioopp.model.Model;
import andioopp.model.player.Money;

public class DroppedCoinEntity {
    private static final String SPRITE_PATH = "coin.png";

    private final Rectangle rectangle;

    private final int value;
    private final Vector3f position;
    private boolean collected = false;

    public DroppedCoinEntity(int value, Vector3f position) {
        this.value = value;
        this.position = position;
        rectangle = new Rectangle(position, new Dimension(0.3f, 0.4f));
    }

    public void collect(Model model) {
        model.getPlayer().give(new Money(value));
        collected = true;
    }

    public boolean isCollected() {
        return collected;
    }

    public <S extends Sprite<?>> S getSprite(SpriteFactory<S> spriteFactory) {
        return spriteFactory.get(SPRITE_PATH);
    }

    public Rectangle getRectangle() {
        return rectangle;
    }
}
