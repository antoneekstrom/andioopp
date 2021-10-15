package andioopp.model.domain.entity;

import andioopp.common.math.Dimension;
import andioopp.common.math.Rectangle;
import andioopp.common.math.Vector3f;
import andioopp.common.math.rectangle.ImmutableRectangle;
import andioopp.model.Model;
import andioopp.model.domain.money.Money;
import andioopp.model.util.ModelCoordinate;

public class DroppedCoinEntity {

    private final Rectangle<ModelCoordinate> rectangle;
    private final Money value;
    private final Dimension<ModelCoordinate> dimension = new Dimension<ModelCoordinate>(new ModelCoordinate(0.3f, 0.4f));

    public DroppedCoinEntity(ModelCoordinate position, Money value) {
        this.rectangle = new ImmutableRectangle(position, dimension);
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
