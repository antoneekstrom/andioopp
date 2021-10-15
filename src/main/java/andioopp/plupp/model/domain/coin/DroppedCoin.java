package andioopp.plupp.model.domain.coin;

import andioopp.common.math.Dimension;
import andioopp.model.money.Money;
import andioopp.plupp.common.math.Rectangle;
import andioopp.plupp.model.util.ModelCoordinate;

public class DroppedCoin {

    private final Rectangle<ModelCoordinate> rectangle;
    private final Money value;

    public DroppedCoin(Rectangle<ModelCoordinate> rectangle, Money value) {
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
