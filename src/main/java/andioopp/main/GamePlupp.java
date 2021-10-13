package andioopp.main;

import andioopp.common.graphics.*;
import andioopp.common.observer.ObservableWithList;
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
import andioopp.model.tower.Tower;
import andioopp.model.tower.Towers;
import andioopp.model.world.LaneBuilder;
import andioopp.model.world.World;
import andioopp.model.world.WorldBuilder;
import andioopp.service.domain.*;
import andioopp.service.infrastructure.input.DragAndDropService;
import andioopp.view.GameView;
import andioopp.view.View;

import java.util.Arrays;
import java.util.List;

/**
 * Initializes the game. Completely decoupled from platform and rendering.
 */
public class GamePlupp implements GfxProgram {

    private DragAndDropService<TowerCardDragEvent> dragAndDropService;

    @Override
    public <S extends Sprite<?>, R extends Renderer<S>, W extends Window<R>> void run(WindowBuilder<W> windowBuilder) {
        ListFactory listFactory = new ArrayListFactory();
        Clock clock = new FxClock(new ObservableWithList<>(listFactory.create()));
        Window<? extends Renderer<S>> window = createWindow(windowBuilder);
        init(window, clock, listFactory);
        clock.start();
    }

    public <S extends Sprite<?>> void init(Window<? extends Renderer<S>> window, Clock clock, ListFactory listFactory) {
        GameView<S> view = createView(window);
        Model model = createModel();

        clock.listen((time) -> view.render(window.getRenderer(), model));

        addControllers(listFactory, window, view, model);
        addServices(clock, model);
        addViews();
    }

    private <S extends Sprite<?>> void addView(View<S> view) {

    }

    private void addViews() {

    }

    private void addServices(Clock clock, Model model) {
        addService(new UpdateEnemyService(), model, clock);
        addService(new UpdateProjectileService(), model, clock);
        addService(new PerformAttackService(), model, clock);
        addService(new EnemyProjectileCollisionService(), model, clock);
        addService(new HandleEnemyAttackService(), model, clock);
        addService(new DespawnOutOfBoundsService(), model, clock);
        addService(new UpdateWavesService(), model, clock);
    }

    private void addService(DomainService service, Model model, Clock clock) {
        service.onSetup(model);
        clock.listen((time) -> service.update(model, time));
    }

    private <S extends Sprite<?>> void addControllers(ListFactory listFactory, Window<? extends Renderer<S>> window, GameView<S> view, Model model) {
        dragAndDropService = new DragAndDropService<>(window.getMouseObservable(), listFactory);
        PlaceTowerController placeTowerController = new PlaceTowerController(dragAndDropService, model, listFactory);
        placeTowerController.register(view.getLanesView(), view.getCardsView());
    }

    private <S extends Sprite<?>> Window<? extends Renderer<S>> createWindow(WindowBuilder<? extends Window<? extends Renderer<S>>> builder) {
        builder.setSize(new Dimension(1280, 720));
        builder.setResizable(true);
        builder.setIcon("mario_icon.png");
        return builder.build();
    }

    private <S extends Sprite<?>> GameView<S> createView(Window<? extends Renderer<S>> window) {
        TransformFactory transformFactory = ConcreteTransform.getFactory();

        Vector3f worldSizeFactor = new Vector3f(0.7f, 0.7f);
        Dimension windowSize = new Dimension(window.getWidth(), window.getHeight());

        Dimension worldSize = new Dimension(windowSize.toVector().scale(worldSizeFactor));
        Vector3f worldPos = new Vector3f(windowSize.getWidth() - (worldSize.getWidth() * 1.01f), windowSize.getHeight() - (worldSize.getHeight() * 1.10f));
        Rectangle viewportRect = new Rectangle(worldPos, worldSize);

        return new GameView<>(viewportRect, transformFactory, dragAndDropService);
    }

    private List<TowerCard<?>> getCards() {
        TowerCard<Tower> mario = new TowerCard<>(new Money(60), Towers::mario);
        TowerCard<Tower> toad = new TowerCard<>(new Money(40), Towers::toad);
        return Arrays.asList(mario, toad);
    }

    private Model createModel() {
        Player player = new Player(getCards(), new Money(100));
        WorldBuilder worldBuilder = new WorldBuilder(new LaneBuilder(new ArrayListFactory()).setCells(7), new ArrayListFactory());
        World world = worldBuilder.setLanes(5).build();
        return new BaseModel(world, player);
    }
}
