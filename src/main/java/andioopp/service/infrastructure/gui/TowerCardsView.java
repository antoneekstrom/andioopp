package andioopp.service.infrastructure.gui;

import andioopp.common.gfx.Renderer;
import andioopp.common.gfx.Sprite;
import andioopp.common.transform.*;
import andioopp.model.tower.Tower;
import andioopp.model.tower.towers.Mario;
import andioopp.model.world.World;
import andioopp.view.gui.TowerCard;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class TowerCardsView<S extends Sprite<?>> {

    private List<TowerCard> towerCardList = new LinkedList<>();

    public TowerCardsView() {
    }
    //Uses a renderer to draw all TowerCards of towerCardList on the screen.
    public void renderTowerCardsList(Renderer renderer, Vector3f towerCardListPosition) {
        for (TowerCard t : towerCardList) {
            t.renderTowerCard(renderer, new Vector3f(towerCardListPosition.getX(), towerCardListPosition.getY()));
            towerCardListPosition = new Vector3f(towerCardListPosition.getX() + t.getWidth() + 10, towerCardListPosition.getY()); //Updates position for next TowerCard
        }
    }
    //Should add a TowerCard of all different towers to TowerCardList
    public void createTowerCardsList() {
        addTowerCardToList(new Mario());
        addTowerCardToList(new Mario());
        addTowerCardToList(new Mario());
        addTowerCardToList(new Mario());
        addTowerCardToList(new Mario());
        addTowerCardToList(new Mario());

    }


    private void addTowerCardToList(Tower tower) {
        this.towerCardList.add(new TowerCard(tower));
    }


}
