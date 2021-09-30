package andioopp.service.infrastructure.gui;

import andioopp.common.gfx.Renderer;
import andioopp.common.gfx.Sprite;
import andioopp.common.gfx.SpriteFactory;
import andioopp.common.transform.*;
import andioopp.common.transform.Dimension;
import andioopp.model.world.World;
import javafx.scene.control.TextField;

public class CoinView <S extends Sprite<?>> {

        private String sprite = "coinBox.png";
        private static final TransformFactory transformFactory = ConcreteTransform.getFactory();
        private TextField currentAmount = new TextField();


        public void renderCoinView(World world, Renderer<S> renderer, Vector3f worldSize){
            S coinViewSprite = getSprite(renderer.getSpriteFactory());
            Dimension coinViewScreenSize = getCoinViewScreenSize(world, coinViewSprite);
            Transform coinViewScreenTransform = transformFactory.createWithPosition(getSpritePosition());
            renderer.drawSprite(coinViewSprite, coinViewScreenTransform, coinViewScreenSize.toVector());
        }

        public <S extends Sprite<?>> S getSprite(SpriteFactory<S> spriteFactory) {
            return spriteFactory.get(sprite);
        }

        private Vector3f getSpritePosition(){
            float distanceFromBorderX = 50f;
            float distanceFromBorderY = 10f;
            return new Vector3f(distanceFromBorderX,distanceFromBorderY);
        }
        private Dimension getCoinViewScreenSize(World world, S coinViewSprite) {
            Dimension coinViewSpriteSize = coinViewSprite.getSize();
            return coinViewSpriteSize;
        }

        public void displayCoins(TextField currentAmount){


        }

        private int numberOfCoinsLeft(){
            int defaultAmount = 100;
            int currentAmount = defaultAmount /* - return value from a method that takes input from mouse */;

            return currentAmount;
        }


}