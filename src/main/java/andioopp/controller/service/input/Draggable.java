package andioopp.controller.service.input;

import andioopp.common.math.rectangle.Rectangle;
import andioopp.common.observer.Observer;

public abstract class Draggable<T> implements Observer<MouseEvent> {
    private final Rectangle rectangle;

    public Draggable(Rectangle rectangle) {
        this.rectangle = rectangle;
    }

    protected abstract T getDragData();

    public Rectangle getRectangle() {
        return rectangle;
    }
}
