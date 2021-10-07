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
    ImageView imageView;
    private static final int HEIGHT = 150;
    private static final int WIDTH = 115;
    private static final int IMAGE_HEIGHT = 115;
    private static final int IMAGE_WIDTH = 85;
    private Tower tower;
    private SpriteFactory<?> spriteFactory;
    private static final TransformFactory transformFactory = ConcreteTransform.getFactory();


    public TowerCard(Tower tower) {
        this.tower = tower;
    }

    public int getWidth() {
        return WIDTH;
    }

    public void renderTowerCard(Renderer<S> renderer, Vector3f towerCardPosition) {
        S towerCardSprite = tower.getSprite(renderer.getSpriteFactory());
        Transform towerCardScreenTransform = transformFactory.createWithPosition(new Vector3f(towerCardPosition.getX() + 15, towerCardPosition.getY()));
        renderer.drawRectangle(towerCardPosition, getCardDimension(), new Color(150, 150, 150));
        renderer.drawSprite(towerCardSprite, towerCardScreenTransform, getImageDimension());
        renderer.writeText(getTextPosition(towerCardPosition), tower.getName(), new Color(0,0,0), new Font("Comic Sans MS", 25));
    }

    public Vector3f getTextPosition(Vector3f towerCardPos){
        return new Vector3f(towerCardPos.getX() + 20, towerCardPos.getY() + IMAGE_HEIGHT + 15);
    }

    public Tower getTower() {
        return tower;
    }

    public static Vector3f getCardDimension() {
        return new Vector3f(WIDTH, HEIGHT);
    }

    public static Vector3f getImageDimension() {
        return new Vector3f(IMAGE_WIDTH, IMAGE_HEIGHT);
    }


}
