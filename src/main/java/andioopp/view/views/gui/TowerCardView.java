package andioopp.view.views.gui;

import andioopp.common.graphics.Color;
import andioopp.common.graphics.Renderer;
import andioopp.common.graphics.Sprite;
import andioopp.common.math.Dimension;
import andioopp.common.math.Vector3f;
import andioopp.common.math.transform.ConcreteTransform;
import andioopp.common.math.transform.Transform;
import andioopp.common.math.transform.TransformFactory;
import andioopp.model.Model;
import andioopp.model.domain.enemy.Enemy;
import andioopp.model.domain.player.TowerCard;
import andioopp.model.domain.tower.Tower;
import andioopp.model.util.ModelCoordinate;
import andioopp.view.View;
import andioopp.view.util.ModelViewport;
import andioopp.view.util.ViewCoordinate;
import javafx.scene.text.Font;

public class TowerCardView  {

    private final static int HEIGHT = 150;
    private final static int WIDTH = 115;
    private final static int IMAGE_HEIGHT = 100;
    private final static int IMAGE_WIDTH = 70;
    private static final TransformFactory transformFactory = ConcreteTransform.getFactory();
    private final TowerCard<?> card;
    private final Tower tower;

    public TowerCardView(TowerCard<?> card) {
        this.card = card;
        this.tower = card.getSupplier().get();
    }

    /**
     * Returns dimension for whole TowerCardView
     */
    public static Vector3f getCardDimension() {
        return new Vector3f(WIDTH, HEIGHT);
    }

    /**
     * Returns dimension for image on TowerCardView
     */
    public static Vector3f getImageDimension() {
        return new Vector3f(WIDTH,HEIGHT);
    }

    /**
     * Uses a renderer to render one TowerCardView on the screen.
     *
     * @param renderer          is used for drawing TowerCardView on screen.
     * @param towerCardPosition the position where TowerCardView will be drawn.
     */

    public <S extends Sprite<?>> void renderTowerCard(Renderer<S> renderer, Vector3f towerCardPosition, ModelViewport viewport) {
        S sprite = tower.getSprite(renderer.getSpriteFactory());
        ViewCoordinate viewPosition = viewport.getViewCoordinate(new ModelCoordinate(new Vector3f(towerCardPosition.getX() + 15, towerCardPosition.getY())));
        Dimension<ViewCoordinate> viewSize = viewport.getViewSize(new Dimension(getCardDimension()));

        renderer.drawRectangle(towerCardPosition, new Dimension(new Vector3f(WIDTH, HEIGHT)), new Color(150, 150, 150));
        renderer.drawSprite(sprite,viewPosition,viewSize);
        renderer.writeText(getTextPosition(towerCardPosition), tower.getName(), new Color(0, 0, 0), new Font("Comic Sans MS", 20));  //Writes name of Towe
        renderer.writeText(getCostPosition(towerCardPosition), String.valueOf(tower.getCost()), new Color(0, 0, 0), new Font("Comic Sans MS", 25)); //Writes cost of Tower


    }




    /**
     * Returns position for name of tower
     *
     * @param towerCardPos where the whole TowerCardView is placed.
     */

    private Vector3f getTextPosition(Vector3f towerCardPos) {
        return new Vector3f(towerCardPos.getX() + 30, towerCardPos.getY() + IMAGE_HEIGHT + 18);
    }

    public TowerCard<?> getCard() {
        return card;
    }

    /**
     * Returns position for cost of Tower
     *
     * @param towerCardPos where the whole TowerCardView is placed.
     */
    private Vector3f getCostPosition(Vector3f towerCardPos) {
        return new Vector3f(towerCardPos.getX() + 35, towerCardPos.getY() + IMAGE_HEIGHT + 40);

    }



}
