package andioopp.service.infrastructure.input;

import andioopp.common.observer.Observable;
import andioopp.common.observer.Observer;
import andioopp.model.Model;
import andioopp.model.entity.DroppedCoinEntity;

public class DroppedCoinsCollectService extends MouseInputService {

    private final Model model;

    public DroppedCoinsCollectService(Observable<MouseEvent, Observer<MouseEvent>> mouseDataObservable, Model model) {
        super(mouseDataObservable);
        this.model = model;
    }

    private void onMouseDown(MouseEvent e) {
        for (DroppedCoinEntity droppedCoin : model.getWorld().getDroppedCoins()){

            //TODO Vi borde ha någon sorts bra global calculatedistance service som tar in två "positions"
            boolean isCloseEnoughX = Math.abs(e.getPosition().getX() - droppedCoin.getRectangle().getPosition().getX()) < 0.4f;
            boolean isCloseEnoughY = Math.abs(e.getPosition().getY() - droppedCoin.getRectangle().getPosition().getY()) < 0.4f;

            if(isCloseEnoughX && isCloseEnoughY){
                droppedCoin.collect(model);
            }
        }
    }
}
