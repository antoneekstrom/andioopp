package andioopp.controller.service.input;

import andioopp.common.math.rectangle.RectanglePlupp;
import andioopp.common.observer.Observer;

public abstract class Draggable<T> implements Observer<MouseEvent> {
    private final RectanglePlupp rectangle;

    public Draggable(RectanglePlupp rectangle) {
        this.rectangle = rectangle;
    }

    protected abstract T getDragData();

    public RectanglePlupp getRectangle() {
        return rectangle;
    }
}
