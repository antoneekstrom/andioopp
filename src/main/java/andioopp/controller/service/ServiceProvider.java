package andioopp.controller.service;

import andioopp.common.storage.ListFactory;

/**
 * Provides a way to access services.
 *
 * @author Anton Ekström
 */
public class ServiceProvider {

    private final ListFactory listFactory;

    public ServiceProvider(ListFactory listFactory) {
        this.listFactory = listFactory;
    }

    public ListFactory getListFactory() {
        return listFactory;
    }
}
