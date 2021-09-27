package andioopp.service.infrastructure.loop;

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

    public void start(Model model, View<?> view) {
        init(model, view);
        getClock().start();
    }

    public void stop() {
        getClock().stop();
        getClock().unlistenAll();
    }

    private void init(Model model, View<?> view) {
        getClock().listen(getUpdateModel(model));
        getClock().listen(getUpdateView(model, view));
    }

    private Observer<Time> getUpdateView(Model model, View<?> view) {
        return (time) -> view.render(model);
    }

    private Observer<Time> getUpdateModel(Model model) {
        return model::update;
    }

    private Clock getClock() {
        return clock;
    }
}
