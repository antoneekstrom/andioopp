package andioopp.view;

import andioopp.common.graphics.Sprite;
import andioopp.common.transform.Dimension;
import andioopp.common.transform.Rectangle;
import andioopp.common.transform.Vector3f;
import andioopp.model.world.World;

public class EntityView extends CellView {

    protected EntityView(Rectangle viewportRect) {
        super(viewportRect);
    }

    protected Rectangle getEntityRect(World world, Vector3f position, Sprite<?> sprite) {
        return new Rectangle(getEntityPosition(world, position), getEntitySize(world, sprite));
    }

    protected Vector3f getEntityPosition(World world, Vector3f position) {
        return getViewport(world).toViewportPosition(position);
    }

    protected Dimension getEntitySize(World world, Sprite<?> sprite) {
        return getViewport(world).toViewportSize(new Dimension(sprite.getSize().scaleByHeight(Dimension.unit().getHeight())));
    }
}
