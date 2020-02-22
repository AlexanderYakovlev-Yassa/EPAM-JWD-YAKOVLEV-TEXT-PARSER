package by.epam.jwd.yakovlev.textparser.entity;

import java.util.regex.Pattern;

public enum TypeEnum {

    TEXT("[\\w\\W]+"),
    PARAGRAPH("[^\\n]+[\\n]"),
    SENTENCE("[\\w](([^\\.\\!\\?\\n])|([\\d]+[\\.][\\d]+))+[\\.\\!\\?]+"),
    TOKEN("[\\S]+"),
    WORD("([\\w]+)"),
    EQUATION("[\\d()|&+\\-\\*\\/<>^~]+"),
    INTEGER_NUMBER ("[\\d]+"),
    FLOAT_POINT_NUMBER("[\\d]+[\\.]?[\\d]*"),
    MATH_SIGN("[()|&+\\-\\*\\/<>^~]"),
    SYMBOL("[\\w\\W]");

    Pattern pattern;

    TypeEnum(String regex) {
        this.pattern = Pattern.compile(regex);
    }

    public Pattern getPattern() {
        return pattern;
    }
}
