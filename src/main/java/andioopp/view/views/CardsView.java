package andioopp.view.views;

import andioopp.common.graphics.Renderer;
import andioopp.common.graphics.Sprite;
import andioopp.common.math.Dimension;
import andioopp.common.math.rectangle.ImmutableRectangle;
import andioopp.common.math.rectangle.Rectangle;
import andioopp.common.math.Vector3f;
import andioopp.model.Model;
import andioopp.model.player.TowerCard;
import andioopp.view.View;

import java.util.List;

public class CardsView<S extends Sprite<?>> implements View<S> {

    private final static Vector3f CARD_OFFSET = new Vector3f(20);

    private final Rectangle viewport;

    public CardsView(Rectangle viewportRect) {
        this.viewport = viewportRect;
    }

    @Override
    public void render(Renderer<S> renderer, Model model) {
        List<TowerCard<?>> cards = model.getPlayer().getCards();
        for (int i = 0; i < cards.size(); i++) {
            Vector3f cardPosition = getTowerCardRect(i).getPosition();
            new TowerCardView<S>(cards.get(i)).renderTowerCard(renderer, cardPosition);
        }
    }

    public Rectangle getTowerCardRect(int cardIndex) {
        return getTowerCardRect(getViewportPosition().sub(TowerCardView.getCardDimension().fromY()), cardIndex);
    }

    public Rectangle getTowerCardRect(Vector3f position, int cardIndex) {
        Vector3f cardOffset = TowerCardView.getCardDimension().fromX().add(CARD_OFFSET);
        return new ImmutableRectangle(position.add(cardOffset.scale(cardIndex)), new Dimension(TowerCardView.getCardDimension()));
    }

    private Vector3f getViewportPosition() {
        return getViewport().getPosition();
    }

    private Rectangle getViewport() {
        return viewport;
    }
}
