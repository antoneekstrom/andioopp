package andioopp.controller.controllers;

import andioopp.common.graphics.Window;
import andioopp.common.math.dimension.Dimension;
import andioopp.common.math.rectangle.ImmutableRectangle;
import andioopp.common.observer.Observer;
import andioopp.controller.Controller;
import andioopp.controller.input.MouseInputEvent;
import andioopp.model.Model;
import andioopp.model.domain.enemy.Enemy;
import andioopp.model.domain.entity.DroppedCoinEntity;
import andioopp.view.util.ViewCoordinate;
import andioopp.view.views.world.DroppedCoinsView;

import java.util.Collection;
import java.util.Iterator;

public class DroppedCoinsController implements Controller<Model>, Observer<MouseInputEvent> {

    private final DroppedCoinsView droppedCoinsView;

    private Model model;

    public DroppedCoinsController(DroppedCoinsView droppedCoinsView) {
        this.droppedCoinsView = droppedCoinsView;
    }

    @Override
    public void init(Model model, Window<?> window) {
        this.model = model;
        window.getMouseObservable().addObserver(this);
    }

    @Override
    public void deinit(Model model, Window<?> window) {
        window.getMouseObservable().removeObserver(this);
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
        if (event.getType() != MouseInputEvent.MouseEventType.RELEASE) {
            return false;
        }
        return getRectangle(droppedCoin).contains(event.getPosition());
    }

    private ImmutableRectangle getRectangle(DroppedCoinEntity droppedCoin) {
        ViewCoordinate position = droppedCoinsView.getPosition(droppedCoin);
        Dimension size = droppedCoinsView.getSize(droppedCoin);
        return new ImmutableRectangle(position, size);
    }

}
