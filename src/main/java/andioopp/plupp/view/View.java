package andioopp.plupp.view;

import andioopp.common.graphics.Renderer;
import andioopp.common.graphics.Sprite;
import andioopp.plupp.view.util.ModelViewport;

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
     * @param viewport the viewport in which to render
     */
    <S extends Sprite<?>> void render(M model, Renderer<S> renderer, ModelViewport viewport);

}
