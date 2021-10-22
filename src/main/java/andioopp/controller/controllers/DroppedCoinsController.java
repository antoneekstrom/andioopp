package andioopp.controller.controllers;

import andioopp.common.graphics.Renderer;
import andioopp.common.graphics.Window;
import andioopp.common.math.dimension.Dimension;
import andioopp.common.math.rectangle.ImmutableRectangle;
import andioopp.common.observer.Observer;
import andioopp.controller.Controller;
import andioopp.controller.input.MouseEventType;
import andioopp.controller.input.MouseInputEvent;
import andioopp.model.Model;
import andioopp.model.domain.entity.DroppedCoinEntity;
import andioopp.view.util.ViewCoordinate;
import andioopp.view.views.world.DroppedCoinsView;

import java.util.Collection;
import java.util.Iterator;

/**
 * Controller for picking up dropped coins
 */
public class DroppedCoinsController implements Controller<Model>, Observer<MouseInputEvent> {

    private final DroppedCoinsView droppedCoinsView;
    private Renderer<?> renderer;
    private Model model;

    public DroppedCoinsController(DroppedCoinsView droppedCoinsView) {
        this.droppedCoinsView = droppedCoinsView;
    }

    @Override
    public void init(Model model, Window<?> window) {
        this.model = model;
        this.renderer = window.getRenderer();
        window.getMouseInput().getMouseClickObservable().addObserver(this);
    }

    @Override
    public void deinit(Model model, Window<?> window) {
        window.getMouseInput().getMouseClickObservable().removeObserver(this);
    }

    @Override
    public void onEvent(MouseInputEvent event) {
        Collection<DroppedCoinEntity> droppedCoins = model.getWorld().getDroppedCoins();
        for (Iterator<DroppedCoinEntity> iter = droppedCoins.iterator(); iter.hasNext();) {
            DroppedCoinEntity droppedCoin = iter.next();
            if (clickedOnCoin(event, droppedCoin)) {
                model.getPlayer().giveMoney(droppedCoin.getValue());
                iter.remove();
            }
        }
    }

    private boolean clickedOnCoin(MouseInputEvent event, DroppedCoinEntity droppedCoin) {
        if (event.getType() != MouseEventType.RELEASE) {
            return false;
        }
        return getRectangle(droppedCoin).contains(event.getMousePosition());
    }

    private ImmutableRectangle getRectangle(DroppedCoinEntity droppedCoin) {
        ViewCoordinate position = droppedCoinsView.getPosition(droppedCoin);
        Dimension size = droppedCoinsView.getSize(renderer.getSpriteFactory().get("coin.png"));
        return new ImmutableRectangle(position, size);
    }

}
