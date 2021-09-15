package andioopp.domain.view;

import andioopp.common.transform.ConcreteTransform;
import andioopp.common.transform.Transform;
import andioopp.common.transform.Vector3f;
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
    private final Vector3f windowSize = new Vector3f(1280, 720);
    private final float worldSizeFactorX = 0.7f;
    private final float worldSizeFactorY = 0.7f;
    private final Vector3f worldSize = new Vector3f(windowSize.getX() * worldSizeFactorX, windowSize.getY() * worldSizeFactorY);
    private final Vector3f worldPos = new Vector3f(windowSize.getX() - worldSize.getX(), (windowSize.getY() - worldSize.getY()) * 0.5f);

    private final static Color COLOR_CELL_ODD = new Color(112, 146, 85);
    private final static Color COLOR_CELL_EVEN = new Color(62, 86, 34);

    public View(Renderer<S> renderer) {
        this.renderer = renderer;
    }

    public void render(Model model) {
        World world = model.getWorld();

        getRenderer().clear(Color.WHITE);
        int numLanes = world.getLanes().size();
        float laneHeight = worldSize.getY() / numLanes;

        for (int i = 0; i < numLanes; i++) {
            Lane lane = world.getLanes().get(i);
            Vector3f lanePos = worldPos.add(new Vector3f(0, laneHeight * i));
            getRenderer().drawRectangle(lanePos, new Vector3f(worldSize.getX(), laneHeight), chooseColor(i));

            drawCells(lane, lanePos, laneHeight, i);

            for (Enemy e : lane.getEnemies()) {
                Vector3f laneEndPos = lanePos.add(Vector3f.withX(worldSize.getX()));
                Vector3f enemyOffset = new Vector3f(-e.getLaneProgress()).scale(new Vector3f(worldSize.getX()));
                Vector3f enemyPos = enemyOffset.add(laneEndPos);
                Transform enemyTransform = ConcreteTransform.getFactory().createWithPosition(enemyPos);
                getRenderer().drawSprite(e.getSprite(renderer.getSpriteFactory()), enemyTransform);
            }
        }

        renderTowers(model);
    }

    private Color chooseColor(int i) {
        if (i % 2 == 0) {
            return COLOR_CELL_EVEN;
        }
        return COLOR_CELL_ODD;

    }

    private void drawCells(Lane lane, Vector3f lanePos, float laneHeight, int row) {
        int numCells = lane.getCells().size();
        float cellWidth = worldSize.getX() / numCells;
        for (int col = 0; col < numCells; col++) {
            getRenderer().drawRectangle(lanePos.add(new Vector3f(cellWidth * col, 0, 0)), new Vector3f(cellWidth, laneHeight), (row % 2 == col % 2) ? COLOR_CELL_EVEN : COLOR_CELL_ODD);
        }
    }

    private void renderTowers(Model model) {
        Transform towerTransform = ConcreteTransform.getFactory().createWithPosition(new Vector3f(40, 40, 40));
        for (Lane lane : model.getWorld().getLanes()) {
            for (Cell cell : lane.getCells()) {

                if (cell.getTower() != null) {
                    Tower t = cell.getTower();
                    getRenderer().drawSprite(t.getSprite(renderer.getSpriteFactory()), towerTransform);
                }
            }
        }
    }

    private Renderer<S> getRenderer() {
        return renderer;
    }
}
