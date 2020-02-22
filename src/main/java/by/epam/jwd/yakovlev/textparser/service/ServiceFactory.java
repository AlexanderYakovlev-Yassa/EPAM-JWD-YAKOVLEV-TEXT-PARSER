package by.epam.jwd.yakovlev.textparser.service;

import by.epam.jwd.yakovlev.textparser.parser.Parser;
import by.epam.jwd.yakovlev.textparser.parser.impl.ParserImpl;
import by.epam.jwd.yakovlev.textparser.service.impl.ServiceLogicImpl;

public class ServiceFactory {

    private static ServiceFactory INSTANCE = new ServiceFactory();

    private ServiceLogic serviceLogic = new ServiceLogicImpl();
    private Parser parser = new ParserImpl();

    private ServiceFactory() {
    }

    public static ServiceFactory getINSTANCE() {
        return INSTANCE;
    }

    public ServiceLogic getServiceLogic() {
        return serviceLogic;
    }

    public Parser getParser() {
        return parser;
    }
}
