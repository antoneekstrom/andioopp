package andioopp.main;

import andioopp.common.graphics.*;
import andioopp.common.observer.ObservableWithList;
import andioopp.common.storage.ArrayListFactory;
import andioopp.common.storage.ListFactory;
import andioopp.common.time.FxClock;
import andioopp.common.transform.ConcreteTransform;
import andioopp.control.PlaceTowerController;
import andioopp.control.TowerCardDragEvent;
import andioopp.model.Model;
import andioopp.model.player.Money;
import andioopp.model.player.Player;
import andioopp.model.player.TowerCard;
import andioopp.model.tower.Tower;
import andioopp.model.tower.Towers;
import andioopp.model.waves.WaveQueue;
import andioopp.service.infrastructure.graphics.WindowingService;
import andioopp.service.infrastructure.input.DragAndDropService;
import andioopp.service.infrastructure.loop.LoopService;
import andioopp.view.GameView;

import java.util.Arrays;
import java.util.List;

/**
 * Initializes the game. Completely decoupled from platform and rendering.
 */
public class Game implements GfxProgram {
    @Override
    public <S extends Sprite<?>, R extends Renderer<S>, W extends Window<R>> void run(WindowBuilder<W> windowBuilder) {
        WindowingService<W> windowingService = new WindowingService<>(windowBuilder);
        ListFactory listFactory = new ArrayListFactory();
        LoopService loopService = new LoopService(new FxClock(new ObservableWithList<>(listFactory.create())));
        start(listFactory, windowingService, loopService);
    }

    public <S extends Sprite<?>> void start(ListFactory listFactory, WindowingService<? extends Window<? extends Renderer<S>>> windowingService, LoopService loopService) {
        Window<? extends Renderer<S>> window = windowingService.createWindow();
        GameView<S> view = createView(window);
        Model model = createModel();
        addControllers(listFactory, window, view, model);
        loopService.start(window.getRenderer(), view, model);
    }

    private <S extends Sprite<?>> void addControllers(ListFactory listFactory, Window<? extends Renderer<S>> window, GameView<S> view, Model model) {
        DragAndDropService<TowerCardDragEvent> dragAndDropService = new DragAndDropService<>(window.getMouseObservable(), listFactory);
        PlaceTowerController placeTowerController = new PlaceTowerController(dragAndDropService, model, listFactory);
        placeTowerController.register(view.getLanesView(), view.getCardsView());
    }

    private <S extends Sprite<?>> GameView<S> createView(Window<? extends Renderer<S>> window) {
        return new GameView<>(window, ConcreteTransform.getFactory());
    }

    private List<TowerCard<?>> getCards() {
        TowerCard<Tower> mario = new TowerCard<>(new Money(60), Towers::mario);
        TowerCard<Tower> toad = new TowerCard<>(new Money(40), Towers::toad);
        return Arrays.asList(mario, toad);
    }

    private Model createModel() {
        return new Model(new WaveQueue(), new Player(new Money(100), getCards()));
    }
}
