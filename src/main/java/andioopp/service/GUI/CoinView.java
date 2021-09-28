package andioopp.service.GUI;

import andioopp.common.gfx.Renderer;
import andioopp.common.gfx.Sprite;
import andioopp.common.gfx.SpriteFactory;
import andioopp.common.gfx.Window;
import andioopp.common.gfx.javafx.FxSprite;
import andioopp.common.transform.*;
import andioopp.common.transform.Dimension;
import andioopp.model.World;
import javafx.scene.image.Image;
import javafx.scene.shape.Rectangle;


import java.awt.*;

    public class CoinView <S extends Sprite<?>> {

        //private final Window<?> window;
        private String sprite = "goomba.png";
        private static final TransformFactory transformFactory = ConcreteTransform.getFactory();


        public void renderCoinView(World world, Renderer<S> renderer,Vector3f worldSize){
            S coinViewSprite = getSprite(renderer.getSpriteFactory());
            Dimension coinViewScreenSize = getCoinViewScreenSize(world, coinViewSprite);
            Transform coinViewScreenTransform = transformFactory.createWithPosition(getSpritePosition(worldSize));
            renderer.drawSprite(coinViewSprite, coinViewScreenTransform, coinViewScreenSize.toVector());

            //call to a method that checks number of coinleft and displays the number

            //Vector3f worldPosition = getSpritePosition(worldDimenson());
        }
        public <S extends Sprite<?>> S getSprite(SpriteFactory<S> spriteFactory) {
            return spriteFactory.get(sprite);
        }


        private Vector3f getSpritePosition(Vector3f worldSize){
            Vector3f boardSize = new Vector3f(worldSize.getX(), worldSize.getY());
            float distanceFromBorderX = 1f;
            float distanceFromBorderY = 1f;
            return new Vector3f(boardSize.getX(),boardSize.getY());
        }
        private Dimension getCoinViewScreenSize(World world, S coinViewSprite) {
            Dimension coinViewSpriteSize = coinViewSprite.getSize();
            return coinViewSpriteSize;
        }

        private int numberOfCoinsLeft(){
            int defaultAmount = 250;
            int currentAmount = defaultAmount;

            return currentAmount;
        }
        /*
        private Vector3f getWorldDimenson(Vector3f windowsize){
             Vector3f worldSize = new Vector3f(windowsize.getX(), windowsize.getY());

            return worldSize;
        }

         */

    }











/*




        private void renderCell(World world, int row, int col) {
            Vector3f cellScreenPosition = getCellScreenPosition(world, row, col);
            Vector3f cellScreenSize = getCellScreenSize(world);
            getRenderer().drawRectangle(cellScreenPosition, cellScreenSize, getCellColor(row, col));
        }
 */
