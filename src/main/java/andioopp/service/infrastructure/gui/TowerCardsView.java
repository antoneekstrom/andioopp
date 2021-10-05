package andioopp.service.infrastructure.gui;

import andioopp.common.gfx.Renderer;
import andioopp.common.gfx.Sprite;
import andioopp.common.transform.*;
import andioopp.model.tower.Tower;
import andioopp.model.tower.towers.Mario;
import andioopp.model.world.World;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class TowerCardsView <S extends Sprite<?>>{

 private List<TowerCard> towerCardList = new LinkedList<>();
 private static final TransformFactory transformFactory = ConcreteTransform.getFactory();


 public TowerCardsView() {
 }
 public void renderTowerCardsList(Renderer<S> renderer, Vector3f towerCardListPosition){

     for (TowerCard<S> t : towerCardList){
         t.renderTowerCard(renderer, new Vector3f(towerCardListPosition.getX(), towerCardListPosition.getY()));
         towerCardListPosition = new Vector3f(towerCardListPosition.getX() + t.getWidth() + 10, towerCardListPosition.getY());
     }
 }
 public void createTowerCardsList(){
     addTowerCardToList(new Mario());
     addTowerCardToList(new Mario());
     addTowerCardToList(new Mario());
     addTowerCardToList(new Mario());
     addTowerCardToList(new Mario());
     addTowerCardToList(new Mario());

 }


 public void addTowerCardToList(Tower tower){
     this.towerCardList.add(new TowerCard(tower));
 }


}
