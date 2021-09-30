package andioopp.service.infrastructure.creation;

import andioopp.common.gfx.Window;
import andioopp.common.transform.Vector3f;
import andioopp.model.Model;
import andioopp.model.waves.WaveQueue;
import andioopp.service.infrastructure.graphics.WindowingService;
import andioopp.service.infrastructure.loop.LoopService;
import andioopp.service.infrastructure.persistence.PersistenceService;
import andioopp.service.infrastructure.resource.ResourceService;
import andioopp.view.View;

public class GameSetupService<W extends Window<?>> {

    private final WindowingService<W> windowingService;
    private final LoopService loopService;
    private final ResourceService resourceService;
    private final PersistenceService persistenceService;
    private final CreationService creationService;

    public GameSetupService(WindowingService<W> windowingService, LoopService loopService, ResourceService resourceService, PersistenceService persistenceService, CreationService creationService) {
        this.windowingService = windowingService;
        this.loopService = loopService;
        this.resourceService = resourceService;
        this.persistenceService = persistenceService;
        this.creationService = creationService;
    }

    public void start() {
        WindowingService<W> windowingService = getWindowingService();
        LoopService loopService = getLoopService();

        Model model = createModel();
        loopService.start(model, createView(windowingService.createWindow()));
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

        window.getMouseObservable().addObserver((e) -> {

        });

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

    private ResourceService getResourceService() {
        return resourceService;
    }

    private PersistenceService getPersistenceService() {
        return persistenceService;
    }
}
