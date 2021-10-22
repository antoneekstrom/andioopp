package andioopp.model.domain.entity;

import andioopp.model.domain.money.Money;
import andioopp.model.util.ModelCoordinate;

/**
 * An entity of a dropped coin
 */
public class DroppedCoinEntity {

    private ModelCoordinate position;
    private final Money value;

    public DroppedCoinEntity(ModelCoordinate position, Money value) {
        this.position = position;
        this.value = value;
    }

    /**
     * Returns value of coin
     */
    public Money getValue() {
        return value;
    }

    /**
     * Returns position of coin
     */
    public ModelCoordinate getPosition() {
        return new ModelCoordinate(rectangle.getPosition());
    }

    /**
     * Returns size of coin
     */
    public Dimension getSize() {
        return rectangle.getSize();
    }
}
