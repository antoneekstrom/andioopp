package andioopp.main;

@FunctionalInterface
public interface GameAdapter<T> {
    T get(Game<?> game);
}
