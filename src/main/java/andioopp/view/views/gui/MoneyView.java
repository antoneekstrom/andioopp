package andioopp.view.views.gui;

import andioopp.common.graphics.Color;
import andioopp.common.graphics.Renderer;
import andioopp.common.graphics.Sprite;
import andioopp.common.graphics.SpriteFactory;
import andioopp.common.math.Dimension;
import andioopp.common.math.Vector3f;
import andioopp.common.math.transform.Transform;
import andioopp.common.math.transform.TransformFactory;
import andioopp.model.Model;
import andioopp.model.domain.enemy.Enemy;
import andioopp.model.domain.player.Player;
import andioopp.model.util.ModelCoordinate;
import andioopp.view.View;
import andioopp.view.util.ModelViewport;
import andioopp.view.util.ViewCoordinate;
import javafx.scene.text.Font;

public class MoneyView implements View<Model> {

    private static final String sprite = "coinBox.png";
    private static final int WIDTH = 120;
    private static final int HEIGHT = 100;


    private final TransformFactory transformFactory;

    public MoneyView(TransformFactory transformFactory) {
        this.transformFactory = transformFactory;
    }

    private Vector3f getSpritePosition() {
        float distanceFromBorderX = 50f;
        float distanceFromBorderY = 10f;
        return new Vector3f(distanceFromBorderX, distanceFromBorderY);
    }

    private void displayCoins(Renderer<?> renderer, Player player) {
        Vector3f textPosition = getSpritePosition().add(new Vector3f(120, 120, 0));
        renderer.writeText(textPosition, String.valueOf(player.getMoney().getValue()), Color.BLACK, new Font("Comic Sans MS", 16));
    }


    @Override
    public <S extends Sprite<?>> void render(Model model, Renderer<S> renderer, ModelViewport viewport) {
        S coinViewSprite = renderer.getSpriteFactory().get("");
        ViewCoordinate viewPosition = viewport.getViewCoordinate(new ModelCoordinate(getSpritePosition()));

        Dimension coinViewScreenSize = viewport.getViewSize(new Dimension(new Vector3f(WIDTH,HEIGHT,0)));

        //Transform coinViewScreenTransform = transformFactory.createWithPosition(getSpritePosition());
        renderer.drawSprite(coinViewSprite, viewPosition, coinViewScreenSize);
        displayCoins(renderer, model.getPlayer());

    }
}