package andioopp.view;

import andioopp.common.gfx.Renderer;
import andioopp.common.gfx.Sprite;
import andioopp.model.Model;

/**
 * @author Anton Ekström
 * @param <S>
 */
@FunctionalInterface
public interface View<S extends Sprite<?>> {
    /**
     * Renders the model.
     * @param model the model to render
     */
    void render(Renderer<S> renderer, Model model);
}
