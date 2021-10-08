package andioopp.main;

import andioopp.common.gfx.*;
import andioopp.common.storage.ArrayListFactory;
import andioopp.common.transform.ConcreteTransform;
import andioopp.service.infrastructure.creation.CreationService;
import andioopp.service.infrastructure.graphics.WindowingService;
import andioopp.service.infrastructure.loop.LoopService;

/**
 * Initializes the game. Completely decoupled from platform and rendering.
 */
public class Game implements GfxProgram {
    @Override
    public <S extends Sprite<?>, R extends Renderer<S>, W extends Window<R>> void run(WindowBuilder<W> windowBuilder) {
        WindowingService<W> windowingService = new WindowingService<>(windowBuilder);
        CreationService creationService = new CreationService(new ArrayListFactory(), ConcreteTransform.getFactory());
        LoopService loopService = new LoopService(creationService.createClock());

        GameSetup<W> gameSetupService = new GameSetup<>(
            windowingService,
            loopService,
            creationService
        );

        gameSetupService.start();
    }
}
