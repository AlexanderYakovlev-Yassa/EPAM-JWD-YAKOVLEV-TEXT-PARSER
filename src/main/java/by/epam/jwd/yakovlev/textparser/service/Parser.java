package by.epam.jwd.yakovlev.textparser.service;

import by.epam.jwd.yakovlev.textparser.entity.RegularTextComponent;
import by.epam.jwd.yakovlev.textparser.entity.SymbolTextComponent;
import by.epam.jwd.yakovlev.textparser.entity.TextComponent;
import by.epam.jwd.yakovlev.textparser.entity.TypeEnum;
import by.epam.jwd.yakovlev.textparser.service.impl.ServiceLogicImpl;
import by.epam.jwd.yakovlev.textparser.validator.Validator;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;

public class Parser {

    private static Validator VALIDATOR = new Validator();
    private static final Logger logger = LogManager.getLogger(ServiceLogicImpl.class);

    public static final int ZERO = 0;

    private TypeEnum type;
    private Parser nextParser;

    public Parser(TypeEnum type, Parser nextParser) {
        this.type = type;
        this.nextParser = nextParser;
    }

    public Parser(TypeEnum type) {
        this.type = type;
        this.nextParser = null;
    }

    public TextComponent parse(String rawText){

        if (rawText == null) {
            throw new UnsupportedOperationException("Can't parse null!!!");
        }

        TextComponent component;

        if (type == TypeEnum.SYMBOL || rawText.length() == 1){

            component = new SymbolTextComponent(rawText.charAt(ZERO));
            return component;
        }

        component = new RegularTextComponent(type);

        if (nextParser == null){

            for (char ch : rawText.toCharArray()) {
                component.appendComponentPart(new SymbolTextComponent(ch));
            }
            return component;
        }

        if (VALIDATOR.isConvertibleIntoType(type, rawText)) {
            for (String s : splitInto(nextParser.type, rawText)) {
                component.appendComponentPart(nextParser.parse(s));
            }
        } else {
            nextParser.parse(rawText);
        }

        return component;
    }

    public TypeEnum getType() {
        return type;
    }

    public Parser getNextParser() {
        return nextParser;
    }

    private static List<String> splitInto(TypeEnum type, String sourceSnippet) {

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
