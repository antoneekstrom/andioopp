package andioopp.view.gui;

import andioopp.common.graphics.Renderer;
import andioopp.common.graphics.Sprite;
import andioopp.common.transform.Dimension;
import andioopp.common.transform.Rectangle;
import andioopp.common.transform.Vector3f;
import andioopp.model.Model;
import andioopp.model.tower.Tower;
import andioopp.view.View;

import java.util.List;
import java.util.function.Supplier;

public class CardsView<S extends Sprite<?>> implements View<S> {

    private final static Vector3f CARD_OFFSET = new Vector3f(20);

    private final Rectangle viewport;

    public CardsView(Rectangle viewportRect) {
        this.viewport = viewportRect;
    }

    @Override
    public void render(Renderer<S> renderer, Model model) {
        List<Supplier<Tower>> cards = model.getCards();
        for (int i = 0; i < cards.size(); i++) {
            Vector3f cardPosition = getTowerCardRect(i).getPosition();
            new TowerCard<S>(cards.get(i)).renderTowerCard(renderer, cardPosition);
        }
    }

    public Rectangle getTowerCardRect(int cardIndex) {
        return getTowerCardRect(getViewportPosition().sub(TowerCard.getCardDimension().onlyY()), cardIndex);
    }

    public Rectangle getTowerCardRect(Vector3f position, int cardIndex) {
        Vector3f cardOffset = TowerCard.getCardDimension().onlyX().add(CARD_OFFSET);
        return new Rectangle(position.add(cardOffset.scale(cardIndex)), new Dimension(TowerCard.getCardDimension()));
    }

    private Vector3f getViewportPosition() {
        return getViewport().getPosition();
    }

    private Rectangle getViewport() {
        return viewport;
    }
}
