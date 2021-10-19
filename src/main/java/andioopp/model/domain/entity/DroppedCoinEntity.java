package andioopp.model.domain.entity;

import andioopp.common.math.dimension.Dimension;
import andioopp.common.math.rectangle.Rectangle;
import andioopp.common.math.rectangle.ImmutableRectangle;
import andioopp.common.math.vector.Vector3f;
import andioopp.model.domain.money.Money;
import andioopp.model.util.ModelCoordinate;

public class DroppedCoinEntity {

    private final Rectangle rectangle;
    private final Money value;
    private final Dimension dimension = new Dimension(new ModelCoordinate(0.3f, 0.4f));

    public DroppedCoinEntity(Vector3f position, Money value) {
        this.rectangle = new ImmutableRectangle(position, dimension);
        this.value = value;
    }

    public Money getValue() {
        return value;
    }

    public ModelCoordinate getPosition() {
        return new ModelCoordinate(rectangle.getPosition());
    }

    public Dimension getSize() {
        return rectangle.getSize();
    }
}
