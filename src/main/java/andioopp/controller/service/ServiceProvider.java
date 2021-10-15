package andioopp.controller.service;

import andioopp.common.storage.ListFactory;

/**
 * Provides a way to access services.
 *
 * @author Anton Ekström
 */
public class ServiceProvider {

    private final ListFactory listFactory;
    private final MouseService mouseService;

    public ServiceProvider(ListFactory listFactory, MouseService mouseService) {
        this.listFactory = listFactory;
        this.mouseService = mouseService;
    }

    public ListFactory getListFactory() {
        return listFactory;
    }

    public MouseService mouseService() { return mouseService; }
}
