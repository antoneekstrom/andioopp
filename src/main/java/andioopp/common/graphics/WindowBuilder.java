package andioopp.common.graphics;

import andioopp.common.math.Dimension;

/**
 * Builds {@link Window} objects.
 * @param <W> Describes the type of window that is created by the builder
 */
public interface WindowBuilder<W extends Window<?>> {
    /**
     * Creates the {@link Window}.
     * @return The {@link Window}
     */
    W build();

    /**
     * Sets the size of the {@link Window}.
     * @param size Size as a {@link Dimension}
     */
    void setSize(Dimension size);

    /**
     * Sets the title of the {@link Window}.
     * @param title Title of the {@link Window}
     */
    void setTitle(String title);

    /**
     * Sets the icon of the {@link Window}.
     * @param path Location of the image file
     */
    void setIcon(String path);

    /**
     * Sets if the window should be resizable.
     * @param isResizable If the window should be resizable
     */
    void setResizable(boolean isResizable);
}
