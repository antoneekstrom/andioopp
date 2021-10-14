package andioopp.view;

import andioopp.common.graphics.Renderer;
import andioopp.common.graphics.Sprite;
import andioopp.common.math.rectangle.Rectangle;
import andioopp.common.math.transform.Transform;
import andioopp.common.math.transform.TransformFactory;
import andioopp.model.Model;
import andioopp.model.entity.DroppedCoinEntity;
import andioopp.model.world.World;
import andioopp.view.views.EntityView;

public class DroppedCoinsView<S extends Sprite<?>> extends EntityView implements View<S> {

    private final TransformFactory transformFactory;

    public DroppedCoinsView(Rectangle viewportRect, TransformFactory transformFactory) {
        super(viewportRect);
        this.transformFactory = transformFactory;
    }

    @Override
    public void render(Renderer<S> renderer, Model model) {
        World world = model.getWorld();
        for (DroppedCoinEntity droppedCoin : world.getDroppedCoins()) {
            renderDroppedCoins(renderer, world, droppedCoin);
        }
    }

    private void renderDroppedCoins(Renderer<S> renderer, World world, DroppedCoinEntity droppedCoin) {
        S sprite = droppedCoin.getSprite(renderer.getSpriteFactory());

        Transform droppedCoinTransform = transformFactory.createWithPosition(droppedCoin.getRectangle().getPosition());

        renderer.drawSprite(sprite, droppedCoinTransform, droppedCoin.getRectangle().getSize());
    }
}
