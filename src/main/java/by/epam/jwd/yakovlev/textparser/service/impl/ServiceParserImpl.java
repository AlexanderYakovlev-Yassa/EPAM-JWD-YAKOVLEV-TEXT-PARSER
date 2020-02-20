package by.epam.jwd.yakovlev.textparser.service.impl;

import by.epam.jwd.yakovlev.textparser.entity.TextComponent;
import by.epam.jwd.yakovlev.textparser.entity.RegularTextComponent;
import by.epam.jwd.yakovlev.textparser.entity.TextComponentTypesEnum;
import by.epam.jwd.yakovlev.textparser.entity.TextSymbol;
import by.epam.jwd.yakovlev.textparser.service.ServiceParser;

import javax.naming.OperationNotSupportedException;
import java.util.ArrayList;
import java.util.regex.Matcher;

public class ServiceParserImpl implements ServiceParser {

    public static final ServiceTypeValidator TYPE_VALIDATOR = new ServiceTypeValidator();
    public static final String EMPTY_STRING = "";
    public static final int ZERO = 0;

    TextComponent buildText(String string) {

        TextComponent text = new RegularTextComponent(TextComponentTypesEnum.TEXT);

        try {
            text.getComponents().addAll(parseToTextComponentsList(TypeEnum.PARAGRAPH, string));
        } catch (OperationNotSupportedException e) {
        }

        return text;
    }

    TextComponent buildParagraph(String string) {

        if (!TYPE_VALIDATOR.isTypeParagraph(string)) {
            return null;
        }

        TextComponent paragraph = new RegularTextComponent(TextComponentTypesEnum.PARAGRAPH);

        try {
            paragraph.getComponents().addAll(parseToTextComponentsList(TypeEnum.SENTENCE, string));
        } catch (OperationNotSupportedException e) {
        }

        return paragraph;
    }

    TextComponent buildSentence(String string) {

        if (!TYPE_VALIDATOR.isTypeSentence(string)) {
            return null;
        }

        TextComponent sentence = new RegularTextComponent(TextComponentTypesEnum.SENTENCE);

        try {
            sentence.getComponents().addAll(parseToTextComponentsList(TypeEnum.TOKEN, string));
        } catch (OperationNotSupportedException e) {
        }

        return sentence;
    }

    TextComponent buildToken(String string) {

        if (!TYPE_VALIDATOR.isTypeToken(string)) {
            return null;
        }

        TextComponent textComponent = new RegularTextComponent(TextComponentTypesEnum.TOKEN);

        if (TYPE_VALIDATOR.isTypeEquation(string)) {
            textComponent = buildEquation(string);
            return textComponent;
        }

        textComponent = buildRegularToken(string);

        return textComponent;
    }

    TextComponent buildRegularToken(String string) {

        if (!TYPE_VALIDATOR.isTypeToken(string)) {
            return null;
        }

        TextComponent regularToken = new RegularTextComponent(TextComponentTypesEnum.TOKEN);

        try {
            regularToken.getComponents().addAll(parseToTextComponentsList(TypeEnum.WORD, string));
        } catch (OperationNotSupportedException e) {
        }

        return regularToken;
    }

    TextComponent buildEquation(String string) {

        if (!TYPE_VALIDATOR.isTypeEquation(string)) {
            return null;
        }

        TextComponent equation = new RegularTextComponent(TextComponentTypesEnum.EQUATION);

        try {
            equation.getComponents().addAll(parseToTextComponentsList(TypeEnum.NUMBER, string));
        } catch (OperationNotSupportedException e) {
        }

        return equation;
    }

    TextComponent buildNumber(String string) {

        if (!TYPE_VALIDATOR.isTypeNumber(string)) {
            return null;
        }

        TextComponent number = new RegularTextComponent(TextComponentTypesEnum.NUMBER);

        addChainOfSymbols(number, string);

        return number;
    }

    TextComponent buildWord(String string) {

        if (!TYPE_VALIDATOR.isTypeWord(string)) {
            return null;
        }

        TextComponent word = new RegularTextComponent(TextComponentTypesEnum.WORD);

        addChainOfSymbols(word, string);

        return word;
    }

    TextComponent buildSymbol(String string) {

        if (!TYPE_VALIDATOR.isTypeSymbol(string)) {
            return null;
        }

        TextSymbol textSymbol = TextSymbol.getSymbol(string.charAt(ZERO));

        return textSymbol;
    }

    private ArrayList<TextComponent> parseToTextComponentsList(TypeEnum type, String string) {

        ArrayList<TextComponent> componentList = new ArrayList<>();

        if (type == null || string == null){
            return componentList;
        }

        TextComponent textComponent = null;
        Matcher matcher = type.getPattern().matcher(string);
        String chainOfSymbol;
        int charsChainStart = ZERO;

        while (matcher.find(charsChainStart)) {

            chainOfSymbol = string.substring(charsChainStart, matcher.start());
            charsChainStart = matcher.end();
            componentList.addAll(buildTextSymbolsChain(chainOfSymbol));

            switch (type) {
                case EQUATION: {
                    textComponent = buildEquation(matcher.group());
                    System.out.println("***");
                    System.out.println(componentList.size());
                    break;
                }
                case TOKEN: {
                    textComponent = buildToken(matcher.group());
                    break;
                }
                case SENTENCE: {
                    textComponent = buildSentence(matcher.group());
                    break;
                }
                case PARAGRAPH: {
                    textComponent = buildParagraph(matcher.group());
                    break;
                }
                case NUMBER: {
                    textComponent = buildNumber(matcher.group());
                    break;
                }
                case WORD: {
                    textComponent = buildWord(matcher.group());
                    break;
                }
                default: {
                    return new ArrayList<>();
                }
            }
            componentList.add(textComponent);
        }

        if (string.length() > charsChainStart) {
            chainOfSymbol = string.substring(charsChainStart);
            componentList.addAll(buildTextSymbolsChain(chainOfSymbol));
        }

        return componentList;
    }

    private void addChainOfSymbols(TextComponent textComponent, String string) {
        char[] charArray = string.toCharArray();
        for (char ch : charArray) {
            try {
                textComponent.append(TextSymbol.getSymbol(ch));
            } catch (OperationNotSupportedException e) {
            }
        }
    }

    public ArrayList<TextSymbol> buildTextSymbolsChain(String string) {

        ArrayList<TextSymbol> chain = new ArrayList<>();

        if (string == null || string == EMPTY_STRING) {
            return chain;
        }

        char[] charArray = string.toCharArray();

        for (char ch : charArray) {

            chain.add(TextSymbol.getSymbol(ch));
        }

        return chain;
    }
}
