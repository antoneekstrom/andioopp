package andioopp.main;

import andioopp.common.graphics.*;
import andioopp.common.math.*;
import andioopp.common.math.rectangle.ImmutableRectangle;
import andioopp.common.math.rectangle.Rectangle;
import andioopp.common.math.transform.ConcreteTransform;
import andioopp.common.math.transform.TransformFactory;
import andioopp.common.observer.Observable;
import andioopp.common.observer.ConcreteObservable;
import andioopp.common.observer.Observer;
import andioopp.common.storage.ArrayListFactory;
import andioopp.common.storage.ListFactory;
import andioopp.common.time.Clock;
import andioopp.common.javafx.time.FxClock;
import andioopp.control.PlaceTowerController;
import andioopp.control.TowerCardDragEvent;
import andioopp.game.Game;
import andioopp.model.Model;
import andioopp.model.money.Money;
import andioopp.model.player.Player;
import andioopp.model.player.TowerCard;
import andioopp.model.money.Wallet;
import andioopp.model.tower.Towers;
import andioopp.model.world.LaneBuilder;
import andioopp.model.world.World;
import andioopp.model.world.WorldBuilder;
import andioopp.service.domain.*;
import andioopp.service.infrastructure.input.DragAndDropService;
import andioopp.service.infrastructure.input.MouseEvent;
import andioopp.view.views.CardsView;
import andioopp.view.views.CoinView;
import andioopp.view.views.*;

import java.util.List;

/**
 * Setup of the mario game, initializes a {@link Game} object.
 * @author Anton Ekström
 */
public class MarioGame {

    /**
     * Starts the game.
     * @param windowBuilder a builder which can produce a window for the game to use
     * @param <S> the type of sprite of the builder
     */
    public <S extends Sprite<?>> void start(WindowBuilder<? extends Window<? extends Renderer<S>>> windowBuilder) {
        ListFactory listFactory = getListFactory();
        Model model = getModel(listFactory);
        Window<? extends Renderer<S>> window = getWindow(windowBuilder);
        Clock clock = getClock(listFactory);

        Game<S> game = new Game<>(model, window, listFactory);

        registerServices(game);
        registerViewsAndControllers(game);

        start(game, clock);
    }

    private <S extends Sprite<?>> void registerTowerDragControllerAndView(Game<S> game, LanesView<S> lanesView, CardsView<S> cardsView, EnemiesView<S> enemiesView) {
        ListFactory listFactory = getListFactory();
        TransformFactory transformFactory = ConcreteTransform.getFactory();

        Observable<MouseEvent, Observer<MouseEvent>> mouseObservable = game.getWindow().getMouseObservable();
        DragAndDropService<TowerCardDragEvent> dragAndDropService = new DragAndDropService<>(mouseObservable, listFactory);

        PlaceTowerController placeTowerController = new PlaceTowerController(dragAndDropService, game.getModel(), listFactory);
        TowerDragMouseView<S> towerDragMouseView = new TowerDragMouseView<>(dragAndDropService, enemiesView, transformFactory);

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

        registerTowerDragControllerAndView(game, lanesView, cardsView, enemiesView);
    }

    private <S extends Sprite<?>> Rectangle getViewportRect(Game<S> game) {
        Dimension windowSize = game.getWindow().getSize();
        Dimension viewportSize = windowSize.setHeight(windowSize.getHeight() * 0.7f);
        Vector3f viewportPosition = windowSize.centerWithin(Vector3f.zero(), viewportSize);
        viewportPosition = viewportPosition.setX(windowSize.getWidth()- viewportSize.getWidth());

        return new ImmutableRectangle(viewportPosition, viewportSize);
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

    private <S extends Sprite<?>> Window<? extends Renderer<S>> getWindow(WindowBuilder<? extends Window<? extends Renderer<S>>> windowBuilder) {
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
        return new FxClock(new ConcreteObservable<>(listFactory.create()));
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
        Player player = new Player(cards, new Wallet(new Money(100)));

        WorldBuilder worldBuilder = new WorldBuilder(new LaneBuilder(listFactory).setCells(7), listFactory).setLanes(5);
        World build = worldBuilder.build();

        return new Model(build, player);
    }

}
