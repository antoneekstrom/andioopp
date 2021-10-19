package andioopp.view;

import andioopp.common.graphics.Renderer;
import andioopp.common.graphics.Sprite;

/**
 * Represents a view of the model.
 *
 * @param <M> the type of the model
 * @author Anton Ekström, Jacob Bengtsson
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
