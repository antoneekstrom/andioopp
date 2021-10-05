package andioopp.service.infrastructure.input;

import andioopp.common.input.MouseData;
import andioopp.common.observer.Observer;
import andioopp.common.transform.Rectangle;

public abstract class Draggable<T> implements Observer<MouseData> {
    private final Rectangle rectangle;

    public Draggable(Rectangle rectangle) {
        this.rectangle = rectangle;
    }

    abstract T getDragData();

    public Rectangle getRectangle() {
        return rectangle;
    }
}
