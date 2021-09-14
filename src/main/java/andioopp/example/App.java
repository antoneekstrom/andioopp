package andioopp.example;

import andioopp.common.transform.ConcreteTransform;
import andioopp.common.transform.TransformFactory;
import andioopp.domain.model.enemy.EnemyFactory;
import andioopp.domain.model.towers.Mario;
import andioopp.domain.model.towers.Tower;
import andioopp.gfx.*;
import andioopp.domain.model.*;
import andioopp.common.time.Clock;
import andioopp.common.time.FxClock;
import andioopp.domain.view.View;

import java.util.ArrayList;

public class App implements GfxProgram {
    @Override
    public <S extends Sprite<?>, R extends Renderer<S>> void run(Window<R> window) {
        R renderer = window.getRenderer();
        initGameloop(createModel(), createView(renderer), createClock());
    }

    private <S extends Sprite<?>> void initGameloop(Model model, View<S> view, Clock clock) {
        clock.listen(model::update);
        clock.listen((time) -> view.render(model));
        clock.start();

        /*Tower mario = new Mario();
        model.placeTower(mario, model.getCell(1, 3));*/

        // här kan man börja skrivas
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
