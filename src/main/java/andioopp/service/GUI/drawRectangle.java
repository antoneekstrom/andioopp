package andioopp.service.GUI;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class drawRectangle {
    private final float height;
    private final float width;
    private final float xPosition;
    private final float yPosition;
    private final Color color;

    public drawRectangle(float height, float width, float xPosition, float yPosition, Color color) {
        this.height = height;
        this.width = width;
        this.xPosition = xPosition;
        this.yPosition = yPosition;
        this.color = color;
    }


}
