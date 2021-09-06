package andioopp.gfx.javafx;

import javafx.geometry.Point2D;
import javafx.scene.image.Image;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.testfx.framework.junit.ApplicationTest;

import static org.junit.Assert.*;

public class FxSpriteTest extends ApplicationTest {

    private final String SPRITE_PATH = "gobbo.png";
    private final Point2D SPRITE_POSITION = new Point2D(7, 77);
    private FxSprite sprite;

    @Before
    public void setUp() {
        sprite = FxSprite.load(SPRITE_PATH, SPRITE_POSITION);
    }

    @Test
    public void loadReturnsSprite() {
        FxSprite sprite = FxSprite.load(SPRITE_PATH, SPRITE_POSITION);
        assertNotNull(sprite);
    }

    @Test
    public void getImageReturnsImage() {
        Image image = sprite.getImage();
        assertNotNull(image);
    }

    @Test
    public void getPositionReturnsExpectedPosition() {
        Point2D position = sprite.getPosition();
        assertEquals(position, SPRITE_POSITION);
    }
}