package andioopp.service.infrastructure.gui;

import andioopp.common.gfx.Renderer;
import andioopp.common.gfx.Sprite;
import andioopp.common.transform.*;
import andioopp.model.tower.Tower;
import andioopp.model.world.World;

import java.util.ArrayList;
import java.util.List;

public class TowerCardsView <S extends Sprite<?>>{

 private List<TowerCard> towerCardList;
 private static final TransformFactory transformFactory = ConcreteTransform.getFactory();


 public TowerCardsView(List<TowerCard> towerCardList) {
     this.towerCardList = towerCardList;
 }

 public void addTowerCardToList(Tower tower){
     this.towerCardList.add(new TowerCard(tower));
 }


}
