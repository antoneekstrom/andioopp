package andioopp.main;

import andioopp.common.gfx.Window;
import andioopp.common.transform.Vector3f;
import andioopp.control.PlaceTowerController;
import andioopp.control.TowerDragEvent;
import andioopp.model.Model;
import andioopp.model.waves.WaveQueue;
import andioopp.service.infrastructure.creation.CreationService;
import andioopp.service.infrastructure.graphics.WindowingService;
import andioopp.service.infrastructure.input.DragAndDropService;
import andioopp.service.infrastructure.loop.LoopService;
import andioopp.view.View;

public class GameSetup<W extends Window<?>> {

    private final WindowingService<W> windowingService;
    private final LoopService loopService;
    private final CreationService creationService;

    public GameSetup(WindowingService<W> windowingService, LoopService loopService, CreationService creationService) {
        this.windowingService = windowingService;
        this.loopService = loopService;
        this.creationService = creationService;
    }

    public void start() {
        WindowingService<W> windowingService = getWindowingService();
        LoopService loopService = getLoopService();

        W window = windowingService.createWindow();
        View<?> view = createView(window);
        Model model = createModel();
        DragAndDropService<TowerDragEvent> dragAndDropService = new DragAndDropService<>(window.getMouseObservable(), getCreationService().getListFactory());
        PlaceTowerController placeTowerController = new PlaceTowerController(dragAndDropService, model, view, getCreationService().getListFactory());
        placeTowerController.register();
        loopService.start(model, view);
    }

    private Model createModel() {
        return new Model(new WaveQueue());
    }

    private View<?> createView(W window) {
        float worldSizeFactorX = 0.7f;
        float worldSizeFactorY = 0.7f;

        Vector3f windowSize = new Vector3f(window.getWidth(), window.getHeight());
        Vector3f worldSize = new Vector3f(windowSize.getX() * worldSizeFactorX, windowSize.getY() * worldSizeFactorY);
        Vector3f worldPos = new Vector3f(windowSize.getX() - (worldSize.getX()*1.01f), windowSize.getY()-(worldSize.getY()*1.10f));

        return new View<>(window.getRenderer(), worldPos, worldSize);
    }

    private CreationService getCreationService() {
        return creationService;
    }

    private WindowingService<W> getWindowingService() {
        return windowingService;
    }

    private LoopService getLoopService() {
        return loopService;
    }
}
