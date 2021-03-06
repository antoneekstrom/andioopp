package andioopp.controller.input;

import andioopp.common.math.rectangle.Rectangle;
import andioopp.common.observer.Observer;

public abstract class Droppable<T> implements Observer<T> {

    private final Rectangle rectangle;

    public Droppable(Rectangle rectangle) {
        this.rectangle = rectangle;
    }

    public Rectangle getArea() {
        return rectangle;
    }
}
