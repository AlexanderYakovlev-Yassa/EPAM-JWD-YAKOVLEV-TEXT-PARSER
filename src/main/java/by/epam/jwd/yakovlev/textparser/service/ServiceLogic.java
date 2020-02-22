package by.epam.jwd.yakovlev.textparser.service;

import by.epam.jwd.yakovlev.textparser.dao.exception.DAOLogicException;
import by.epam.jwd.yakovlev.textparser.entity.TextComponent;

import java.util.List;

public interface ServiceLogic {

    public String loadText(String filePath) throws DAOLogicException;

    public List<TextComponent> paragraphsSortedByNumberOfSentences(TextComponent text);
    public List<TextComponent> wordsSortedByLength(TextComponent text);
    public List<TextComponent> sentencesSortedByNumberOfWords(TextComponent text);
}
