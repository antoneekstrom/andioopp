package andioopp.game;

import andioopp.common.time.Time;
import andioopp.service.Service;
import java.util.function.Consumer;

/**
 * Resolves another service into one that consumes a type of {@link Game}.
 * @param <T> type of the service which is adapted
 */
public class GameServiceAdapter<T> implements Service<Game<?>> {

    private final Service<T> service;
    private final GameDependencySelector<T> adapter;

    /**
     * Creates an adapter for a service by using a selector to extract neccessary dependencies from the game.
     * @param service the service to adapt
     * @param selector transforms a {@link Game} object into a type which the service uses
     */
    public GameServiceAdapter(Service<T> service, GameDependencySelector<T> selector) {
        this.service = service;
        this.adapter = selector;
    }

    private void invokeWithAdapter(Game<?> game, Consumer<T> consumer) {
        consumer.accept(adapter.get(game));
    }

    @Override
    public void update(Game<?> game, Time time) {
        service.update(adapter.get(game), time);
    }

    @Override
    public void onSetup(Game<?> game) {
        invokeWithAdapter(game, service::onSetup);
    }

    @Override
    public void onDestroy(Game<?> game) {
        invokeWithAdapter(game, service::onDestroy);
    }
}
