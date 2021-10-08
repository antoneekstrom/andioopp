package andioopp.view.gui;

import andioopp.common.gfx.Color;
import andioopp.common.gfx.Renderer;
import andioopp.common.gfx.Sprite;
import andioopp.common.transform.*;
import andioopp.model.tower.Tower;
import andioopp.model.tower.Towers;
import javafx.scene.text.Font;

import java.util.function.Supplier;

public class TowerCard<S extends Sprite<?>> {

    private final static int HEIGHT = 150;
    private final static int WIDTH = 115;
    private final static int IMAGE_HEIGHT = 100;
    private final static int IMAGE_WIDTH = 70;
    private Supplier<Tower> towerSupplier;
    Tower tower;
    private static final TransformFactory transformFactory = ConcreteTransform.getFactory();


    public TowerCard(Supplier<Tower> towerSupplier) {
        this.towerSupplier = towerSupplier;
        tower = towerSupplier.get();
    }

    /**
     * Uses a renderer to render one TowerCard on the screen.
     * @param renderer is used for drawing TowerCard on screen.
     * @param towerCardPosition the position where TowerCard will be drawn.
     */

    public void renderTowerCard(Renderer<S> renderer, Vector3f towerCardPosition) {
        S towerCardSprite = tower.getSprite(renderer.getSpriteFactory());   //Gets image for Tower
        Transform towerCardScreenTransform = transformFactory.createWithPosition(new Vector3f(towerCardPosition.getX() + 15, towerCardPosition.getY())); // Gets position for Image
        renderer.drawRectangle(towerCardPosition, new Dimension(WIDTH, HEIGHT), new Color(150, 150, 150));    //Creates a background for TowerCard
        renderer.drawSprite(towerCardSprite, towerCardScreenTransform, getImageDimension());
        renderer.writeText(getTextPosition(towerCardPosition), tower.getName(), new Color(0,0,0), new Font("Comic Sans MS", 20));  //Writes name of Tower
        renderer.writeText(getCostPosition(towerCardPosition), String.valueOf(tower.getCost()), new Color(0,0,0), new Font("Comic Sans MS", 25)); //Writes cost of Tower

    }

    /**
     * Returns width of whole TowerCard
     */

    public int getWidth() {
        return WIDTH;
    }

    /**
     * Returns position for name of tower
     * @param towerCardPos where the whole TowerCard is placed.
     */

    private Vector3f getTextPosition(Vector3f towerCardPos) {
        return new Vector3f(towerCardPos.getX() + 30, towerCardPos.getY() + IMAGE_HEIGHT + 18);
    }

    public Supplier<Tower> getTowerSupplier() {
        return towerSupplier;
    }

    /**
     * Returns position for cost of Tower
     * @param towerCardPos where the whole TowerCard is placed.
     */
    private Vector3f getCostPosition(Vector3f towerCardPos){
        return new Vector3f(towerCardPos.getX() + 35, towerCardPos.getY() + IMAGE_HEIGHT + 40);

    }

    /**
     * Returns dimension for whole TowerCard
     */
    public static Vector3f getCardDimension() {
        return new Vector3f(WIDTH, HEIGHT);
    }
    /**
     * Returns dimension for image on TowerCard
     */
    public static Vector3f getImageDimension() {
        return new Vector3f(IMAGE_WIDTH, IMAGE_HEIGHT);
    }


}
