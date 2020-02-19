package by.epam.jwd.yakovlev.textparser.service.impl;

import java.util.regex.Pattern;

public enum TypeEnum {

    PARAGRAPH("[^\\n]+"),
    //SENTENCE("([^\\n]([\\d][\\.][\\d])*([^\\.\\!\\?]))+[\\.\\!\\?]"),

    SENTENCE("([^\\.\\!\\?]+[\\.\\!\\?])"),
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