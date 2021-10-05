package andioopp.common.gfx.javafx;

import andioopp.common.gfx.Window;
import andioopp.common.observer.Observable;
import andioopp.common.observer.ObservableWithList;
import andioopp.common.observer.Observer;
import andioopp.common.storage.ArrayListFactory;
import andioopp.common.input.MouseData;
import andioopp.common.transform.Dimension;
import andioopp.common.transform.Vector3f;
import javafx.scene.canvas.Canvas;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 * {@link Window} implementation for JavaFX. Encapsulates a {@link Stage} and {@link Canvas} instance.
 */
public class FxWindow implements Window<FxRenderer> {

    private final Stage stage;
    private final Canvas canvas;

    private final Observable<MouseData, Observer<MouseData>> mouseObservable;

    public FxWindow(Stage stage, Canvas canvas) {
        this.stage = stage;
        this.canvas = canvas;

        mouseObservable = new ObservableWithList<>(new ArrayListFactory().create());
        stage.addEventHandler(MouseEvent.MOUSE_MOVED, (e) -> {
            Vector3f position = new Vector3f((float) e.getX(), (float) e.getY());
            mouseObservable.notifyObservers(new MouseData(position, MouseData.MouseEventType.MOVE));
        });
        stage.addEventHandler(MouseEvent.MOUSE_PRESSED, (e) -> {
            Vector3f position = new Vector3f((float) e.getX(), (float) e.getY());
            mouseObservable.notifyObservers(new MouseData(position, MouseData.MouseEventType.PRESS));
        });
        stage.addEventHandler(MouseEvent.MOUSE_RELEASED, (e) -> {
            Vector3f position = new Vector3f((float) e.getX(), (float) e.getY());
            mouseObservable.notifyObservers(new MouseData(position, MouseData.MouseEventType.RELEASE));
        });
    }

    @Override
    public Observable<MouseData, Observer<MouseData>> getMouseObservable() {
        return mouseObservable;
    }

    @Override
    public Observable<Dimension, Observer<Dimension>> getResizeObservable() {
        return null;
    }

    @Override
    public void setMaximized(boolean isMaximized) {
        getStage().setMaximized(isMaximized);
    }

    @Override
    public FxRenderer getRenderer() {
        return new FxRenderer(getCanvas().getGraphicsContext2D());
    }

    @Override
    public int getWidth() {
        return (int) stage.getWidth();
    }

    @Override
    public int getHeight() {
        return (int) stage.getHeight();
    }

    public Canvas getCanvas() {
        return canvas;
    }

    public Stage getStage() {
        return stage;
    }
}
