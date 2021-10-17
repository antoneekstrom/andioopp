package andioopp.main;

import andioopp.common.graphics.Window;
import andioopp.common.javafx.graphics.FxWindowBuilder;
import andioopp.common.javafx.time.FxClock;
import andioopp.common.observer.CollectionObservable;
import andioopp.common.observer.Observable;
import andioopp.common.observer.Observer;
import andioopp.common.storage.ArrayListFactory;
import andioopp.common.storage.ListFactory;
import andioopp.common.time.Clock;
import andioopp.common.time.Time;
import andioopp.game.MarioGame;
import javafx.application.Application;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

/**
 * Application entrypoint.
 * @author Anton Ekström
 */
public class App extends Application {

    public static void run(String[] args) {
        launch(args);
    }

    private void run(Stage stage) {
        ListFactory listFactory = ArrayList::new;
        FxWindowBuilder windowBuilder = new FxWindowBuilder(stage);
        MarioGame game = new MarioGame(windowBuilder, listFactory);

        Observable<Time, Observer<Time>> observable = new CollectionObservable<>(listFactory.create());
        Clock clock = new FxClock(observable);
        clock.addObserver(game::update);
        clock.addObserver(game::render);

        game.init();
        clock.start();
    }

    @Override
    public void start(Stage stage) {
        run(stage);
    }
}
