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
    private FxSprite sprite;

    @Before
    public void setUp() {
        sprite = FxSprite.load(SPRITE_PATH);
    }

    @Test
    public void loadShouldReturnSprite() {
        FxSprite sprite = FxSprite.load(SPRITE_PATH);
        assertNotNull(sprite);
    }

    @Test
    public void getImageShouldReturnImage() {
        Image image = sprite.getImage();
        assertNotNull(image);
    }
}