package andioopp.view;

import andioopp.common.graphics.Sprite;

public abstract class DependentView<S extends Sprite<?>, T> implements View<S> {

    private final T dependency;

    public DependentView(T dependency) {
        this.dependency = dependency;
    }

    protected T getDependency() {
        return dependency;
    }
}
