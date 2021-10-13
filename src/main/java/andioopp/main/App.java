package andioopp.main;

import andioopp.common.graphics.Color;
import andioopp.common.graphics.Sprite;
import andioopp.common.graphics.javafx.FxSprite;
import andioopp.common.graphics.javafx.FxWindow;
import andioopp.common.graphics.javafx.FxWindowBuilder;
import andioopp.common.observer.Observable;
import andioopp.common.observer.ObservableWithList;
import andioopp.common.observer.Observer;
import andioopp.common.storage.ArrayListFactory;
import andioopp.common.storage.ListFactory;
import andioopp.common.time.Clock;
import andioopp.common.time.FxClock;
import andioopp.common.transform.*;
import andioopp.control.PlaceTowerController;
import andioopp.control.TowerCardDragEvent;
import andioopp.model.BaseModel;
import andioopp.model.Model;
import andioopp.model.player.Money;
import andioopp.model.player.Player;
import andioopp.model.player.TowerCard;
import andioopp.model.tower.Towers;
import andioopp.model.world.LaneBuilder;
import andioopp.model.world.World;
import andioopp.model.world.WorldBuilder;
import andioopp.service.domain.*;
import andioopp.service.infrastructure.input.DragAndDropService;
import andioopp.service.infrastructure.input.MouseEvent;
import andioopp.view.*;
import andioopp.view.gui.CardsView;
import andioopp.view.gui.CoinView;
import javafx.application.Application;
import javafx.stage.Stage;

import java.util.List;

/**
 * Application entrypoint.
 */
public class App extends Application {

    public static void run(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) {
        run(stage);
    }

    private void run(Stage stage) {
        ListFactory listFactory = getListFactory();
        Model model = getModel(listFactory);
        FxWindow window = getWindow(stage);
        Clock clock = getClock(listFactory);

        Game<FxSprite> game = new Game<>(model, window, listFactory);
        
        registerServices(game);
        registerViewsAndControllers(game);
        
        start(game, clock);
    }
    
    private <S extends Sprite<?>> void registerTowerDragControllerAndView(Game<S> game, LanesView<S> lanesView, CardsView<S> cardsView) {
        ListFactory listFactory = getListFactory();
        
        Observable<MouseEvent, Observer<MouseEvent>> mouseObservable = game.getWindow().getMouseObservable();
        DragAndDropService<TowerCardDragEvent> dragAndDropService = new DragAndDropService<>(mouseObservable, listFactory);

        PlaceTowerController placeTowerController = new PlaceTowerController(dragAndDropService, game.getModel(), listFactory);
        TowerDragMouseView<S> towerDragMouseView = new TowerDragMouseView<>(dragAndDropService);

        placeTowerController.register(lanesView, cardsView);
        
        game.registerView(towerDragMouseView);
    }
    
    private <S extends Sprite<?>> void registerViewsAndControllers(Game<S> game) {
        TransformFactory transformFactory = ConcreteTransform.getFactory();
        Rectangle viewportRect = getViewportRect(game);

        CardsView<S> cardsView = new CardsView<>(viewportRect);
        CoinView<S> coinView = new CoinView<>(transformFactory);
        LanesView<S> lanesView = new LanesView<>(viewportRect);
        ProjectilesView<S> projectilesView = new ProjectilesView<>(viewportRect, transformFactory);
        EnemiesView<S> enemiesView = new EnemiesView<>(viewportRect, transformFactory);
        TowersView<S> towersView = new TowersView<>(viewportRect, transformFactory);

        game.registerView((renderer, model) -> renderer.clear(Color.WHITE));

        game.registerView(lanesView);
        game.registerView(towersView);
        game.registerView(enemiesView);
        game.registerView(projectilesView);
        game.registerView(coinView);
        game.registerView(cardsView);

        registerTowerDragControllerAndView(game, lanesView, cardsView);
    }

    private <S extends Sprite<?>> Rectangle getViewportRect(Game<S> game) {
        Dimension windowSize = new Dimension(game.getWindow().getWidth(), game.getWindow().getHeight());
        Dimension viewportSize = windowSize.scaleByHeight(windowSize.getHeight() * 0.7f);
        Vector3f viewportPosition = windowSize.centerWithin(Vector3f.zero(), viewportSize);
        viewportPosition = viewportPosition.setX(windowSize.getWidth()- viewportSize.getWidth());

        return new Rectangle(viewportPosition, viewportSize);
    }

    private void registerServices(Game<?> game) {
        game.registerDomainService(new UpdateEnemyService());
        game.registerDomainService(new UpdateProjectileService());
        game.registerDomainService(new PerformAttackService());
        game.registerDomainService(new EnemyProjectileCollisionService());
        game.registerDomainService(new HandleEnemyAttackService());
        game.registerDomainService(new DespawnOutOfBoundsService());
        game.registerDomainService(new UpdateWavesService());
    }

    private FxWindow getWindow(Stage stage) {
        FxWindowBuilder windowBuilder = new FxWindowBuilder(stage);
        windowBuilder.setIcon("luigi.png");
        windowBuilder.setResizable(true);
        windowBuilder.setSize(new Dimension(1280, 720));
        windowBuilder.setTitle("and I OOPP");
        return windowBuilder.build();
    }

    private void start(Game<?> game, Clock clock) {
        game.setup();
        clock.listen(game::update);
        clock.start();
    }

    private FxClock getClock(ListFactory listFactory) {
        return new FxClock(new ObservableWithList<>(listFactory.create()));
    }

    private ArrayListFactory getListFactory() {
        return new ArrayListFactory();
    }

    private Model getModel(ListFactory listFactory) {
        List<TowerCard<?>> cards = listFactory.create(
                new TowerCard<>(new Money(40), Towers::toad),
                new TowerCard<>(new Money(60), Towers::mario),
                new TowerCard<>(new Money(-1), Towers::rosalina),
                new TowerCard<>(new Money(-1), Towers::bobomb),
                new TowerCard<>(new Money(-1), Towers::yoshi),
                new TowerCard<>(new Money(-1), Towers::luigi)
        );
        Player player = new Player(cards, new Money(100));

        WorldBuilder worldBuilder = new WorldBuilder(new LaneBuilder(listFactory).setCells(7), listFactory).setLanes(5);
        World build = worldBuilder.build();

        return new BaseModel(build, player);
    }
}
