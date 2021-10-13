package andioopp.main;

import andioopp.common.time.Time;
import andioopp.service.Service;
import java.util.function.Consumer;

public class GameServiceAdapter<T> implements Service<Game<?>> {

    private final Service<T> service;
    private final GameAdapter<T> adapter;

    public GameServiceAdapter(Service<T> service, GameAdapter<T> adapter) {
        this.service = service;
        this.adapter = adapter;
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
