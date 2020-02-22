package by.epam.jwd.yakovlev.textparser.entity.reversepolishnotation;

import java.util.regex.Pattern;

public enum MathSign implements RPNCompatible {

    RIGHT_PARENTHESES(0, "[)]"),
    LEFT_PARENTHESES(0, "[(]"),
    BITWISE_AND(1, "[&]"),
    BITWISE_OR(1, "[|]"),
    BITWISE_XOR(1, "[\\^]"),
    BIT_ADDITION(1, "[~]"),
    RIGHT_BIT_SHIFT(1, "[>]{2}"),
    LEFT_BIT_SHIFT(1, "[<]{2}"),
    ADDITION(2, "[+]"),
    SUBTRACTION(2, "[-]"),
    MULTIPLICATION(3, "[*]"),
    DIVISION(3, "[/]"),
    INTEGER_NUMBER(4, "[\\d]+");

    int priority;
    Pattern pattern;

    MathSign(int priority, String regex) {
        this.priority = priority;
        this.pattern = Pattern.compile(regex);
    }

    public int getPriority() {
        return priority;
    }

    public Pattern getPattern() {
        return pattern;
    }

    public static MathSign recogniseMathSign(String rawString){

        for (MathSign ms: MathSign.values()){

            if (ms.pattern.matcher(rawString).matches()){
                return ms;
            }
        }

        return null;
    }
}
