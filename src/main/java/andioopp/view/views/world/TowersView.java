package andioopp.view.views.world;

import andioopp.common.graphics.Renderer;
import andioopp.common.graphics.Sprite;
import andioopp.common.math.Dimension;
import andioopp.common.math.Vector3f;
import andioopp.common.math.transform.Transform;
import andioopp.common.math.transform.TransformFactory;
import andioopp.model.Model;
import andioopp.model.domain.tower.Tower;
import andioopp.model.domain.world.Cell;
import andioopp.model.domain.world.World;
import andioopp.model.util.ModelCoordinate;
import andioopp.view.View;
import andioopp.view.util.ModelViewport;
import andioopp.view.util.ViewCoordinate;

public class TowersView implements View<Model> {

    private static final float TOWER_CELL_OFFSET_PERCENT = -0.3f;

    @Override
    public <S extends Sprite<?>> void render(Model model, Renderer<S> renderer, ModelViewport viewport) {
        for(int row = 0; row < model.getWorld().getNumberOfLanes(); row++) {
            for(int col = 0 ; col < model.getWorld().getNumberOfCellsInLanes(); col ++ ) {

                Cell cell = model.getWorld().getCell(row, col);
                renderTower(renderer, model.getWorld(), cell.getTower(), viewport, row, col);

            }
        }

    }

    private <S extends Sprite<?>> void renderTower(Renderer<S> renderer, World world, Tower tower, ModelViewport viewport, int row, int col) {
        S sprite = tower.getSprite(renderer.getSpriteFactory());

        ViewCoordinate viewPosition = viewport.getViewCoordinate(new ModelCoordinate(row, col));
        Dimension viewSize = viewport.getViewSize(tower.getSize());

        renderer.drawSprite(sprite, viewPosition, viewSize);
    }

}
