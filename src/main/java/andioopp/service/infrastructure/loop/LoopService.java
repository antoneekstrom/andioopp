package andioopp.service.infrastructure.loop;

import andioopp.common.graphics.Renderer;
import andioopp.common.graphics.Sprite;
import andioopp.common.observer.Observer;
import andioopp.common.time.Clock;
import andioopp.common.time.Time;
import andioopp.model.Model;
import andioopp.view.View;

public class LoopService {

    private final Clock clock;

    public LoopService(Clock clock) {
        this.clock = clock;
    }

    public <S extends Sprite<?>> void start(Renderer<S> renderer, View<S> view, Model model) {
        init(renderer, model, view);
        getClock().start();
    }

    public void stop() {
        getClock().stop();
        getClock().unlistenAll();
    }

    private <S extends Sprite<?>> void init(Renderer<S> renderer, Model model, View<S> view) {
        getClock().listen(getUpdateModel(model));
        getClock().listen(getUpdateView(renderer, model, view));
    }

    private <S extends Sprite<?>> Observer<Time> getUpdateView(Renderer<S> renderer, Model model, View<S> view) {
        return (time) -> view.render(renderer, model);
    }

    private Observer<Time> getUpdateModel(Model model) {
        return model::update;
    }

    private Clock getClock() {
        return clock;
    }
}
