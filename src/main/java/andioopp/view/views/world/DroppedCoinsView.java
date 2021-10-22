package andioopp.view.views.world;

import andioopp.common.graphics.Renderer;
import andioopp.common.graphics.Sprite;
import andioopp.common.math.dimension.Dimension;
import andioopp.model.domain.entity.DroppedCoinEntity;
import andioopp.model.Model;
import andioopp.view.View;
import andioopp.view.util.ModelViewport;
import andioopp.view.util.ViewCoordinate;
/**
 * A View for displaying Coins.
 */
public class DroppedCoinsView implements View<Model> {
    /**
     * String for sprite used for displaying Coins.
     */
    public static final String COIN_SPRITE = "coin.png";
    private final ModelViewport viewport;

    public DroppedCoinsView(ModelViewport viewport) {
        this.viewport = viewport;
    }

    @Override
    public <S extends Sprite<?>> void render(Model model, Renderer<S> renderer) {
        for (DroppedCoinEntity droppedCoin : model.getWorld().getDroppedCoins()) {
            S sprite = renderer.getSpriteFactory().get(COIN_SPRITE);
            ViewCoordinate position = getPosition(droppedCoin);
            Dimension size = getSize(sprite);
            renderer.drawSprite(sprite, position, size);
        }
    }

    /**
     * Returns position of a DroppedCoin as a ViewCoordinate.
     */
    public <S extends Sprite<?>> Dimension getSize(S sprite) {
        return viewport.getSize(sprite.getSize().setHeight(0.4f));
    }

    public ViewCoordinate getPosition(DroppedCoinEntity droppedCoin) {
        return viewport.getPosition(droppedCoin.getPosition());
    }

    /**
     * Returns the size of a DroppedCoin as a Dimension.
     */
    public Dimension getSize(DroppedCoinEntity droppedCoin) {
        return viewport.getSize(droppedCoin.getSize());
    }
}
