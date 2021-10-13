package andioopp.game;

/**
 * Transforms a {@link Game} into another type.
 * @author Anton Ekström
 * @param <T> return type
 */
@FunctionalInterface
public interface GameDependencySelector<T> {
    T get(Game<?> game);
}
