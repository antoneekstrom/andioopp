package andioopp.view.gui;

import andioopp.common.graphics.Color;
import andioopp.common.graphics.Renderer;
import andioopp.common.graphics.Sprite;
import andioopp.common.graphics.SpriteFactory;
import andioopp.common.transform.Dimension;
import andioopp.common.transform.Transform;
import andioopp.common.transform.TransformFactory;
import andioopp.common.transform.Vector3f;
import andioopp.model.Model;
import andioopp.model.player.Player;
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
        S coinViewSprite = getSprite(renderer.getSpriteFactory());
        Dimension coinViewScreenSize = getCoinViewScreenSize(coinViewSprite);
        Transform coinViewScreenTransform = transformFactory.createWithPosition(getSpritePosition());
        renderer.drawSprite(coinViewSprite, coinViewScreenTransform, coinViewScreenSize);
        displayCoins(renderer, model.getPlayer());
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

    private void displayCoins(Renderer<S> renderer, Player player) {
        Vector3f textPosition = getSpritePosition().add(new Vector3f(120, 120, 0));
        renderer.writeText(textPosition, String.valueOf(player.getMoney().getMoney()), Color.BLACK, new Font("Comic Sans MS", 16));
    }
}