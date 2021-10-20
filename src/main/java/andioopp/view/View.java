package andioopp.view;

import andioopp.common.graphics.Renderer;
import andioopp.common.graphics.Sprite;

/**
 * Represents a view of the model.
 * <br>
 * A view is a function or class which uses a {@link Renderer} to display information about the model.
 * Views can be added to an instance of {@link andioopp.game.Game} in order to be rendered.
 *
 * @param <M> the type of the model
 * @author Anton Ekström, Jacob Bengtsson
 * @see andioopp.game.Game
 * @see andioopp.game.MarioGame
 * @see Renderer
 */
@FunctionalInterface
public interface View<M> {
    /**
     * Renders the model using a renderer.
     *
     * @param model    the model to render
     * @param renderer the renderer to use
     */
    <S extends Sprite<?>> void render(M model, Renderer<S> renderer);
}
