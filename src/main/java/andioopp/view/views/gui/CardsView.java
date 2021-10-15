package andioopp.view.views.gui;

import andioopp.common.graphics.Renderer;
import andioopp.common.graphics.Sprite;
import andioopp.common.math.Dimension;
import andioopp.common.math.Vector3f;
import andioopp.common.math.rectangle.ImmutableRectangle;
import andioopp.common.math.rectangle.RectanglePlupp;
import andioopp.model.Model;
import andioopp.model.domain.enemy.Enemy;
import andioopp.model.domain.player.TowerCard;
import andioopp.view.View;
import andioopp.view.util.ModelViewport;
import andioopp.view.util.ViewCoordinate;

import java.util.List;

public class CardsView implements View<Model> {

    private final static Vector3f CARD_OFFSET = new Vector3f(20);

    private final RectanglePlupp viewport;

    public CardsView(RectanglePlupp viewportRect) {
        this.viewport = viewportRect;
    }


    public RectanglePlupp getTowerCardRect(int cardIndex) {
        return getTowerCardRect(getViewportPosition().sub(TowerCardView.getCardDimension().fromY()), cardIndex);
    }

    public RectanglePlupp getTowerCardRect(Vector3f position, int cardIndex) {
        Vector3f cardOffset = TowerCardView.getCardDimension().fromX().add(CARD_OFFSET);
        return new ImmutableRectangle(position.add(cardOffset.scale(cardIndex)), new Dimension(TowerCardView.getCardDimension()));
    }

    private Vector3f getViewportPosition() {
        return getViewport().getPosition();
    }

    private RectanglePlupp getViewport() {
        return viewport;
    }


    @Override
    public <S extends Sprite<?>> void render(Model model, Renderer<S> renderer, ModelViewport viewport) {
        List<TowerCard<?>> cards = model.getPlayer().getCards();
        for (int i = 0; i < cards.size(); i++) {
            Vector3f cardPosition = getTowerCardRect(i).getPosition();
            new TowerCardView(cards.get(i)).renderTowerCard(renderer, cardPosition, viewport);
        }
    }


}
