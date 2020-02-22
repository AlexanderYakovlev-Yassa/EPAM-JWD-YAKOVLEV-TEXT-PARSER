package by.epam.jwd.yakovlev.textparser.dao;

import by.epam.jwd.yakovlev.textparser.dao.exception.DAOLogicException;

public interface DAOLogic {

    public String readTextFromSourceFile(String filePath) throws DAOLogicException;
}
