package andioopp.view.gui;

import andioopp.common.gfx.Color;
import andioopp.common.gfx.Renderer;
import andioopp.common.gfx.Sprite;
import andioopp.common.gfx.SpriteFactory;
import andioopp.common.transform.*;
import andioopp.model.tower.Tower;
import javafx.scene.image.ImageView;
import javafx.scene.text.Font;

public class TowerCard<S extends Sprite<?>> {

    private final static int HEIGHT = 150;
    private final static int WIDTH = 115;
    private final static int IMAGE_HEIGHT = 100;
    private final static int IMAGE_WIDTH = 70;
    private Tower tower;
    private static final TransformFactory transformFactory = ConcreteTransform.getFactory();


    public TowerCard(Tower tower) {
        this.tower = tower;
    }

    // Uses a renderer to render one TowerCard on the screen.
    public void renderTowerCard(Renderer<S> renderer, Vector3f towerCardPosition) {
        S towerCardSprite = tower.getSprite(renderer.getSpriteFactory());   //Gets image for Tower
        Transform towerCardScreenTransform = transformFactory.createWithPosition(new Vector3f(towerCardPosition.getX() + 15, towerCardPosition.getY())); // Gets position for Image
        renderer.drawRectangle(towerCardPosition, getCardDimension(), new Color(150, 150, 150));    //Creates a background for TowerCard
        renderer.drawSprite(towerCardSprite, towerCardScreenTransform, getImageDimension());
        renderer.writeText(getTextPosition(towerCardPosition), tower.getName(), new Color(0,0,0), new Font("Comic Sans MS", 20));  //Writes name of Tower
        renderer.writeText(getCostPosition(towerCardPosition), String.valueOf(tower.getCost()), new Color(0,0,0), new Font("Comic Sans MS", 25)); //Writes cost of Tower

    }
    //returns width of whole TowerCard
    public int getWidth() {
        return WIDTH;
    }

    // returns position for name of tower
    private Vector3f getTextPosition(Vector3f towerCardPos) {
        return new Vector3f(towerCardPos.getX() + 30, towerCardPos.getY() + IMAGE_HEIGHT + 18);
    }

    public Tower getTower() {
        return tower;
    }
    //returns position for cost of Tower
    private Vector3f getCostPosition(Vector3f towerCardPos){
        return new Vector3f(towerCardPos.getX() + 35, towerCardPos.getY() + IMAGE_HEIGHT + 40);

    }
    //returns dimension for whole TowerCard

    public static Vector3f getCardDimension() {
        return new Vector3f(WIDTH, HEIGHT);
    }

    //returns dimension for image on TowerCard

    public static Vector3f getImageDimension() {
        return new Vector3f(IMAGE_WIDTH, IMAGE_HEIGHT);
    }


}
