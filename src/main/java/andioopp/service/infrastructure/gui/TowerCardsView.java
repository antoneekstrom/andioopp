package andioopp.service.infrastructure.gui;

import andioopp.common.gfx.Renderer;
import andioopp.common.gfx.Sprite;
import andioopp.common.transform.*;
import andioopp.model.tower.Tower;
import andioopp.model.tower.towers.*;

import java.util.ArrayList;
import java.util.List;

public class TowerCardsView <S extends Sprite<?>>{

 private List<TowerCard> towerCardList = new ArrayList<>();
 private static final TransformFactory transformFactory = ConcreteTransform.getFactory();


 public TowerCardsView() {
     addCardstoTowerCardList();

 }

 public void addTowerCardToList(Tower tower){
     this.towerCardList.add(new TowerCard(tower));
 }

 public void renderTowerCardsList(Renderer renderer, Vector3f towerCardPosition){

     for (TowerCard towerCard : towerCardList) {
         towerCard.renderTowerCard(renderer, towerCardPosition);
         towerCardPosition = new Vector3f(towerCardPosition.getX() + towerCard.getWidth() + 12, towerCardPosition.getY());
     }

 }
 public void addCardstoTowerCardList(){
     towerCardList.add(new TowerCard(new Mario()));
     towerCardList.add(new TowerCard(new Mario()));
     towerCardList.add(new TowerCard(new Mario()));
     towerCardList.add(new TowerCard(new Mario()));
     towerCardList.add(new TowerCard(new Mario()));
     towerCardList.add(new TowerCard(new Mario()));
     towerCardList.add(new TowerCard(new Mario()));
     /*towerCardList.add(new TowerCard(new Toad()));
     towerCardList.add(new TowerCard(new Luigi)));
     towerCardList.add(new TowerCard(new Yoshi)));
     towerCardList.add(new TowerCard(new Rosalina)));
     towerCardList.add(new TowerCard(new Bobomb)));*/



 }

}
