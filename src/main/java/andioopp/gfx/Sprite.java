package andioopp.gfx;

import javafx.geometry.Point2D;

public interface Sprite<T> {
    T getImage();
    Point2D getPosition();
}
