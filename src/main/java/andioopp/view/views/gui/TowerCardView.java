package andioopp.view.views.gui;

import andioopp.common.graphics.Color;
import andioopp.common.graphics.Renderer;
import andioopp.common.graphics.Sprite;
import andioopp.common.math.dimension.Dimension;
import andioopp.common.math.rectangle.ImmutableRectangle;
import andioopp.common.math.rectangle.Rectangle;
import andioopp.common.math.vector.Vector3f;
import andioopp.common.math.transform.ConcreteTransform;
import andioopp.common.math.transform.TransformFactory;
import andioopp.model.Model;
import andioopp.model.domain.player.TowerCard;
import andioopp.model.domain.tower.Tower;
import andioopp.model.util.ModelCoordinate;
import andioopp.view.View;
import andioopp.view.util.ModelViewport;
import andioopp.view.util.ViewCoordinate;
import andioopp.view.util.Viewport;
import javafx.scene.text.Font;

public class TowerCardView implements View<Model> {

    public final static Dimension CARD_SIZE = new Dimension(110, 170);
    public static final Color BACKGROUND_COLOR = new Color(150, 150, 150);
    public static final Dimension IMAGE_ASPECT_RATIO = new Dimension(1, 1.2f);

    private final Viewport viewport;
    private final Tower tower;

    public TowerCardView(Viewport viewport, TowerCard<?> card) {
        this.viewport = viewport;
        this.tower = card.getSupplier().create(new ModelCoordinate(Vector3f.zero()));
    }

    @Override
    public <S extends Sprite<?>> void render(Model model, Renderer<S> renderer) {
        S sprite = renderer.getSpriteFactory().get(tower.getSprite());
        Rectangle cardViewRectangle = getCardViewRectangle();
        Rectangle imageViewRectangle = getImageViewRectangle(cardViewRectangle);
        Vector3f textPosition = getTextPosition(imageViewRectangle);
        Vector3f costPosition = getCostPosition(textPosition);
        String costStr = String.valueOf(tower.getCost().getValue());
        String nameStr = tower.getName();

        renderer.drawRectangle(cardViewRectangle, BACKGROUND_COLOR);
        renderer.drawSprite(sprite, imageViewRectangle);
        renderer.writeText(textPosition, nameStr, Color.BLACK, new Font("Comic Sans MS", 16));
        renderer.writeText(costPosition, costStr, Color.BLACK, new Font("Comic Sans MS", 22));
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

    private Rectangle getImageViewRectangle(Rectangle cardViewRectangle) {
        float cardWidth = cardViewRectangle.getSize().getWidth();
        float padding = cardWidth * 0.2f;
        Dimension imageSize = IMAGE_ASPECT_RATIO.setWidth(cardWidth - padding);
        Rectangle imageViewRectangle = new ImmutableRectangle(Vector3f.ZERO, imageSize);
        imageViewRectangle = cardViewRectangle.centerWithin(imageViewRectangle);
        imageViewRectangle = imageViewRectangle.setPosition(imageViewRectangle.getPosition().setY(padding));
        return imageViewRectangle;
    }
}
