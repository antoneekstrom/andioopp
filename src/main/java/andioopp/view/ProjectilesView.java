package andioopp.view;

import andioopp.common.gfx.Renderer;
import andioopp.common.gfx.Sprite;
import andioopp.common.transform.*;
import andioopp.model.tower.attack.projectiles.Projectile;
import andioopp.model.world.World;

public class ProjectilesView<S extends Sprite<?>> {

    private static final float TOWER_CELL_OFFSET_PERCENT = -0.3f;

    private final Renderer<S> renderer;
    private final CellsView<S> cellsView;
    private final Rectangle viewport;

    private final TransformFactory transformFactory;

    public ProjectilesView(Renderer<S> renderer, CellsView<S> cellsView, Rectangle viewport, TransformFactory transformFactory) {
        this.renderer = renderer;
        this.cellsView = cellsView;
        this.viewport = viewport;
        this.transformFactory = transformFactory;
    }

    public void render(World world) {
        S fireballSprite = getRenderer().getSpriteFactory().get("fireball.png");
        for (Projectile projectile : world.getProjectiles()) {
            Dimension enemyScreenSize = new Dimension(50, 50);
            Transform enemyScreenTransform = transformFactory.createWithPosition(getEntityScreenPosition(world, projectile.getPosition(), enemyScreenSize));
            getRenderer().drawSprite(fireballSprite, enemyScreenTransform, enemyScreenSize.toVector());
        }
    }

    private Vector3f getEntityScreenPosition(World world, Vector3f position, Dimension size) {
        return getViewportPosition().add(position.scale(cellsView.getCellScreenSize(world).toVector())).add(alignWithCellBottom(world, size)).add(getEntityCellOffset(world));
    }

    private Vector3f getEntityCellOffset(World world) {
        return cellsView.getCellScreenSize(world).toVector().onlyY().scale(TOWER_CELL_OFFSET_PERCENT);
    }

    private Vector3f alignWithCellBottom(World world, Dimension size) {
        Dimension cellScreenSize = cellsView.getCellScreenSize(world);
        Vector3f cellScreenPositionCenter = cellScreenSize.centerWithin(Vector3f.zero(), size);
        Vector3f offsetToBottom = cellScreenSize.toVector().onlyY().scale(0.5f).sub(size.toVector().onlyY().scale(0.5f));
        return cellScreenPositionCenter.add(offsetToBottom);
    }

    private Vector3f getViewportPosition() {
        return getViewport().getPosition();
    }

    private Dimension getViewportSize() {
        return getViewport().getSize();
    }

    private Rectangle getViewport() {
        return viewport;
    }

    private Renderer<S> getRenderer() {
        return renderer;
    }
}
