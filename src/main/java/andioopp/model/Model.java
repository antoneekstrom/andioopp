package andioopp.model;

import andioopp.model.domain.player.Player;
import andioopp.model.domain.world.World;

/**
 * The model is relatively light on business-logic.
 * Most of the logic is expressed as systems which operate on the model.
 * Therefore, the model mostly holds data.
 *
 * @author Anton Ekström, Arvid Svedberg, Elin Nilsson, Amanda Papacosta, Jacob Bengtsson
 * @see andioopp.game.Game
 * @see System
 */
public class Model {

    private final World world;
    private final Player player;

    /**
     * Creates a model.
     *
     * @param world  the world
     * @param player the player
     */
    public Model(World world, Player player) {
        this.player = player;
        this.world = world;
    }

    /**
     * Returns the player.
     *
     * @return the player
     */
    public Player getPlayer() {
        return player;
    }

    /**
     * Returns the world.
     *
     * @return the world
     */
    public World getWorld() {
        return world;
    }
}
