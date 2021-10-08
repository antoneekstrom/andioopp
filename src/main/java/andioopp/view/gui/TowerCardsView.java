package andioopp.view.gui;

import andioopp.common.gfx.Renderer;
import andioopp.common.gfx.Sprite;
import andioopp.common.transform.*;
import andioopp.model.tower.Tower;
import andioopp.model.tower.Towers;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.function.Supplier;

public class TowerCardsView<S extends Sprite<?>> {

    private final List<TowerCard<S>> cards = new LinkedList<>();
    private final Vector3f position;

    private final static Vector3f CARD_OFFSET = new Vector3f(20);

    public TowerCardsView(Vector3f screenPosition) {
        this.position = screenPosition;
    }

    /**
     * Uses a renderer to draw all TowerCards of towerCardList on the screen.
     * @param renderer is used for drawing towerCardList on the screen.
     * @param towerCardListPosition where the towerCard will be drawn.
     */
    public void renderTowerCardsList(Renderer<S> renderer) {
        for (int i = 0; i < cards.size(); i++) {
            Vector3f cardPosition = getTowerCardRectangle(position, i).getPosition();
            cards.get(i).renderTowerCard(renderer, cardPosition);
        }
    }

    public Rectangle getTowerCardRectangle(int cardIndex) {
        return getTowerCardRectangle(position, cardIndex);
    }

    public Rectangle getTowerCardRectangle(Vector3f position, int cardIndex) {
        Vector3f cardOffset = TowerCard.getCardDimension().onlyX().add(CARD_OFFSET);
        return new Rectangle(position.add(cardOffset.scale(cardIndex)), new Dimension(TowerCard.getCardDimension()));
    }

    /**
     * Adds a TowerCard of all different towers to TowerCardList.
     */
    public void createTowerCardsList() {
        addTowerCardToList(Towers::mario);
        addTowerCardToList(Towers::toad);
        addTowerCardToList(Towers::luigi);
        addTowerCardToList(Towers::yoshi);
        addTowerCardToList(Towers::rosalina);
        addTowerCardToList(Towers::bobomb);
    }

    /**
     * Adds a TowerCard to TowerCardList
     * @param tower the tower that will be added
     */
    public void addTowerCardToList(Supplier<Tower> towerSupplier) {
        this.cards.add(new TowerCard<>(towerSupplier));
    }

    public List<TowerCard<S>> getCards() {
        return cards;
    }
}
