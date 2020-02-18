package by.epam.jwd.yakovlev.textparser.service.impl;


import by.epam.jwd.yakovlev.textparser.dao.DAOFactory;
import by.epam.jwd.yakovlev.textparser.dao.DAOLogic;
import by.epam.jwd.yakovlev.textparser.service.ServiceLogic;

public class ServiceLogicImpl implements ServiceLogic {

    private static DAOLogic DAO_LOGIC = DAOFactory.getINSTANCE().getDAO_LOGIC();

}
