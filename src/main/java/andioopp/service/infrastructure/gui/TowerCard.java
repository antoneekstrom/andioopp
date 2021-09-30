package andioopp.service.infrastructure.gui;

import andioopp.common.gfx.Color;
import andioopp.common.gfx.Renderer;
import andioopp.common.gfx.Sprite;
import andioopp.common.gfx.SpriteFactory;
import andioopp.common.transform.*;
import andioopp.model.tower.Tower;
import andioopp.model.world.World;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

import java.io.IOException;

public class TowerCard <S extends Sprite<?>> {
    ImageView imageView;
    Label prizeLabel;
    Label nameLabel;
    private static final int HEIGHT = 150;
    private static final int WIDTH = 120;
    private static final int IMAGE_HEIGHT = 115;
    private static final int IMAGE_WIDTH = 90;
    private Tower tower;
    private SpriteFactory<?> spriteFactory;
    private static final TransformFactory transformFactory = ConcreteTransform.getFactory();


    public TowerCard(Tower tower) {

        this.tower = tower;

        //nameLabel.setText(tower.getName());
        //prizeLabel.setText(String.valueOf(tower.getCost()));
        //imageView.setImage(new Image(tower.getSprite()));



    }
    public void renderTowerCard(Renderer<S> renderer, Vector3f towerCardPosition){
        S towerCardSprite = tower.getSprite(renderer.getSpriteFactory());
        Transform towerCardScreenTransform = transformFactory.createWithPosition(new Vector3f(towerCardPosition.getX() + 15, towerCardPosition.getY()));
        renderer.drawRectangle(towerCardPosition, getCardDimension(), new Color(150, 150, 150));
        renderer.drawSprite(towerCardSprite,towerCardScreenTransform ,getImageDimension());
    }
    public Vector3f getCardDimension(){
        return new Vector3f(WIDTH, HEIGHT);
    }
    public Vector3f getImageDimension(){
        return new Vector3f(IMAGE_WIDTH,IMAGE_HEIGHT);
    }


}
