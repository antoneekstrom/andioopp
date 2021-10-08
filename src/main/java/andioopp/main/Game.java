package andioopp.main;

import andioopp.common.gfx.*;
import andioopp.common.gfx.Renderer;
import andioopp.common.observer.ObservableWithList;
import andioopp.common.storage.ArrayListFactory;
import andioopp.common.storage.ListFactory;
import andioopp.common.time.FxClock;
import andioopp.common.transform.*;
import andioopp.control.PlaceTowerController;
import andioopp.control.TowerDragEvent;
import andioopp.model.Model;
import andioopp.model.waves.WaveQueue;
import andioopp.service.infrastructure.graphics.WindowingService;
import andioopp.service.infrastructure.input.DragAndDropService;
import andioopp.service.infrastructure.loop.LoopService;
import andioopp.view.View;

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

    public <W extends Window<?>> void start(ListFactory listFactory, WindowingService<W> windowingService, LoopService loopService) {
        W window = windowingService.createWindow();
        View<?> view = createView(window);
        Model model = createModel();

        DragAndDropService<TowerDragEvent> dragAndDropService = new DragAndDropService<>(window.getMouseObservable(), listFactory);
        PlaceTowerController placeTowerController = new PlaceTowerController(dragAndDropService, model, view, listFactory);
        placeTowerController.register();
        loopService.start(model, view);
    }

    private <W extends Window<?>> View<?> createView(W window) {
        float worldSizeFactorX = 0.7f;
        float worldSizeFactorY = 0.7f;

        TransformFactory transformFactory = ConcreteTransform.getFactory();
        Renderer<?> renderer = window.getRenderer();

        Vector3f windowSize = new Vector3f(window.getWidth(), window.getHeight());
        Dimension worldSize = new Dimension(windowSize.getX() * worldSizeFactorX, windowSize.getY() * worldSizeFactorY);
        Vector3f worldPos = new Vector3f(windowSize.getX() - (worldSize.getWidth() * 1.01f), windowSize.getY() - (worldSize.getHeight() * 1.10f));
        Rectangle viewport = new Rectangle(worldPos, worldSize);
        return new View<>(renderer, transformFactory, viewport);
    }

    private Model createModel() {
        return new Model(new WaveQueue());
    }
}
