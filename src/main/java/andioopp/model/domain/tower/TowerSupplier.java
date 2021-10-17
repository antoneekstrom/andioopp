package andioopp.model.domain.tower;

import andioopp.model.util.ModelCoordinate;

@FunctionalInterface
public interface TowerSupplier<T extends Tower> {
    T create(ModelCoordinate position);
}
