package andioopp.example;

import andioopp.common.transform.ConcreteTransform;
import andioopp.common.transform.TransformFactory;
import andioopp.domain.model.enemy.EnemyFactory;
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
        initGameloop(createModel(ConcreteTransform.getFactory(), new EnemyFactory()), createView(renderer), createClock());
    }

    private <S extends Sprite<?>> void initGameloop(Model model, View<S> view, Clock clock) {
        clock.listen(model::update);
        clock.listen((time) -> view.render(model));
        clock.start();
    }

    private Model createModel(TransformFactory transformFactory, EnemyFactory enemyFactory) {
        Lane lane = new Lane(transformFactory.create());
        lane.getEnemies().add(enemyFactory.goomba());

        World world = new World(new ArrayList<>());
        world.getLanes().add(lane);

        return new Model(world, new WaveQueue(), new Player());
    }

    private FxClock createClock() {
        return new FxClock();
    }

    private <S extends Sprite<?>, R extends Renderer<S>> View<S> createView(R renderer) {
        return new View<>(renderer);
    }
}
