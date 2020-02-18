package by.epam.jwd.yakovlev.textparser.service;

import by.epam.jwd.yakovlev.textparser.service.impl.ServiceLogicImpl;

public class ServiceFactory {

    private static ServiceFactory INSTANCE = new ServiceFactory();

    private ServiceLogic serviceLogic = new ServiceLogicImpl();

    private ServiceFactory() {
    }

    public static ServiceFactory getINSTANCE() {
        return INSTANCE;
    }

    public ServiceLogic getServiceLogic() {
        return serviceLogic;
    }
}
