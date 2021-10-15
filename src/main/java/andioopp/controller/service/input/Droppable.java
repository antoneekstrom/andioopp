package andioopp.controller.service.input;

import andioopp.common.math.rectangle.RectanglePlupp;
import andioopp.common.observer.Observer;

public abstract class Droppable<T> implements Observer<T> {

    private final RectanglePlupp rectangle;

    public Droppable(RectanglePlupp rectangle) {
        this.rectangle = rectangle;
    }

    public RectanglePlupp getRectangle() {
        return rectangle;
    }
}
