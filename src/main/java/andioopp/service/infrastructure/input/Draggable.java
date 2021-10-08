package andioopp.service.infrastructure.input;

import andioopp.common.input.MouseEvent;
import andioopp.common.observer.Observer;
import andioopp.common.transform.Rectangle;

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
