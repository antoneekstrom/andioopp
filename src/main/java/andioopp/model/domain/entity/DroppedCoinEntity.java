package andioopp.model.domain.entity;

import andioopp.common.math.Dimension;
import andioopp.common.math.Rectangle;
import andioopp.model.domain.money.Money;
import andioopp.model.util.ModelCoordinate;

public class DroppedCoinEntity {

    private final Rectangle<ModelCoordinate> rectangle;
    private final Money value;

    public DroppedCoinEntity(Rectangle<ModelCoordinate> rectangle, Money value) {
        this.rectangle = rectangle;
        this.value = value;
    }

    public Money getValue() {
        return value;
    }

    public ModelCoordinate getPosition() {
        return rectangle.getPosition();
    }

    public Dimension getSize() {
        return rectangle.getSize();
    }
}
