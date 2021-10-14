package andioopp.model.entity;

import andioopp.common.graphics.Sprite;
import andioopp.common.graphics.SpriteFactory;
import andioopp.common.math.Dimension;
import andioopp.common.math.Vector3f;
import andioopp.common.math.rectangle.ImmutableRectangle;
import andioopp.common.math.rectangle.Rectangle;
import andioopp.model.Model;
import andioopp.model.money.Money;

public class DroppedCoinEntity {
    private static final String SPRITE_PATH = "coin.png";

    private final Rectangle rectangle;

    private final int value;
    private boolean collected = false;

    public DroppedCoinEntity(int value, Vector3f position) {
        this.value = value;
        rectangle = new ImmutableRectangle(position, new Dimension(0.3f, 0.4f));
    }

    public void collect(Model model) {
        model.getPlayer().giveMoney(new Money(value));
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
