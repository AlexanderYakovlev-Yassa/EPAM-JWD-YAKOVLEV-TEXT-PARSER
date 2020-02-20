package by.epam.jwd.yakovlev.textparser.service.impl;

import by.epam.jwd.yakovlev.textparser.entity.*;
import by.epam.jwd.yakovlev.textparser.service.ServiceParser;

import java.util.ArrayList;
import java.util.regex.Matcher;

public class ServiceParserImpl implements ServiceParser {

    public static final ServiceTypeValidator TYPE_VALIDATOR = new ServiceTypeValidator();
    public static final String EMPTY_STRING = "";
    public static final int ZERO = 0;

    TextComponent buildText(String string) {

        TextComponent text = new RegularTextComponent(TypeEnum.TEXT);

        switch (getAppropriateType(string)) {
            case SYMBOL: {
                text.getComponentList().add(buildSymbol(string));
                return text;
            }
            case PARAGRAPH: {
                text.getComponentList().add(buildParagraph(string));
                return text;
            }
            case SENTENCE: {
                text.getComponentList().add(buildSentence(string));
                return text;
            }
            case TOKEN: {
                text.getComponentList().add(buildToken(string));
                return text;
            }
        }

        text.getComponentList().addAll(parseToTextComponentsList(TypeEnum.PARAGRAPH, string));

        return text;
    }

    TextComponent buildParagraph(String string) {

        if (!TYPE_VALIDATOR.isTypeParagraph(string)) {
            return null;
        }

        TextComponent paragraph = new RegularTextComponent(TypeEnum.PARAGRAPH);

        paragraph.getComponentList().addAll(parseToTextComponentsList(TypeEnum.SENTENCE, string));

        return paragraph;
    }

    TextComponent buildSentence(String string) {

        if (!TYPE_VALIDATOR.isTypeSentence(string)) {
            return null;
        }

        TextComponent sentence = new RegularTextComponent(TypeEnum.SENTENCE);

        sentence.getComponentList().addAll(parseToTextComponentsList(TypeEnum.TOKEN, string));

        return sentence;
    }

    TextComponent buildToken(String string) {

        if (!TYPE_VALIDATOR.isTypeToken(string)) {
            return null;
        }

        TextComponent textComponent;

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

        TextComponent regularToken = new RegularTextComponent(TypeEnum.TOKEN);

        regularToken.getComponentList().addAll(parseToTextComponentsList(TypeEnum.WORD, string));

        return regularToken;
    }

    TextComponent buildEquation(String string) {

        if (!TYPE_VALIDATOR.isTypeEquation(string)) {
            return null;
        }

        TextComponent equation = new RegularTextComponent(TypeEnum.EQUATION);

        equation.getComponentList().addAll(parseToTextComponentsList(TypeEnum.NUMBER, string));

        return equation;
    }

    TextComponent buildNumber(String string) {

        if (!TYPE_VALIDATOR.isTypeNumber(string)) {
            return null;
        }

        TextComponent number = new RegularTextComponent(TypeEnum.NUMBER);

        number.getComponentList().addAll(buildTextSymbolsChain(string));

        return number;
    }

    TextComponent buildWord(String string) {

        if (!TYPE_VALIDATOR.isTypeWord(string)) {
            return null;
        }

        TextComponent word = new RegularTextComponent(TypeEnum.WORD);

        word.getComponentList().addAll(buildTextSymbolsChain(string));

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

        if (type == null || string == null) {
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

    private ArrayList<TextSymbol> buildTextSymbolsChain(String string) {

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

    private TypeEnum getAppropriateType(String string) {

        if (string.length() == 1){
            return TypeEnum.SYMBOL;
        }
        if (TYPE_VALIDATOR.isTypeParagraph(string)) {
            return TypeEnum.PARAGRAPH;
        }
        if (TYPE_VALIDATOR.isTypeSentence(string)) {
            return TypeEnum.SENTENCE;
        }
        if (TYPE_VALIDATOR.isTypeToken(string)) {
            return TypeEnum.TOKEN;
        }


        return TypeEnum.TEXT;
    }
}
