package andioopp.view.views.gui;

import andioopp.common.graphics.Renderer;
import andioopp.common.graphics.Sprite;
import andioopp.common.math.dimension.Dimension;
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

public class TowerDragMouseView implements View<Model>, Observer<MouseMoveEvent> {

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
            Vector3f position = mouse.sub(size.halved().toVector().fromX());

            // double angle = getSpriteRotationAngle(sprite.getHeight());
            // renderer.rotate(mouse, (float) angle);

            renderer.drawSprite(sprite, position, size);
        }
    }

    @Override
    public void onEvent(MouseMoveEvent e) {
        setMouseVelocity(e);
    }

    private float getSpriteRotationAngle(float spriteHeight) {
        float nextAngle = (float) Math.toDegrees(getAngleFromVelocity(spriteHeight));
        updateTowerVelocity();
        previousAngle = nextAngle;
        return previousAngle;
    }

    private float getAngleFromVelocity(float centerOfMassHeight) {
        Vector3f velocityDiff = towerVelocity.sub(mouseVelocity);
        float opposite = velocityDiff.magnitude();
        float dir = Math.signum(velocityDiff.getX());
        double angle = Math.abs(Math.tan(opposite / centerOfMassHeight));
        double maxAngle = Math.PI / 2;
        angle = Math.min(angle, maxAngle);
        angle *= -dir;
        return (float) angle;
    }

    private void setMouseVelocity(MouseMoveEvent e) {
        mouseVelocity = (e.getMouseDelta().scale(70f)).fromX();
    }

    private void updateTowerVelocity() {
        towerVelocity = towerVelocity.lerp(mouseVelocity, 0.001f);
    }

    private Tower getTower() {
        return dragAndDrop.getDragData().getCard().getSupplier().create(new ModelCoordinate(Vector3f.ZERO));
    }
}
