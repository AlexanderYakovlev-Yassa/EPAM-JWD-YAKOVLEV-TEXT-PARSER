package by.epam.jwd.yakovlev.textparser.entity.reversepolishnotation;

public enum ActionSign implements RPNCompatible {

    RIGHT_PARENTHESES(0, ")"),
    LEFT_PARENTHESES(0, "("),
    BITWISE_AND(1, "&"),
    BITWISE_OR(1, "|"),
    BITWISE_XOR(1, "^"),
    BIT_ADDITION(1, "~"),
    RIGHT_BIT_SHIFT(1, ">>"),
    LEFT_BIT_SHIFT(1, "<<"),
    ADDITION(2, "+"),
    SUBTRACTION(2, "-"),
    MULTIPLICATION(3, "*"),
    DIVISION(3, "/");

    int priority;
    String symbol;

    ActionSign(int priority, String symbol) {
        this.priority = priority;
        this.symbol = symbol;
    }

    public int getPriority() {
        return priority;
    }

    public String getSymbol() {
        return symbol;
    }
}
