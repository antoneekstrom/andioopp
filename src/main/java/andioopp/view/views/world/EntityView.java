package andioopp.view.views.world;

import andioopp.common.graphics.Sprite;
import andioopp.common.math.Dimension;
import andioopp.common.math.Vector3f;
import andioopp.common.math.rectangle.ImmutableRectangle;
import andioopp.common.math.rectangle.RectanglePlupp;

public class EntityView extends CellView {

    protected EntityView(RectanglePlupp viewportRect) {
        super(viewportRect);
    }

    protected RectanglePlupp getEntityRect(World world, Vector3f position, Sprite<?> sprite) {
        return new ImmutableRectangle(getEntityPosition(world, position), getEntitySize(world, sprite));
    }

    protected Vector3f getEntityPosition(World world, Vector3f position) {
        return getViewport(world).toViewportPosition(position);
    }

    protected Dimension getEntitySize(World world, Sprite<?> sprite) {
        return getViewport(world).toViewportSize(new Dimension(sprite.getSize().setHeight(Dimension.unit().getHeight())));
    }
}
