package andioopp.main;

import andioopp.common.javafx.graphics.FxWindowBuilder;
import andioopp.common.javafx.time.FxClock;
import andioopp.common.observer.ConcreteObservable;
import andioopp.common.time.Clock;
import andioopp.game.MarioGame;
import javafx.application.Application;
import javafx.stage.Stage;

import java.util.ArrayList;

/**
 * Application entrypoint.
 * @author Anton Ekström
 */
public class App extends Application {

    public static void run(String[] args) {
        launch(args);
    }

    private void run(Stage stage) {
        FxWindowBuilder windowBuilder = new FxWindowBuilder(stage);
        MarioGame game = new MarioGame(windowBuilder.build());

        Clock clock = new FxClock(new ConcreteObservable<>(new ArrayList<>()));
        clock.listen(game::update);

        game.init();
        clock.start();
    }

    @Override
    public void start(Stage stage) {
        run(stage);
    }
}
