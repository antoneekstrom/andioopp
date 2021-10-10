package andioopp.main;

import andioopp.common.graphics.*;
import andioopp.common.observer.ObservableWithList;
import andioopp.common.storage.ArrayListFactory;
import andioopp.common.storage.ListFactory;
import andioopp.common.time.FxClock;
import andioopp.common.transform.*;
import andioopp.control.PlaceTowerController;
import andioopp.control.TowerDragEvent;
import andioopp.model.Model;
import andioopp.model.tower.Tower;
import andioopp.model.tower.Towers;
import andioopp.model.waves.WaveQueue;
import andioopp.service.infrastructure.graphics.WindowingService;
import andioopp.service.infrastructure.input.DragAndDropService;
import andioopp.service.infrastructure.loop.LoopService;
import andioopp.view.*;

import java.util.Arrays;
import java.util.List;
import java.util.function.Supplier;

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
        DragAndDropService<TowerDragEvent> dragAndDropService = new DragAndDropService<>(window.getMouseObservable(), listFactory);
        PlaceTowerController placeTowerController = new PlaceTowerController(dragAndDropService, model, listFactory);
        placeTowerController.register(view.getLanesView(), view.getCardsView());
    }

    private <S extends Sprite<?>> GameView<S> createView(Window<? extends Renderer<S>> window) {
        TransformFactory transformFactory = ConcreteTransform.getFactory();

        Vector3f worldSizeFactor = new Vector3f(0.7f, 0.7f);
        Dimension windowSize = new Dimension(window.getWidth(), window.getHeight());

        Dimension worldSize = new Dimension(windowSize.toVector().scale(worldSizeFactor));
        Vector3f worldPos = new Vector3f(windowSize.getWidth() - (worldSize.getWidth() * 1.01f), windowSize.getHeight() - (worldSize.getHeight() * 1.10f));
        Rectangle viewportRect = new Rectangle(worldPos, worldSize);

        return new GameView<>(viewportRect, transformFactory);
    }

    private List<Supplier<Tower>> getCards() {
        return Arrays.asList(Towers::mario, Towers::toad);
    }

    private Model createModel() {
        return new Model(new WaveQueue(), getCards());
    }
}
