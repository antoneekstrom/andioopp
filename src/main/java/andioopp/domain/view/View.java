package andioopp.domain.view;

import andioopp.common.transform.*;
import andioopp.domain.model.Cell;
import andioopp.domain.model.Lane;
import andioopp.domain.model.Model;
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

    /**
     * Renders the model.
     * @param model the model to render
     */
    public void render(Model model) {
        World world = model.getWorld();

        clearScreen();
        renderLanes(world);
        renderTowers(world);
        renderEnemies(world);
    }

    private void clearScreen() {
        getRenderer().clear(Color.WHITE);
    }

    private void renderLanes(World world) {
        for (int row = 0; row < world.getNumberOfLanes(); row++) {
            for (int col = 0; col < world.getNumberOfCellsInLanes(); col++) {
                renderCell(world, row, col);
            }
        }
    }

    private void renderEnemies(World world) {
        for (int row = 0; row < world.getNumberOfLanes(); row++) {
            Lane lane = world.getLane(row);

            for (Enemy enemy : lane.getEnemies()) {
                renderEnemy(world, enemy, row);
            }
        }
    }

    private void renderTowers(World world) {
        for (int row = 0; row < world.getNumberOfLanes(); row++) {
            for (int col = 0; col < world.getNumberOfCellsInLanes(); col++) {
                renderTowerInCell(world, row, col);
            }
        }
    }


    private void renderCell(World world, int row, int col) {
        Lane lane = world.getLane(row);

        Vector3f cellScreenPosition = getCellScreenPosition(world, row, col);
        Vector3f cellScreenSize = getCellScreenSize(world, lane);

        getRenderer().drawRectangle(cellScreenPosition, cellScreenSize, getCellColor(row, col));
    }

    private void renderEnemy(World world, Enemy enemy, int row) {
        Vector3f laneScreenPosition = getLaneScreenPosition(world, row);
        Vector3f laneScreenSize = getLaneScreenSize(world);
        Vector3f laneScreenPositionEnd = laneScreenPosition.add(laneScreenSize.onlyX());

        Vector3f enemyScreenOffset = Vector3f.withX(-enemy.getLaneProgress() * laneScreenSize.getX());
        Vector3f enemyScreenPosition = laneScreenPositionEnd.add(enemyScreenOffset);

        S enemySprite = enemy.getSprite(getRenderer().getSpriteFactory());
        Transform enemyScreenTransform = getTransformFactory().createWithPosition(enemyScreenPosition);
        Vector3f enemySpriteSize = getCellScreenSize(world, world.getLane(row));

        getRenderer().drawSprite(enemySprite, enemyScreenTransform, enemySpriteSize);
    }

    private void renderTowerInCell(World world, int row, int col) {
        Lane lane = world.getLane(row);
        Cell cell = world.getCell(row, col);
        Tower tower = cell.getTower();

        if (tower != null) {
            Vector3f cellScreenPosition = getCellScreenPosition(world, row, col);

            S towerSprite = tower.getSprite(getRenderer().getSpriteFactory());
            Transform towerScreenTransform = getTransformFactory().createWithPosition(cellScreenPosition);
            Vector3f towerSpriteSize = getCellScreenSize(world, lane);

            getRenderer().drawSprite(towerSprite, towerScreenTransform, towerSpriteSize);
        }
    }

    private Vector3f getCellScreenPosition(World world, int row, int col) {
        Vector3f cellScreenSize = getCellScreenSize(world, world.getLane(row));
        Vector3f laneScreenPosition = getLaneScreenPosition(world, row);
        Vector3f cellScreenOffset = cellScreenSize.onlyX().scale(col);

        return laneScreenPosition.add(cellScreenOffset);
    }

    private Color getCellColor(int row, int col) {
        if (row % 2 == col % 2) {
            return COLOR_CELL_EVEN;
        }
        return COLOR_CELL_ODD;
    }

    private Vector3f getLaneScreenSize(World world) {
        return new Vector3f(getLaneScreenWidth(), getLaneScreenHeight(world));
    }

    private Vector3f getCellScreenSize(World world, Lane lane) {
        return new Vector3f(getCellScreenWidth(lane), getLaneScreenHeight(world));
    }

    private float getCellScreenWidth(Lane lane) {
        return getLaneScreenWidth() / lane.getNumberOfCells();
    }

    private float getLaneScreenWidth() {
        return getViewSize().getX();
    }

    private float getLaneScreenHeight(World world) {
        return getViewSize().getY() / world.getNumberOfLanes();
    }

    private Vector3f getLaneScreenPosition(World world, int row) {
        Vector3f laneOffset = Vector3f.withY(getLaneScreenHeight(world) * row);
        return getViewPosition().add(laneOffset);
    }

    private TransformFactory getTransformFactory() {
        return ConcreteTransform.getFactory();
    }

    private Vector3f getViewPosition() {
        return position;
    }

    private Vector3f getViewSize() {
        return size;
    }

    private Renderer<S> getRenderer() {
        return renderer;
    }
}
