package andioopp.view.views.gui;

import andioopp.common.graphics.Color;
import andioopp.common.graphics.Renderer;
import andioopp.common.graphics.Sprite;
import andioopp.common.math.dimension.Dimension;
import andioopp.common.math.vector.Vector3f;
import andioopp.model.Model;
import andioopp.model.domain.player.Player;
import andioopp.view.View;
import andioopp.view.util.Viewport;
import javafx.scene.text.Font;

public class MoneyView implements View<Model> {

    public static final Dimension DEFAULT_SIZE = new Dimension(276, 176).setHeight(150);
    private static final String BACKGROUND_SPRITE = "coinBox.png";

    private final Viewport viewport;

    public MoneyView(Viewport viewport) {
        this.viewport = viewport;
    }

    @Override
    public <S extends Sprite<?>> void render(Model model, Renderer<S> renderer) {
        S backgroundSprite = renderer.getSpriteFactory().get(BACKGROUND_SPRITE);

        Vector3f position = getPosition();
        Dimension size = getSize();

        renderer.drawSprite(backgroundSprite, position, size);
        displayCoins(renderer, model.getPlayer());
    }

    private void displayCoins(Renderer<?> renderer, Player player) {
        Vector3f textOffset = getSize().toVector().scale(new Vector3f(0.45f, 0.69f));
        Vector3f textPosition = getPosition().add(textOffset);
        String str = String.valueOf(player.getMoney().getValue());
        Color color = Color.BLACK;
        Font font = new Font("Comic Sans MS", 16);
        renderer.writeText(textPosition, str, color, font);
    }

    private Dimension getSize() {
        return viewport.getSizeOutside(Dimension.UNIT);
    }

    private Vector3f getPosition() {
        return viewport.getPositionOutside(Vector3f.zero());
    }
}