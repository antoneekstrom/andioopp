package andioopp.plupp.view.views;

import andioopp.common.graphics.Renderer;
import andioopp.common.graphics.Sprite;
import andioopp.plupp.model.Model;
import andioopp.plupp.model.domain.coin.DroppedCoin;
import andioopp.plupp.view.View;
import andioopp.plupp.view.util.ModelViewport;
import andioopp.plupp.view.util.ViewCoordinate;

public class DroppedCoinsView implements View<Model> {
    @Override
    public <S extends Sprite<?>> void render(Model model, Renderer<S> renderer, ModelViewport viewport) {
        for (DroppedCoin droppedCoin : model.getWorld().getDroppedCoins()) {
            ViewCoordinate viewCoordinate = viewport.getViewCoordinate(droppedCoin.getPosition());
            S sprite = renderer.getSpriteFactory().get("coin.jpg");
            renderer.drawSprite(sprite, viewCoordinate);
        }
    }
}
