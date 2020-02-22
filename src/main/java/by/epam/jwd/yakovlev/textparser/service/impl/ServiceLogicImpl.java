package by.epam.jwd.yakovlev.textparser.service.impl;

import by.epam.jwd.yakovlev.textparser.dao.DAOFactory;
import by.epam.jwd.yakovlev.textparser.dao.DAOLogic;
import by.epam.jwd.yakovlev.textparser.dao.exception.DAOLogicException;
import by.epam.jwd.yakovlev.textparser.entity.TextComponent;
import by.epam.jwd.yakovlev.textparser.entity.TypeEnum;
import by.epam.jwd.yakovlev.textparser.entity.comparator.ByNumberOfSentence;
import by.epam.jwd.yakovlev.textparser.entity.comparator.ByNumberOfWord;
import by.epam.jwd.yakovlev.textparser.entity.comparator.ByWordLength;
import by.epam.jwd.yakovlev.textparser.service.ServiceLogic;

import java.util.Comparator;
import java.util.List;

public class ServiceLogicImpl implements ServiceLogic {

    private static DAOLogic DAO_LOGIC = DAOFactory.getINSTANCE().getDAO_LOGIC();

    @Override
    public List<TextComponent> paragraphsSortedByNumberOfSentences(TextComponent text) {

        List<TextComponent> sortedList = text.getComponentsOfType(TypeEnum.PARAGRAPH);
        Comparator<TextComponent> componentComparator = new ByNumberOfSentence();
        sortedList.sort(componentComparator);

        return sortedList;
    }

    @Override
    public List<TextComponent> wordsSortedByLength(TextComponent text) {

        List<TextComponent> sortedList = text.getComponentsOfType(TypeEnum.WORD);
        Comparator<TextComponent> componentComparator = new ByWordLength();
        sortedList.sort(componentComparator);

        return sortedList;
    }

    @Override
    public List<TextComponent> sentencesSortedByNumberOfWords(TextComponent text) {
        List<TextComponent> sortedList = text.getComponentsOfType(TypeEnum.SENTENCE);
        Comparator<TextComponent> componentComparator = new ByNumberOfWord();
        sortedList.sort(componentComparator);

        return sortedList;
    }

    @Override
    public String loadText(String filePath) throws DAOLogicException {

        String text = DAO_LOGIC.readTextFromSourceFile(filePath);

        return text;
    }
}
