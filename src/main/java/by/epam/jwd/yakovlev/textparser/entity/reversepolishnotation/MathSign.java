package by.epam.jwd.yakovlev.textparser.entity.reversepolishnotation;

import java.util.regex.Pattern;

public enum MathSign implements RPNCompatible {

    RIGHT_PARENTHESES(0, "[)]"),
    LEFT_PARENTHESES(1, "[(]"),
    BITWISE_AND(2, "[&]"),
    BITWISE_OR(2, "[|]"),
    BITWISE_XOR(2, "[\\^]"),
    BIT_ADDITION(2, "[~]"),
    RIGHT_BIT_SHIFT(2, "[>]{2}"),
    LEFT_BIT_SHIFT(2, "[<]{2}"),
    ADDITION(3, "[+]"),
    SUBTRACTION(3, "[-]"),
    MULTIPLICATION(4, "[*]"),
    DIVISION(4, "[/]"),
    INTEGER_NUMBER(5, "[\\d]+");

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
