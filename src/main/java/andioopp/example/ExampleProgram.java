package andioopp.example;

import andioopp.common.transform.Vector3f;
import andioopp.domain.view.View;
import andioopp.gfx.*;
import andioopp.domain.model.*;
import andioopp.common.time.Clock;
import andioopp.common.time.FxClock;

/**
 * Initializes the game.
 */
public class ExampleProgram implements GfxProgram {
    @Override
    public <S extends Sprite<?>, R extends Renderer<S>> void run(Window<R> window) {
        initGameloop(createModel(), createView(window), createClock());
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

    private <S extends Sprite<?>, R extends Renderer<S>> View<S> createView(Window<R> window) {
        float worldSizeFactorX = 0.7f;
        float worldSizeFactorY = 0.7f;

        Vector3f windowSize = new Vector3f(window.getWidth(), window.getHeight());
        Vector3f worldSize = new Vector3f(windowSize.getX() * worldSizeFactorX, windowSize.getY() * worldSizeFactorY);
        Vector3f worldPos = new Vector3f(windowSize.getX() - worldSize.getX(), (windowSize.getY() - worldSize.getY()) * 0.5f);

        return new View<>(window.getRenderer(), worldPos, worldSize);
    }
}
