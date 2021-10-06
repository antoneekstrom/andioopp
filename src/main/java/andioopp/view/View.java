package andioopp.view;

import andioopp.common.storage.ArrayListFactory;
import andioopp.common.storage.ListFactory;
import andioopp.common.transform.*;
import andioopp.model.tower.towers.Mario;
import andioopp.model.world.Lane;
import andioopp.model.Model;
import andioopp.model.world.World;
import andioopp.model.enemy.Enemy;
import andioopp.model.tower.Tower;
import andioopp.common.gfx.Color;
import andioopp.common.gfx.Renderer;
import andioopp.common.gfx.Sprite;
import andioopp.model.tower.attack.projectiles.Projectile;
import andioopp.service.infrastructure.gui.CoinView;

import java.util.List;
import andioopp.service.infrastructure.gui.TowerCard;
import andioopp.service.infrastructure.gui.TowerCardsView;

public class View<S extends Sprite<?>> {

    private static final Color COLOR_CELL_ODD = new Color(112, 146, 85);
    private static final Color COLOR_CELL_EVEN = new Color(62, 86, 34);
    public static final Vector3f TOWER_CARD_LIST_POSITION = new Vector3f(380, 10);

    private final Renderer<S> renderer;
    private final Vector3f position;
    private final Vector3f size;
    private final CoinView<S> coinView;
    private final TowerCardsView<S> towerCardsView;

    private static final TransformFactory transformFactory = ConcreteTransform.getFactory();

    public View(Renderer<S> renderer, Vector3f position, Vector3f size) {
        this.renderer = renderer;
        this.position = position;
        this.size = size;
        this.coinView = new CoinView<>();
        this.towerCardsView = new TowerCardsView<>();
        towerCardsView.createTowerCardsList();
    }

    public Rectangle getCellRectangle(World world, int row, int col) {
        return new Rectangle(getCellScreenPosition(world, row, col), new Dimension(getCellScreenSize(world)));
    }

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
        coinView.renderCoinView(world, renderer, getViewSize());

        towerCardsView.renderTowerCardsList(renderer, TOWER_CARD_LIST_POSITION);
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
        Transform enemyScreenTransform = transformFactory.createWithPosition(getPluppScreenPosition(world, enemy.getPosition(), enemyScreenSize));
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
            Dimension enemyScreenSize = getEnemyScreenSize(world, fireballSprite);
            Transform enemyScreenTransform = transformFactory.createWithPosition(getPluppScreenPosition(world, projectile.getPosition(), enemyScreenSize));
            getRenderer().drawSprite(fireballSprite, enemyScreenTransform, enemyScreenSize.toVector());
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

    private Vector3f getPluppScreenPosition(World world, Vector3f position, Dimension size) {
        return getViewPosition().add(position.scale(getCellScreenSize(world))).add(alignWithCellBottom(world, size)).add(getEntityCellOffset(world));
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
        return cellScreenPositionCenter.add(offsetToBottom);
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

    public TowerCardsView<?> getTowerCardsView() {
        return towerCardsView;
    }
}
