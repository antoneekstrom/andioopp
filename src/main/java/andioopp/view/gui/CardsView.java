package andioopp.view.gui;

import andioopp.common.gfx.Renderer;
import andioopp.common.gfx.Sprite;
import andioopp.common.transform.*;
import andioopp.model.tower.Tower;

import java.util.List;
import java.util.function.Supplier;

public class CardsView<S extends Sprite<?>> {

    private final static Vector3f CARD_OFFSET = new Vector3f(20);

    private final Renderer<S> renderer;
    private final List<TowerCard<S>> cards;
    private final Rectangle viewport;

    public CardsView(Renderer<S> renderer, List<TowerCard<S>> cards, Rectangle viewport) {
        this.renderer = renderer;
        this.cards = cards;
        this.viewport = viewport;
    }

    /**
     * Uses a renderer to draw all TowerCards of towerCardList on the screen.
     */
    public void render() {
        for (int i = 0; i < cards.size(); i++) {
            Vector3f cardPosition = getTowerCardRectangle(i).getPosition();
            cards.get(i).renderTowerCard(renderer, cardPosition);
        }
    }

    public List<TowerCard<S>> getCards() {
        return cards;
    }

    public Rectangle getTowerCardRectangle(int cardIndex) {
        return getTowerCardRectangle(getViewportPosition(), cardIndex);
    }

    public Rectangle getTowerCardRectangle(Vector3f position, int cardIndex) {
        Vector3f cardOffset = TowerCard.getCardDimension().onlyX().add(CARD_OFFSET);
        return new Rectangle(position.add(cardOffset.scale(cardIndex)), new Dimension(TowerCard.getCardDimension()));
    }

    private Vector3f getViewportPosition() {
        return getViewport().getPosition();
    }

    private Dimension getViewportSize() {
        return getViewport().getSize();
    }

    private Rectangle getViewport() {
        return viewport;
    }
}
