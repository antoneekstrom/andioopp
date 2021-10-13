package andioopp.model;

import andioopp.model.player.Player;
import andioopp.model.world.World;

/**
 * Facade for the entire model.
 */
public interface Model {
    Player getPlayer();
    World getWorld();
}



