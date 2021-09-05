package andioopp.gfx;

import javafx.geometry.Point2D;

@FunctionalInterface
public interface SpriteFactory<T extends Sprite<?>> {
    T create(String path, Point2D position);
}
