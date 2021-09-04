package andioopp.gfx;

import java.awt.*;

public interface WindowBuilder {
    Window build();
    void setSize(Dimension size);
    void setTitle(String title);
}
