package andioopp.service.infrastructure.graphics;

import andioopp.common.gfx.Window;
import andioopp.common.gfx.WindowBuilder;

import java.awt.*;

public class WindowingService<W extends Window<?>> {

    private final WindowBuilder<W> builder;

    public WindowingService(WindowBuilder<W> builder) {
        this.builder = builder;
    }

    public W createWindow() {
        WindowBuilder<W> builder = getBuilder();
        builder.setTitle("Example");
        builder.setSize(new Dimension(1280, 720));
        builder.setResizable(true);
        builder.setIcon("mario_icon.png");
        return builder.build();
    }

    private WindowBuilder<W> getBuilder() {
        return builder;
    }
}
