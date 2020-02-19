package by.epam.jwd.yakovlev.textparser.service.impl;

import by.epam.jwd.yakovlev.textparser.entity.TextComponent;
import by.epam.jwd.yakovlev.textparser.entity.RegularTextComponent;
import by.epam.jwd.yakovlev.textparser.entity.TextComponentTypesEnum;
import by.epam.jwd.yakovlev.textparser.entity.TextSymbol;
import by.epam.jwd.yakovlev.textparser.service.ServiceParsers;

import javax.naming.OperationNotSupportedException;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ServiceParsersImpl implements ServiceParsers {

    public static final ServiceTypeValidator TYPE_VALIDATOR = new ServiceTypeValidator();

    public ArrayList<String> parseText(String text, TextComponentTypesEnum type) {

        ArrayList<String> stringList = new ArrayList<>();

        switch (type) {
            case TEXT: {
                stringList = parseText(text, TypeEnum.PARAGRAPH.getPattern());
                break;
            }
            case PARAGRAPH: {
                stringList = parseText(text, TypeEnum.SENTENCE.getPattern());
                break;
            }
            case SYMBOL: {
                stringList = parseText(text, TypeEnum.TOKEN.getPattern());
                break;
            }
            /*case TOKEN: {
                stringList = parseText(text, TypeEnum.TOKEN_COMPONENTS.getPattern());
                break;
            }*/
            case WORD: {
                stringList = parseText(text, TypeEnum.SYMBOL.getPattern());
            }
        }

        return stringList;
    }

    TextComponent buildSentence(String string) {

        TextComponent sentence = new RegularTextComponent(TextComponentTypesEnum.SENTENCE);

        Matcher tokenMatcher = TypeEnum.TOKEN.getPattern().matcher(string);
        String temporaryChainOfSymbol = "";
        int startTemporaryChainOfSymbol = 0;

        while (tokenMatcher.find(startTemporaryChainOfSymbol)) {

            temporaryChainOfSymbol = string.substring(startTemporaryChainOfSymbol, tokenMatcher.start());
            startTemporaryChainOfSymbol = tokenMatcher.end();
            addChainOfSymbols(sentence, temporaryChainOfSymbol);
            try {
                sentence.append(buildToken(tokenMatcher.group()));
            } catch (OperationNotSupportedException e) {
            }
        }

        if (string.length() > startTemporaryChainOfSymbol) {
            temporaryChainOfSymbol = string.substring(startTemporaryChainOfSymbol);
            addChainOfSymbols(sentence, temporaryChainOfSymbol);
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

        Matcher wordMatcher = TypeEnum.WORD.getPattern().matcher(string);
        String temporaryChainOfSymbol = "";
        int startTemporaryChainOfSymbol = 0;

        while (wordMatcher.find(startTemporaryChainOfSymbol)) {

            temporaryChainOfSymbol = string.substring(startTemporaryChainOfSymbol, wordMatcher.start());
            startTemporaryChainOfSymbol = wordMatcher.end();
            addChainOfSymbols(regularToken, temporaryChainOfSymbol);
            try {
                regularToken.append(buildWord(wordMatcher.group()));
            } catch (OperationNotSupportedException e) {
            }
        }

        if (string.length() > startTemporaryChainOfSymbol) {
            temporaryChainOfSymbol = string.substring(startTemporaryChainOfSymbol);
            addChainOfSymbols(regularToken, temporaryChainOfSymbol);
        }

        return regularToken;
    }

    TextComponent buildEquation(String string) {

        if (!TYPE_VALIDATOR.isTypeEquation(string)) {
            return null;
        }

        TextComponent equation = new RegularTextComponent(TextComponentTypesEnum.EQUATION);

        Matcher numberMatcher = TypeEnum.NUMBER.getPattern().matcher(string);
        String temporaryChainOfSymbol = "";
        int startTemporaryChainOfSymbol = 0;

        while (numberMatcher.find(startTemporaryChainOfSymbol)) {

            temporaryChainOfSymbol = string.substring(startTemporaryChainOfSymbol, numberMatcher.start());
            startTemporaryChainOfSymbol = numberMatcher.end();
            addChainOfSymbols(equation, temporaryChainOfSymbol);
            try {
                equation.append(buildNumber(numberMatcher.group()));
            } catch (OperationNotSupportedException e) {
            }
        }

        if (string.length() > startTemporaryChainOfSymbol) {
            temporaryChainOfSymbol = string.substring(startTemporaryChainOfSymbol);
            addChainOfSymbols(equation, temporaryChainOfSymbol);
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

        TextSymbol textSymbol = TextSymbol.getSymbol(string.charAt(0));

        return textSymbol;
    }

    private ArrayList<String> parseText(String text, Pattern pattern) {

        Matcher matcher = pattern.matcher(text);
        ArrayList<String> stringList = new ArrayList<>();

        while (matcher.find()) {
            for (int i = 1; i <= matcher.groupCount(); i++) {
                if (matcher.group(i).length() > 0) {
                    stringList.add(matcher.group(i));
                }
            }
        }

        return stringList;
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
}
