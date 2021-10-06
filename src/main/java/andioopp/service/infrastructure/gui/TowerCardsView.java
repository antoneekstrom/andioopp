package andioopp.service.infrastructure.gui;

import andioopp.common.gfx.Renderer;
import andioopp.common.gfx.Sprite;
import andioopp.common.transform.*;
import andioopp.model.tower.Tower;
import andioopp.model.tower.towers.Mario;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

public class TowerCardsView<S extends Sprite<?>> {

    private final List<TowerCard<S>> cards = new LinkedList<>();

    public void renderTowerCardsList(Renderer<S> renderer, Vector3f towerCardListPosition) {
        for (TowerCard<S> t : cards) {
            t.renderTowerCard(renderer, towerCardListPosition);
            // den här raden gjorde inget??
            // towerCardListPosition = new Vector3f(towerCardListPosition.getX() + t.getWidth() + 10, towerCardListPosition.getY());
        }
    }

    public Rectangle getTowerCardRectangle(Vector3f towerCardListPosition) {
        return new Rectangle(towerCardListPosition, new Dimension(TowerCard.getCardDimension()));
    }

    public void createTowerCardsList() {
        addTowerCardToList(new Mario());
        addTowerCardToList(new Mario());
        addTowerCardToList(new Mario());
        addTowerCardToList(new Mario());
        addTowerCardToList(new Mario());
        addTowerCardToList(new Mario());
    }

    public void addTowerCardToList(Tower tower) {
        this.cards.add(new TowerCard<>(tower));
    }

    public Collection<TowerCard<S>> getCards() {
        return cards;
    }
}
