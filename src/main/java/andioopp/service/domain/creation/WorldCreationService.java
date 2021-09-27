package andioopp.service.domain.creation;

import andioopp.model.world.World;

@FunctionalInterface
public interface WorldCreationService {
    World createWorld();
}
