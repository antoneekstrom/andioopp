package andioopp.domain.view;

import andioopp.common.transform.ConcreteTransform;
import andioopp.common.transform.Transform;
import andioopp.common.transform.Vector3f;
import andioopp.domain.model.Cell;
import andioopp.domain.model.Lane;
import andioopp.domain.model.World;
import andioopp.domain.model.enemy.Enemy;
import andioopp.domain.model.tower.Tower;
import andioopp.gfx.Color;
import andioopp.gfx.Renderer;
import andioopp.gfx.Sprite;

public class View<S extends Sprite<?>> {

    private final Renderer<S> renderer;
    private final Vector3f position;
    private final Vector3f size;

    public View(Renderer<S> renderer, Vector3f position, Vector3f size) {
        this.renderer = renderer;
        this.position = position;
        this.size = size;
    }

    private final static Color COLOR_CELL_ODD = new Color(112, 146, 85);
    private final static Color COLOR_CELL_EVEN = new Color(62, 86, 34);

    public void render(World world) {
        getRenderer().clear(Color.WHITE);
        renderLanes(world);
    }

    private void renderLanes(World world) {
        for (int i = 0; i < getLaneCount(world); i++) {
            Lane lane = world.getLanes().get(i);
            Vector3f laneScreenPosition = getLaneScreenPosition(world, i);
            Vector3f laneSize = new Vector3f(getLaneWidth(), getLaneHeight(world));
            renderCells(world, lane, i, laneScreenPosition);
            renderEnemies(world, lane, laneScreenPosition, laneSize.getX());
        }
    }

    private void renderEnemies(World world, Lane lane, Vector3f laneScreenPosition, float laneWidth) {
        for (Enemy e : lane.getEnemies()) {
            Vector3f laneEndPosition = laneScreenPosition.add(Vector3f.withX(laneWidth));
            Vector3f enemyOffset = new Vector3f(-e.getLaneProgress()).scale(new Vector3f(getSize().getX()));
            enemyOffset = enemyOffset.sub(Vector3f.withY(getCellDimensions(world, lane).getY() * 0.35f));
            Vector3f enemyPos = laneEndPosition.add(enemyOffset);
            Transform enemyTransform = ConcreteTransform.getFactory().createWithPosition(enemyPos);
            getRenderer().drawSprite(e.getSprite(renderer.getSpriteFactory()), enemyTransform, getCellDimensions(world, lane).scale(0.7f));
        }
    }

    private void renderCells(World world, Lane lane, int row, Vector3f laneScreenPosition) {
        for (int col = 0; col < getCellCount(lane); col++) {
            Cell cell = lane.getCells().get(col);
            Vector3f cellDimensions = getCellDimensions(world, lane);
            Vector3f cellPositionOffset = Vector3f.withX(cellDimensions.getX() * col);
            Vector3f cellPosition = laneScreenPosition.add(cellPositionOffset);

            getRenderer().drawRectangle(cellPosition, cellDimensions, getCellColor(row, col));
            renderTower(world, lane, cell.getTower(), cellPosition);
        }
    }

    private void renderTower(World world, Lane lane, Tower tower, Vector3f cellPosition) {
        if (tower != null) {
            Sprite<?> sprite = tower.getSprite(renderer.getSpriteFactory());
            Transform transform = ConcreteTransform.getFactory().createWithPosition(cellPosition);

            getRenderer().drawSprite(tower.getSprite(renderer.getSpriteFactory()), transform, getCellDimensions(world, lane));
        }
    }

    private Vector3f getCellCenter(World world, Lane lane, Vector3f cellPosition) {
        return cellPosition.add(getCellDimensions(world, lane).scale(Vector3f.all(0.5f)));
    }

    private Vector3f getCellDimensions(World world, Lane lane) {
        return new Vector3f(getCellWidth(lane), getLaneHeight(world));
    }

    private float getLaneWidth() {
        return getSize().getX();
    }

    private Vector3f getLaneScreenPosition(World world, int laneIndex) {
        Vector3f laneOffset = Vector3f.withY(getLaneHeight(world) * laneIndex);
        return getPosition().add(laneOffset);
    }

    private Color getCellColor(int i, int j) {
        if (i % 2 == j % 2) {
            return COLOR_CELL_EVEN;
        }
        return COLOR_CELL_ODD;
    }

    private float getCellWidth(Lane lane) {
        return getLaneWidth() / getCellCount(lane);
    }

    private float getLaneHeight(World world) {
        return getSize().getY() / getLaneCount(world);
    }

    private int getCellCount(Lane lane) {
        return lane.getCells().size();
    }

    private int getLaneCount(World world) {
        return world.getLanes().size();
    }

    private Vector3f getPosition() {
        return position;
    }

    private Vector3f getSize() {
        return size;
    }

    private Renderer<S> getRenderer() {
        return renderer;
    }
}
