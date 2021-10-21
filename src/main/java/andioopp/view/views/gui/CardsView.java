package andioopp.view.views.gui;

import andioopp.common.graphics.Renderer;
import andioopp.common.graphics.Sprite;
import andioopp.common.math.dimension.Dimension;
import andioopp.common.math.vector.Vector3f;
import andioopp.model.Model;
import andioopp.model.domain.player.TowerCard;
import andioopp.view.View;
import andioopp.view.util.Viewport;

import java.util.List;

/**
 * A View for displaying all TowerCards.
 */

public class CardsView implements View<Model> {

    private final static float CARD_OFFSET = 20;

    private final Vector3f position;

    public CardsView(Vector3f position) {
        this.position = position;
    }

    @Override
    public <S extends Sprite<?>> void render(Model model, Renderer<S> renderer) {
        List<TowerCard<?>> cards = model.getPlayer().getCards();
        for (int i = 0; i < cards.size(); i++) {
            TowerCard<?> card = cards.get(i);
            TowerCardView cardView = getCardView(card, i);
            cardView.render(model, renderer);
        }
    }

    /**
     * Returns a TowerCardView using a ViewPort.
     */
    public TowerCardView getCardView(TowerCard<?> card, int i) {
        Viewport cardViewport = getCardViewport(i);
        return new TowerCardView(cardViewport, card);
    }

    private Viewport getCardViewport(int i) {
        return new Viewport(Dimension.UNIT, TowerCardView.CARD_SIZE, getCardPosition(i));
    }

    private Vector3f getCardPosition(int i) {
        return position.add(Vector3f.fromX((TowerCardView.CARD_SIZE.getWidth() + CARD_OFFSET) * i));
    }
}
