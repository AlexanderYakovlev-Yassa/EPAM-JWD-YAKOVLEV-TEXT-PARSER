package by.epam.jwd.yakovlev.textparser.service;

import by.epam.jwd.yakovlev.textparser.entity.TextComponent;

import java.util.ArrayList;

public interface ServiceParsers {

    ArrayList<TextComponent> parseTextToParagraphs(String text);

    ArrayList<TextComponent> parseParagraphToSentences(String text);

    ArrayList<TextComponent> parseSentenceToTokens(String text);

    ArrayList<TextComponent> parseTokenToWords(String text);

    ArrayList<TextComponent> parseWordToSymbols(String text);

    ArrayList<TextComponent> parseMathEquationToSymbols(String text);
}
