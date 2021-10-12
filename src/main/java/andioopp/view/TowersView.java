package andioopp.view;

import andioopp.common.gfx.Renderer;
import andioopp.common.gfx.Sprite;
import andioopp.common.transform.*;
import andioopp.model.tower.Tower;
import andioopp.model.world.World;

public class TowersView<S extends Sprite<?>> {

    private static final float TOWER_CELL_OFFSET_PERCENT = -0.3f;

    private final Renderer<S> renderer;
    private final CellsView<S> cellsView;
    private final TransformFactory transformFactory;

    public TowersView(Renderer<S> renderer, CellsView<S> cellsView, TransformFactory transformFactory) {
        this.renderer = renderer;
        this.cellsView = cellsView;
        this.transformFactory = transformFactory;
    }

    public void render(World world) {
        for (int row = 0; row < world.getNumberOfLanes(); row++) {
            for (int col = 0; col < world.getNumberOfCellsInLanes(); col++) {
                renderTower(world, row, col);
            }
        }
    }

    private void renderTower(World world, int row, int col) {
        Tower tower = world.getCell(row, col).getTower();
        if (tower != null) {
            Vector3f cellScreenPosition = cellsView.getCellScreenPosition(world, row, col);
            S towerSprite = tower.getSprite(getRenderer().getSpriteFactory());
            Dimension towerScreenSize = new Dimension(getTowerScreenSize(world, tower).toVector());
            Transform towerScreenTransform = transformFactory.createWithPosition(getTowerScreenPosition(world, cellScreenPosition, towerScreenSize));
            getRenderer().drawSprite(towerSprite, towerScreenTransform, towerScreenSize.toVector());
        }
    }

    private Vector3f alignWithCellBottom(World world, Dimension size) {
        Dimension cellScreenSize = cellsView.getCellScreenSize(world);
        Vector3f cellScreenPositionCenter = cellScreenSize.centerWithin(Vector3f.zero(), size);
        Vector3f offsetToBottom = cellScreenSize.toVector().onlyY().scale(0.5f).sub(size.toVector().onlyY().scale(0.5f));
        return cellScreenPositionCenter.add(offsetToBottom);
    }

    private Vector3f getTowerScreenPosition(World world, Vector3f cellScreenPosition, Dimension size) {
        return cellScreenPosition.add(alignWithCellBottom(world, size)).add(getEntityCellOffset(world));
    }

    private Vector3f getEntityCellOffset(World world) {
        return cellsView.getCellScreenSize(world).toVector().onlyY().scale(TOWER_CELL_OFFSET_PERCENT);
    }

    private Dimension getTowerScreenSize(World world, Tower tower) {
        Dimension cellScreenSize = new Dimension(cellsView.getCellScreenSize(world));
        S towerSprite = tower.getSprite(getRenderer().getSpriteFactory());
        Dimension towerSpriteSize = towerSprite.getSize();
        return towerSpriteSize.scaleByHeight(cellScreenSize.getHeight());
    }


    private Renderer<S> getRenderer() {
        return renderer;
    }
}
