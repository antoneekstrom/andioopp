package andioopp.example;

import andioopp.gfx.*;
import andioopp.domain.model.*;
import andioopp.common.time.Clock;
import andioopp.common.time.FxClock;
import andioopp.domain.view.View;

/**
 * Initializes the game.
 */
public class ExampleProgram implements GfxProgram {
    @Override
    public <S extends Sprite<?>, R extends Renderer<S>> void run(Window<R> window) {
        initGameloop(createModel(), createView(window.getRenderer()), createClock());
    }

    private <S extends Sprite<?>> void initGameloop(Model model, View<S> view, Clock clock) {
        clock.listen(model::update);
        clock.listen((time) -> view.render(model));
        clock.start();
    }

    private Model createModel() {
        return new Model(new WaveQueue(), new Player());
    }

    private FxClock createClock() {
        return new FxClock();
    }

    private <S extends Sprite<?>, R extends Renderer<S>> View<S> createView(R renderer) {
        return new View<>(renderer);
    }
}
