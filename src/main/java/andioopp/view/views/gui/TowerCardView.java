package andioopp.view.views.gui;

import andioopp.common.graphics.Color;
import andioopp.common.graphics.Renderer;
import andioopp.common.graphics.Sprite;
import andioopp.common.math.dimension.Dimension;
import andioopp.common.math.rectangle.ImmutableRectangle;
import andioopp.common.math.rectangle.Rectangle;
import andioopp.common.math.vector.Vector3f;
import andioopp.model.Model;
import andioopp.model.domain.player.Player;
import andioopp.model.domain.player.TowerCard;
import andioopp.model.domain.tower.Tower;
import andioopp.model.util.ModelCoordinate;
import andioopp.view.View;
import andioopp.view.util.Viewport;
import javafx.scene.text.Font;

public class TowerCardView implements View<Model> {

    public final static Dimension CARD_SIZE = new Dimension(110, 170);
    public static final Color BACKGROUND_COLOR = new Color(150, 150, 150);
    public static final Color ALT_BACKGROUND_COLOR = new Color(100, 100, 100);

    private final Viewport viewport;
    private final TowerCard<?> card;
    private final Tower tower;

    public TowerCardView(Viewport viewport, TowerCard<?> card) {
        this.viewport = viewport;
        this.card = card;
        this.tower = card.getSupplier().create(new ModelCoordinate(Vector3f.ZERO));
    }

    @Override
    public <S extends Sprite<?>> void render(Model model, Renderer<S> renderer) {
        S sprite = renderer.getSpriteFactory().get(tower.getSprite());
        Rectangle cardViewRectangle = getCardViewRectangle();
        Rectangle imageViewRectangle = getImageViewRectangle(cardViewRectangle, sprite);
        Vector3f textPosition = getTextPosition(imageViewRectangle);
        Vector3f costPosition = getCostPosition(textPosition);
        String costStr = String.valueOf(card.getCost().getValue());
        String nameStr = tower.getName();

        if (towerIsAvailable(model.getPlayer(), card)) {
            renderer.drawRectangle(cardViewRectangle, BACKGROUND_COLOR);
        }else{
            renderer.drawRectangle(cardViewRectangle, ALT_BACKGROUND_COLOR);
        }

        renderer.drawSprite(sprite, imageViewRectangle);
        renderer.writeText(textPosition, nameStr, Color.BLACK, new Font("Comic Sans MS", 16));
        renderer.writeText(costPosition, costStr, Color.BLACK, new Font("Comic Sans MS", 22));
    }

    boolean towerIsAvailable(Player player, TowerCard<?> card) {
        return player.getMoney().canSpend(card.getCost());
    }




    /**
     * Returns position for cost of Tower
     *
     * @param textPosition position of the text
     */
    private Vector3f getCostPosition(Vector3f textPosition) {
        return textPosition.add(Vector3f.fromY(20)).add(Vector3f.fromX(10));
    }

    /**
     * Returns position for name of tower
     *
     * @param cardViewRectangle rectangle of the card
     */
    private Vector3f getTextPosition(Rectangle cardViewRectangle) {
        Vector3f bottom = cardViewRectangle.getPosition().add(cardViewRectangle.getSize().toVector());
        bottom = bottom.add(Vector3f.fromY(20)).add(Vector3f.fromX(-75));
        return bottom;
    }

    public Rectangle getCardViewRectangle() {
        Vector3f cardPosition = viewport.getPositionOutside(Vector3f.ZERO);
        Dimension cardSize = viewport.getSizeOutside(Dimension.UNIT);
        return new ImmutableRectangle(cardPosition, cardSize);
    }

    private Rectangle getImageViewRectangle(Rectangle cardViewRectangle, Sprite sprite) {
        float cardWidth = cardViewRectangle.getSize().getWidth();
        float padding = cardWidth * 0.3f;
        Dimension imageSize = sprite.getSize().setHeight(80);
        //Dimension imageSize = IMAGE_ASPECT_RATIO.setWidth(cardWidth - padding);
        Rectangle imageViewRectangle = new ImmutableRectangle(Vector3f.ZERO, imageSize);
        imageViewRectangle = cardViewRectangle.centerWithin(imageViewRectangle);
        imageViewRectangle = imageViewRectangle.setPosition(imageViewRectangle.getPosition().setY(padding));
        return imageViewRectangle;
    }
}
