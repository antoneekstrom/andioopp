package andioopp.view.gui;

import andioopp.common.gfx.Renderer;
import andioopp.common.gfx.Sprite;
import andioopp.common.transform.*;
import andioopp.model.tower.Tower;
import andioopp.model.tower.towers.*;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

public class TowerCardsView<S extends Sprite<?>> {

    private final List<TowerCard<S>> cards = new LinkedList<>();

    /**
     * Uses a renderer to draw all TowerCards of towerCardList on the screen.
     * @param renderer is used for drawing towerCardList on the screen.
     * @param towerCardListPosition where the towerCard will be drawn.
     */

    public void renderTowerCardsList(Renderer<S> renderer, Vector3f towerCardListPosition) {
        for (TowerCard<S> t : cards) {
            t.renderTowerCard(renderer, towerCardListPosition);

            towerCardListPosition = new Vector3f(towerCardListPosition.getX() + t.getWidth() + 10, towerCardListPosition.getY());
        }
    }

    public Rectangle getTowerCardRectangle(Vector3f towerCardListPosition) {
        return new Rectangle(towerCardListPosition, new Dimension(TowerCard.getCardDimension()));
    }

    /**
     * Adds a TowerCard of all different towers to TowerCardList.
     */
    public void createTowerCardsList() {
        addTowerCardToList(new Mario());
        addTowerCardToList(new Toad());
        addTowerCardToList(new Yoshi());
        addTowerCardToList(new Luigi());
        addTowerCardToList(new Rosalina());
        addTowerCardToList(new Bobomb());
    }

    /**
     * Adds a TowerCard to TowerCardList
     * @param tower the tower that will be added
     */
    public void addTowerCardToList(Tower tower) {
        this.cards.add(new TowerCard<>(tower));
    }

    public Collection<TowerCard<S>> getCards() {
        return cards;
    }
}
