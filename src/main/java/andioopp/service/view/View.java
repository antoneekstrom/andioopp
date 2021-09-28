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
import andioopp.model.tower.attack.projectiles.Projectile;

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
        renderProjectiles(world);
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
        S enemySprite = enemy.getSprite(getRenderer().getSpriteFactory());
        Dimension enemyScreenSize = getEnemyScreenSize(world, enemySprite);
        Transform enemyScreenTransform = transformFactory.createWithPosition(getEnemyScreenPosition(world, enemy, enemyScreenSize));
        getRenderer().drawSprite(enemySprite, enemyScreenTransform, enemyScreenSize.toVector());
    }

    private void renderTowerInCell(World world, int row, int col) {
        Tower tower = world.getCell(row, col).getTower();
        if (tower != null) {
            Vector3f cellScreenPosition = getCellScreenPosition(world, row, col);
            S towerSprite = tower.getSprite(getRenderer().getSpriteFactory());
            Dimension towerScreenSize = new Dimension(getTowerScreenSize(world, tower).toVector());
            Transform towerScreenTransform = transformFactory.createWithPosition(getTowerScreenPosition(world, cellScreenPosition, towerScreenSize));
            getRenderer().drawSprite(towerSprite, towerScreenTransform, towerScreenSize.toVector());
        }
    }

    private void renderProjectiles(World world) {
        S fireballSprite = getRenderer().getSpriteFactory().get("fireball.png");
        for (Projectile projectile : world.getProjectiles()) {
            getRenderer().drawSprite(fireballSprite, ConcreteTransform.getFactory().createWithPosition(projectile.getPosition().scale(getCellScreenSize(world))));
        }
    }

    private Vector3f getTowerScreenPosition(World world, Vector3f cellScreenPosition, Dimension size) {
        return cellScreenPosition.add(alignWithCellBottom(world, size)).add(getEntityCellOffset(world));
    }

    private Dimension getTowerScreenSize(World world, Tower tower) {
        Dimension cellScreenSize = new Dimension(getCellScreenSize(world));
        S towerSprite = tower.getSprite(getRenderer().getSpriteFactory());
        Dimension towerSpriteSize = towerSprite.getSize();
        return towerSpriteSize.scaleByHeight(cellScreenSize.getHeight());
    }

    private Vector3f getEnemyScreenPosition(World world, Enemy enemy, Dimension size) {
        return getViewPosition().add(enemy.getPosition().scale(getCellScreenSize(world))).add(alignWithCellBottom(world, size)).add(getEntityCellOffset(world));
    }

    private Dimension getEnemyScreenSize(World world, S enemySprite) {
        Dimension cellScreenSize = new Dimension(getCellScreenSize(world));
        Dimension enemySpriteSize = enemySprite.getSize();
        return enemySpriteSize.scaleByHeight(cellScreenSize.getHeight() * 0.7f);
    }

    private Vector3f alignWithCellBottom(World world, Dimension size) {
        Dimension cellScreenSize = new Dimension(getCellScreenSize(world));
        Vector3f cellScreenPositionCenter = cellScreenSize.centerWithin(Vector3f.zero(), size);
        Vector3f offsetToBottom = cellScreenSize.toVector().onlyY().scale(0.5f).sub(size.toVector().onlyY().scale(0.5f));
        // Vector3f cellScreenPositionBottom = cellScreenPositionCenter.add(cellScreenSize.toVector().onlyY().scale(0.5f));
        return cellScreenPositionCenter.add(offsetToBottom); // cellScreenPositionBottom.sub(size.toVector());
    }

    private Vector3f getEntityCellOffset(World world) {
        return getCellScreenSize(world).onlyY().scale(-0.3f);
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
