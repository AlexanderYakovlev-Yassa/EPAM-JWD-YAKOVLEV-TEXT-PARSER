package by.epam.jwd.yakovlev.textparser.parser.impl;

import by.epam.jwd.yakovlev.textparser.parser.Parser;

import by.epam.jwd.yakovlev.textparser.dao.DAOFactory;
import by.epam.jwd.yakovlev.textparser.dao.DAOLogic;
import by.epam.jwd.yakovlev.textparser.dao.exception.DAOLogicException;
import by.epam.jwd.yakovlev.textparser.entity.RegularTextComponent;
import by.epam.jwd.yakovlev.textparser.entity.TextComponent;
import by.epam.jwd.yakovlev.textparser.entity.SymbolTextComponent;
import by.epam.jwd.yakovlev.textparser.entity.TypeEnum;
import by.epam.jwd.yakovlev.textparser.entity.nextypesolver.NextTypeSolver;
import by.epam.jwd.yakovlev.textparser.service.impl.ServiceLogicImpl;
import by.epam.jwd.yakovlev.textparser.validator.Validator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;


public class ParserImpl implements Parser {

    private static Validator VALIDATOR = new Validator();
    private static final Logger logger = LogManager.getLogger(ServiceLogicImpl.class);

    public static final int ZERO = 0;

    @Override
    public TextComponent buildComponentOf(TypeEnum type, String rawText, NextTypeSolver solver) {

        TextComponent component;
        if (type == TypeEnum.SYMBOL) {
            component = new SymbolTextComponent(rawText.charAt(0));
            return component;
        }

        TypeEnum nextType = solver.getNextType(type, rawText);

        if (VALIDATOR.isConvertibleIntoType(type, rawText)) {

            component = new RegularTextComponent(type);

            List<String> rawStringsList = splitInto(nextType, rawText);
            List<TextComponent> innerComponentsList = component.getComponentPartsList();


            for (String rs : rawStringsList) {
                innerComponentsList.add(buildComponentOf(nextType, rs, solver));
            }
        } else {
            component = buildComponentOf(nextType, rawText, solver);
        }

        return component;
    }

    @Override
    public List<String> splitInto(TypeEnum type, String sourceSnippet) {

        List<String> newSnippetsList = new ArrayList<>();

        if (sourceSnippet == null) {
            return null;
        }
        if (sourceSnippet.length() == 0) {
            return newSnippetsList;
        }
        if (type == TypeEnum.TEXT) {
            if (!VALIDATOR.isConvertibleIntoType(TypeEnum.TEXT, sourceSnippet)) {
                return newSnippetsList;
            }
            newSnippetsList.add(sourceSnippet);
            return newSnippetsList;
        }
        if (type == TypeEnum.SYMBOL) {
            for (char ch : sourceSnippet.toCharArray()) {
                newSnippetsList.add(String.valueOf(ch));
            }
            return newSnippetsList;
        }

        Matcher matcher = type.getPattern().matcher(sourceSnippet);
        String headChainOfSymbol;
        String tailChainOfSymbol;
        int charsChainStart = ZERO;

        while (matcher.find(charsChainStart)) {

            headChainOfSymbol = sourceSnippet.substring(charsChainStart, matcher.start());
            charsChainStart = matcher.end();
            newSnippetsList.addAll(splitInto(TypeEnum.SYMBOL, headChainOfSymbol));

            newSnippetsList.add(matcher.group());
        }

        if (sourceSnippet.length() > charsChainStart) {
            tailChainOfSymbol = sourceSnippet.substring(charsChainStart);
            newSnippetsList.addAll(splitInto(TypeEnum.SYMBOL, tailChainOfSymbol));
        }

        return newSnippetsList;
    }
}
