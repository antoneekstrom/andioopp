package andioopp.service.infrastructure.input;

import andioopp.common.observer.Observer;
import andioopp.common.math.rectangle.Rectangle;

public abstract class Droppable<T> implements Observer<T> {

    private final Rectangle rectangle;

    public Droppable(Rectangle rectangle) {
        this.rectangle = rectangle;
    }

    public Rectangle getRectangle() {
        return rectangle;
    }
}
