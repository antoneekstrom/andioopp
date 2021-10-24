package andioopp.game;

import andioopp.common.graphics.Window;
import andioopp.common.graphics.WindowBuilder;
import andioopp.common.math.dimension.Dimension;
import andioopp.common.math.range.IntRange;
import andioopp.common.math.rectangle.ImmutableRectangle;
import andioopp.common.math.rectangle.Rectangle;
import andioopp.common.math.vector.Vector3f;
import andioopp.common.storage.ListFactory;
import andioopp.common.time.Time;
import andioopp.controller.Controller;
import andioopp.controller.controllers.DroppedCoinsController;
import andioopp.controller.controllers.PlaceTowerController;
import andioopp.controller.controllers.TowerCardDragEvent;
import andioopp.controller.input.DragAndDrop;
import andioopp.controller.input.MouseInput;
import andioopp.model.Model;
import andioopp.model.domain.money.Money;
import andioopp.model.domain.money.Wallet;
import andioopp.model.domain.player.Player;
import andioopp.model.domain.player.TowerCard;
import andioopp.model.domain.tower.TowerFactory;
import andioopp.model.domain.waves.WaveQueue;
import andioopp.model.domain.world.LaneBuilder;
import andioopp.model.domain.world.World;
import andioopp.model.domain.world.WorldBuilder;
import andioopp.model.services.EnemyDropCoinService;
import andioopp.model.system.System;
import andioopp.model.system.systems.*;
import andioopp.view.View;
import andioopp.view.util.ModelViewport;
import andioopp.view.util.Viewport;
import andioopp.view.views.gui.CardsView;
import andioopp.view.views.gui.CellHighlightView;
import andioopp.view.views.gui.MoneyView;
import andioopp.view.views.gui.TowerDragMouseView;
import andioopp.view.views.world.*;

import java.util.List;

/**
 * An implementation of {@link Game} for the mario game.
 *
 * @author Anton Ekström
 */
public class MarioGame extends Game<Model> {

    private static final Dimension WINDOW_SIZE = new Dimension(1280, 720);
    private CardsView cardsView;
    private LanesView lanesView;
    private DragAndDrop<TowerCardDragEvent> dragAndDrop;
    private DroppedCoinsView droppedCoinsView;

    private PlaceTowerController placeTowerController;
    private RemoveDeadEnemiesSystem removeDeadEnemiesSystem;

    private boolean gameOver = false;

    /**
     * Creates the mario game.
     *
     * @param windowBuilder the window builder
     * @param listFactory the listfactory
     */
    public MarioGame(WindowBuilder<? extends Window<?>> windowBuilder, ListFactory listFactory) {
        super(listFactory, windowBuilder);
    }

    @Override
    public void init() {
        super.init();
        initCellHighlightView();
    }

    @Override
    public void update(Time time) {
        if(!gameOver) {
            super.update(time);
        }
    }

    @Override
    protected Window<?> initWindow(WindowBuilder<? extends Window<?>> windowBuilder) {
        Window<?> window = windowBuilder
                .setIcon("mario_icon.png")
                .setSize(WINDOW_SIZE)
                .setTitle("and I OOPP")
                .setResizable(true)
                .build();

        dragAndDrop = new DragAndDrop<>(getListFactory());
        window.getMouseInput().getMouseMoveObservable().addObserver(dragAndDrop::onMoveEvent);
        window.getMouseInput().getMouseClickObservable().addObserver(dragAndDrop::onClickEvent);

        return window;
    }

    @Override
    protected List<System<Model>> initSystems() {
        WaveQueue waves = new WaveQueue().addWaves(7, new IntRange(3, 5));
        removeDeadEnemiesSystem = new RemoveDeadEnemiesSystem(getListFactory().create());

        return getListFactory().create(
                new PerformTowerAttackSystem(),
                new MoveEnemySystem(),
                new UpdateWaveSystem(waves, new IntRange(8, 20)),
                new UpdateProjectileSystem(),
                new EnemyProjectileCollisionSystem(),
                new HandleEnemyAttackSystem(),
                new DespawnOutOfBoundsSystem(this),
                new RemoveDeadTowersSystem(),
                new RemoveDeadProjectilesSystem(),
                removeDeadEnemiesSystem
        );
    }

    @Override
    protected List<View<Model>> initViews() {
        ModelViewport modelViewport = getModelViewport();
        Viewport moneyViewport = getMoneyViewport();
        Vector3f cardsViewPosition = getCardsViewPosition();
        MouseInput mouseInput = getWindow().getMouseInput();

        cardsView = new CardsView(cardsViewPosition);
        lanesView = new LanesView(modelViewport);
        TowersView towersView = new TowersView(modelViewport);
        droppedCoinsView = new DroppedCoinsView(modelViewport);
        TowerDragMouseView towerDragMouseView = new TowerDragMouseView(dragAndDrop, towersView);
        mouseInput.getMouseMoveObservable().addObserver(towerDragMouseView);

        return getListFactory().create(
                lanesView,
                cardsView,
                new MoneyView(moneyViewport),
                towersView,
                new EnemiesView(modelViewport),
                towerDragMouseView,
                new ProjectilesView(modelViewport),
                droppedCoinsView
        );
    }

    @Override
    protected List<Controller<Model>> initControllers() {
        placeTowerController = new PlaceTowerController(dragAndDrop, cardsView, lanesView);
        return getListFactory().create(
                placeTowerController,
                new DroppedCoinsController(droppedCoinsView)
        );
    }

    @Override
    protected Model initModel() {
        List<TowerCard<?>> cards = getCards();
        Player player = getPlayer(cards);
        WorldBuilder worldBuilder = getWorldBuilder(getListFactory());
        World build = worldBuilder.build();
        return new Model(build, player);
    }

    @Override
    protected void initServices() {
        removeDeadEnemiesSystem.addObserver(new EnemyDropCoinService(getModel()));
    }

    /**
     * Sets the gameover state. If set to true then the game will won't update.
     *
     * @param state the state
     */
    public void setGameOver(boolean state) {
        this.gameOver = state;
    }

    // Controllers are initialized after views, but we need a controller for this view!
    private void initCellHighlightView() {
        CellHighlightView cellHighlightView = new CellHighlightView(dragAndDrop, placeTowerController.getDroppables());
        int i = getViews().indexOf(lanesView) + 1;
        getViews().add(i, cellHighlightView);
    }

    private Vector3f getCardsViewPosition() {
        return new Vector3f(MoneyView.DEFAULT_SIZE.getWidth() + 15 + 50, 15);
    }

    private Viewport getMoneyViewport() {
        Vector3f position = new Vector3f(15, 15);
        return new Viewport(Dimension.UNIT, MoneyView.DEFAULT_SIZE, position);
    }

    private ModelViewport getModelViewport() {
        Dimension windowSize = getWindow().getSize();
        Dimension size = new Dimension(windowSize.toVector().scale(0.6f));
        ImmutableRectangle viewportRectangle = new ImmutableRectangle(Vector3f.zero(), size);
        ImmutableRectangle windowRectangle = new ImmutableRectangle(Vector3f.zero(), windowSize);
        Rectangle centeredViewport = windowRectangle.centerWithin(viewportRectangle);
        centeredViewport = centeredViewport.translate(size.toVector().scale(0.1f).fromY());
        Vector3f offset = centeredViewport.getPosition();
        return new ModelViewport(getModel(), size, offset);
    }

    private List<TowerCard<?>> getCards() {
        return getListFactory().create(
                new TowerCard<>(new Money(50), TowerFactory::createMario),
                new TowerCard<>(new Money(30), TowerFactory::createToad),
                new TowerCard<>(new Money(40), TowerFactory::createLuigi),
                new TowerCard<>(new Money(40), TowerFactory::createBobomb)
        );
    }

    private Player getPlayer(List<TowerCard<?>> cards) {
        return new Player(cards, new Wallet(new Money(100)));
    }

    private WorldBuilder getWorldBuilder(ListFactory listFactory) {
        return new WorldBuilder(new LaneBuilder(listFactory).setCells(9), listFactory).setLanes(5);
    }
}
