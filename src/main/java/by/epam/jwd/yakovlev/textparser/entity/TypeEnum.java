package by.epam.jwd.yakovlev.textparser.entity;

import java.util.regex.Pattern;

public enum TypeEnum {

    TEXT("[.]"),
    PARAGRAPH("[^\\n]+[\\n]"),
    SENTENCE("[\\w](([^\\.\\!\\?\\n])|([\\d]+[\\.][\\d]+))+[\\.\\!\\?]+"),
    TOKEN("[\\S]+"),
    WORD("([\\w]+)"),
    EQUATION("([\\d()|&+\\-\\*\\/<>^~]+)"),
    SYMBOL("[.]"),
    NUMBER("[\\d]+[\\.]?[\\d]*");

    Pattern pattern;

    TypeEnum(String regex) {
        this.pattern = Pattern.compile(regex);
    }

    public Pattern getPattern() {
        return pattern;
    }
}
