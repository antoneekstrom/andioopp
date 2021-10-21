package andioopp.model.domain.entity;

import andioopp.common.math.dimension.Dimension;
import andioopp.common.math.rectangle.Rectangle;
import andioopp.common.math.rectangle.ImmutableRectangle;
import andioopp.model.domain.money.Money;
import andioopp.model.util.ModelCoordinate;

/**
 * An entity of a dropped coin
 */
public class DroppedCoinEntity {

    private final Rectangle rectangle;
    private final Money value;
    private final Dimension dimension = new Dimension(new ModelCoordinate(0.3f, 0.4f));

    public DroppedCoinEntity(ModelCoordinate position, Money value) {
        this.rectangle = new ImmutableRectangle(position, dimension);
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
