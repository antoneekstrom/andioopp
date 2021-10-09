package andioopp.view.gui;

import andioopp.common.gfx.Color;
import andioopp.common.gfx.Renderer;
import andioopp.common.gfx.Sprite;
import andioopp.common.gfx.SpriteFactory;
import andioopp.common.transform.*;
import andioopp.common.transform.Dimension;
import andioopp.model.Model;
import andioopp.model.world.World;
import andioopp.view.View;
import javafx.scene.text.Font;

public class CoinView<S extends Sprite<?>> implements View<S> {

    private static final String sprite = "coinBox.png";

    private final TransformFactory transformFactory;

    public CoinView(TransformFactory transformFactory) {
        this.transformFactory = transformFactory;
    }

    @Override
    public void render(Renderer<S> renderer, Model model) {
        World world = model.getWorld();
        S coinViewSprite = getSprite(renderer.getSpriteFactory());
        Dimension coinViewScreenSize = getCoinViewScreenSize(coinViewSprite);
        Transform coinViewScreenTransform = transformFactory.createWithPosition(getSpritePosition());
        renderer.drawSprite(coinViewSprite, coinViewScreenTransform, coinViewScreenSize);
        displayCoins(renderer, world);
    }

    private S getSprite(SpriteFactory<S> spriteFactory) {
        return spriteFactory.get(sprite);
    }

    private Vector3f getSpritePosition() {
        float distanceFromBorderX = 50f;
        float distanceFromBorderY = 10f;
        return new Vector3f(distanceFromBorderX, distanceFromBorderY);
    }

    private Dimension getCoinViewScreenSize(S coinViewSprite) {
        return coinViewSprite.getSize();
    }

    private void displayCoins(Renderer<S> renderer, World world) {
        Vector3f textPosition = getSpritePosition().add(new Vector3f(120, 120, 0));
        renderer.writeText(textPosition, String.valueOf(world.getMoney().get()), new Color(0, 0, 0), new Font("Comic Sans MS", 16));
    }
}