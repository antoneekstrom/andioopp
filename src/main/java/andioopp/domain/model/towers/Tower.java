package andioopp.domain.model.towers;

import andioopp.gfx.Sprite;
import andioopp.gfx.SpriteFactory;

public abstract class Tower {
    private int cost;
    private int hp;
    private String sprite;

    public Tower(String spritePath) {
        this.sprite = spritePath;

    }



    public <S extends Sprite<?>> S getSprite(SpriteFactory<S> spriteFactory) {
        return spriteFactory.get(sprite);
    }
}
