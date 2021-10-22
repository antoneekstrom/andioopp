package andioopp.view.views.gui;

import andioopp.common.graphics.Renderer;
import andioopp.common.graphics.Sprite;
import andioopp.common.math.dimension.Dimension;
import andioopp.common.math.interpolation.Interpolations;
import andioopp.common.math.vector.Vector3f;
import andioopp.common.observer.Observer;
import andioopp.controller.controllers.TowerCardDragEvent;
import andioopp.controller.input.MouseMoveEvent;
import andioopp.model.Model;
import andioopp.model.domain.tower.Tower;
import andioopp.model.util.ModelCoordinate;
import andioopp.controller.input.DragAndDrop;
import andioopp.view.View;
import andioopp.view.views.world.TowersView;

/**
 * A View for displaying a Tower while being dragged by mouse.
 */
public class TowerDragMouseView implements View<Model>, Observer<MouseMoveEvent> {

    public static final double MAX_ANGLE = Math.toRadians(70);
    public static final float LERP_AMOUNT = 0.00003f;
    public static final float MOUSE_DELTA_SCALE = 80f;
    public static final float MAX_MOUSE_DELTA_MAG = 40f;
    private final DragAndDrop<TowerCardDragEvent> dragAndDrop;
    private final TowersView towersView;

    private Vector3f mouseVelocity = Vector3f.ZERO, towerVelocity = Vector3f.ZERO;
    private float previousAngle = 0;

    public TowerDragMouseView(DragAndDrop<TowerCardDragEvent> dragAndDrop, TowersView towersView) {
        this.dragAndDrop = dragAndDrop;
        this.towersView = towersView;
    }

    @Override
    public <S extends Sprite<?>> void render(Model model, Renderer<S> renderer) {
        if (dragAndDrop.isDragging()) {
            Tower tower = getTower();
            S sprite = renderer.getSpriteFactory().get(tower.getSprite());

            Dimension size = towersView.getTowerSizeFromSprite(sprite);
            Vector3f mouse = dragAndDrop.getMousePosition();
            Vector3f position = mouse.sub(size.halved().toVector());

            renderer.drawSprite(sprite, position, size);
        }
    }

    @Override
    public void onEvent(MouseMoveEvent e) {
        setMouseVelocity(e);
    }

    private float getSpriteRotationAngle(float spriteHeight) {
        float nextAngle = (float) Math.toDegrees(getAngleFromVelocity(spriteHeight));
        previousAngle = Interpolations.linear(previousAngle, nextAngle, 0.5f);
        updateTowerVelocity(LERP_AMOUNT);
        return previousAngle;
    }

    private float getAngleFromVelocity(float centerOfMassHeight) {
        Vector3f velocityDiff = towerVelocity.sub(mouseVelocity);
        float opposite = velocityDiff.magnitude();
        float dir = Math.signum(velocityDiff.getX());
        double angle = Math.abs(Math.tan(opposite / centerOfMassHeight));
        angle = Math.min(angle, MAX_ANGLE);
        angle *= -dir;
        return (float) angle;
    }

    private void setMouseVelocity(MouseMoveEvent e) {
        Vector3f delta = e.getMouseDelta().scale(MOUSE_DELTA_SCALE);
        delta = delta.limitMagnitude(MAX_MOUSE_DELTA_MAG);
        mouseVelocity = delta.fromX();
    }

    private void updateTowerVelocity(float lerpAmount) {
        towerVelocity = towerVelocity.lerp(mouseVelocity, lerpAmount);
    }

    private Tower getTower() {
        return dragAndDrop.getDragData().getCard().getSupplier().create(new ModelCoordinate(Vector3f.ZERO));
    }
}
