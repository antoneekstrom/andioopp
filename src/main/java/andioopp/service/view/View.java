package andioopp.service.view;

import andioopp.common.transform.*;
import andioopp.model.Lane;
import andioopp.model.Model;
import andioopp.model.World;
import andioopp.model.enemy.Enemy;
import andioopp.model.tower.Tower;
import andioopp.common.gfx.Color;
import andioopp.common.gfx.Renderer;
import andioopp.common.gfx.Sprite;

public class View<S extends Sprite<?>> {

    private final Renderer<S> renderer;
    private final Vector3f position;
    private final Vector3f size;

    public View(Renderer<S> renderer, Vector3f position, Vector3f size) {
        this.renderer = renderer;
        this.position = position;
        this.size = size;
    }

    private static final Color COLOR_CELL_ODD = new Color(112, 146, 85);
    private static final Color COLOR_CELL_EVEN = new Color(62, 86, 34);

    private static final TransformFactory transformFactory = ConcreteTransform.getFactory();

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
        for (Enemy enemy : world.getEnemies()) {
            renderEnemy(world, enemy);
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
        Vector3f cellScreenPosition = getCellScreenPosition(world, row, col);
        Vector3f cellScreenSize = getCellScreenSize(world);

        getRenderer().drawRectangle(cellScreenPosition, cellScreenSize, getCellColor(row, col));
    }

    private void renderEnemy(World world, Enemy enemy) {
        Dimension cellScreenSize = new Dimension(getCellScreenSize(world));

        Vector3f enemyScreenOffset = enemy.getPosition().scale(cellScreenSize.toVector());
        Vector3f enemyScreenOffsetLane = cellScreenSize.toVector().onlyY().scale(-0.3f);
        Vector3f enemyScreenPosition = getViewPosition().add(enemyScreenOffset).add(enemyScreenOffsetLane);

        S enemySprite = enemy.getSprite(getRenderer().getSpriteFactory());
        Dimension enemySpriteSize = enemySprite.getSize();
        Dimension enemyScreenSize = enemySpriteSize.scaleByHeight(cellScreenSize.getHeight() * 0.8f);

        Transform enemyScreenTransform = transformFactory.createWithPosition(enemyScreenPosition);

        getRenderer().drawSprite(enemySprite, enemyScreenTransform, enemyScreenSize.toVector());
    }

    private void renderTowerInCell(World world, int row, int col) {
        Tower tower = world.getCell(row, col).getTower();

        if (tower != null) {
            Vector3f cellScreenPosition = getCellScreenPosition(world, row, col);
            Dimension cellScreenSize = new Dimension(getCellScreenSize(world));

            S towerSprite = tower.getSprite(getRenderer().getSpriteFactory());
            Dimension towerSpriteSize = towerSprite.getSize();
            Dimension towerScreenSize = towerSpriteSize.scaleByHeight(cellScreenSize.getHeight());

            Vector3f towerScreenPositionCenter = cellScreenSize.centerWithin(cellScreenPosition, towerScreenSize);
            Vector3f towerScreenPositionOffset = cellScreenSize.toVector().onlyY().scale(-0.3f);
            Vector3f towerScreenPosition = towerScreenPositionCenter.add(towerScreenPositionOffset);

            Transform towerScreenTransform = transformFactory.createWithPosition(towerScreenPosition);

            getRenderer().drawSprite(towerSprite, towerScreenTransform, towerScreenSize.toVector());
        }
    }

    private Vector3f getCellScreenPosition(World world, int row, int col) {
        Vector3f cellScreenSize = getCellScreenSize(world);
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

    private Vector3f getCellScreenSize(World world) {
        return new Vector3f(getCellScreenWidth(world.getLane(0)), getLaneScreenHeight(world));
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
