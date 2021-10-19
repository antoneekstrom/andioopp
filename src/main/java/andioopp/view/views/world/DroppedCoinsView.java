package andioopp.view.views.world;

import andioopp.common.graphics.Renderer;
import andioopp.common.graphics.Sprite;
import andioopp.common.math.dimension.Dimension;
import andioopp.model.domain.entity.DroppedCoinEntity;
import andioopp.model.Model;
import andioopp.view.View;
import andioopp.view.util.ModelViewport;
import andioopp.view.util.ViewCoordinate;

public class DroppedCoinsView implements View<Model> {

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
            Dimension size = getSize(droppedCoin);
            renderer.drawSprite(sprite, position, size);
        }
    }

    public ViewCoordinate getPosition(DroppedCoinEntity droppedCoin) {
        return viewport.getViewCoordinate(droppedCoin.getPosition());
    }

    public Dimension getSize(DroppedCoinEntity droppedCoin) {
        return viewport.getViewSize(droppedCoin.getSize());
    }
}
