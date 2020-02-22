package by.epam.jwd.yakovlev.textparser.dao.impl;

import by.epam.jwd.yakovlev.textparser.dao.DAOLogic;
import by.epam.jwd.yakovlev.textparser.dao.exception.DAOLogicException;
import by.epam.jwd.yakovlev.textparser.util.FileUtil;
import by.epam.jwd.yakovlev.textparser.util.exception.FileUtilException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class DAOLogicImpl implements DAOLogic {

    private final FileUtil FILE_UTIL = FileUtil.getInstance();
    private static final Logger logger = LogManager.getLogger(DAOLogicImpl.class);

    public String readTextFromSourceFile(String filePath) throws DAOLogicException {

        File file = new File(filePath);
        List<String> text = new ArrayList<>();
        logger.info("File " + filePath + " has been successfully read");
        try {
            text = FILE_UTIL.readFile(file);
        } catch (FileUtilException e) {
            logger.info("File " + filePath + " has not found");
            throw new DAOLogicException("Such file not found.", e);
        }

        StringBuilder sb = new StringBuilder();

        for (String s : text){
            sb.append(s + "\n");
        }

        return sb.toString();
    }
}
