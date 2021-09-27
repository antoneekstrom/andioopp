package andioopp.service.infrastructure.creation;

import andioopp.common.gfx.Renderer;
import andioopp.common.gfx.Sprite;
import andioopp.common.gfx.Window;
import andioopp.common.transform.Vector3f;
import andioopp.model.Model;
import andioopp.model.waves.WaveQueue;
import andioopp.service.infrastructure.graphics.WindowingService;
import andioopp.service.infrastructure.persistence.PersistenceService;
import andioopp.service.infrastructure.resource.ResourceService;
import andioopp.service.infrastructure.loop.LoopService;
import andioopp.view.View;

public class GameSetupService {

    private final WindowingService<?> windowingService;
    private final LoopService loopService;
    private final ResourceService resourceService;
    private final PersistenceService persistenceService;
    private final CreationService creationService;

    public GameSetupService(WindowingService<?> windowingService, LoopService loopService, ResourceService resourceService, PersistenceService persistenceService, CreationService creationService) {
        this.windowingService = windowingService;
        this.loopService = loopService;
        this.resourceService = resourceService;
        this.persistenceService = persistenceService;
        this.creationService = creationService;
    }

    public void start() {
        WindowingService<?> windowingService = getWindowingService();
        LoopService loopService = getLoopService();

        Model model = createModel();
        View<?> view = createView(windowingService.createWindow());

        loopService.start(model, view);
    }

    private Model createModel() {
        return new Model(new WaveQueue());
    }

    private <S extends Sprite<?>, R extends Renderer<S>> View<S> createView(Window<R> window) {
        float worldSizeFactorX = 0.7f;
        float worldSizeFactorY = 0.7f;

        Vector3f windowSize = new Vector3f(window.getWidth(), window.getHeight());
        Vector3f worldSize = new Vector3f(windowSize.getX() * worldSizeFactorX, windowSize.getY() * worldSizeFactorY);
        Vector3f worldPos = new Vector3f(windowSize.getX() - worldSize.getX(), (windowSize.getY() - worldSize.getY()) * 0.5f);

        return new View<>(window.getRenderer(), worldPos, worldSize);
    }

    private CreationService getCreationService() {
        return creationService;
    }

    private WindowingService<?> getWindowingService() {
        return windowingService;
    }

    private LoopService getLoopService() {
        return loopService;
    }

    private ResourceService getResourceService() {
        return resourceService;
    }

    private PersistenceService getPersistenceService() {
        return persistenceService;
    }
}
