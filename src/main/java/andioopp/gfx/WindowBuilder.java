package andioopp.gfx;

import java.awt.*;

public interface WindowBuilder<W extends Window<?>> {
    W build();
    void setSize(Dimension size);
    void setTitle(String title);
}
