package by.epam.jwd.yakovlev.textparser.entity.reversepolishnotation;

public class RPNNumber implements RPNCompatible{
    int number;

    public RPNNumber(int number) {
        this.number = number;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }
}
