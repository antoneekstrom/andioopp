package andioopp.view.views.world;

import andioopp.common.graphics.Renderer;
import andioopp.common.graphics.Sprite;
import andioopp.common.math.Dimension;
import andioopp.model.domain.entity.DroppedCoinEntity;
import andioopp.model.Model;
import andioopp.view.View;
import andioopp.view.util.ModelViewport;
import andioopp.view.util.ViewCoordinate;

public class DroppedCoinsView implements View<Model> {
    @Override
    public <S extends Sprite<?>> void render(Model model, Renderer<S> renderer, ModelViewport viewport) {
        for (DroppedCoinEntity droppedCoin : model.getWorld().getDroppedCoins()) {
            S sprite = renderer.getSpriteFactory().get("coin.jpg");
            ViewCoordinate position = viewport.getViewCoordinate(droppedCoin.getPosition());
            Dimension size = viewport.getViewSize(droppedCoin.getSize());
            renderer.drawSprite(sprite, position, size);
        }
    }
}
