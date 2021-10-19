package andioopp.model.domain.entity;

import andioopp.model.domain.money.Money;
import andioopp.model.util.ModelCoordinate;

public class DroppedCoinEntity {

    private ModelCoordinate position;
    private final Money value;

    public DroppedCoinEntity(ModelCoordinate position, Money value) {
        this.position = position;
        this.value = value;
    }

    public Money getValue() {
        return value;
    }

    public ModelCoordinate getPosition() {
        return position;
    }
}
