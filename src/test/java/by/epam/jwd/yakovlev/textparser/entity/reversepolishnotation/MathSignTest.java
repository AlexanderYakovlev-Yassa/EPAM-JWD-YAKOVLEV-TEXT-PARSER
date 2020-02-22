package by.epam.jwd.yakovlev.textparser.entity.reversepolishnotation;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class MathSignTest {

    private static final ReversePolishNotation RPN = new ReversePolishNotation();

    @Test
    public void recogniseMathSignPositiveTest() {

        String rawSign = "/";

        MathSign actualMathSign = MathSign.recogniseMathSign(rawSign);
        Assert.assertNotNull(actualMathSign);
        Assert.assertEquals(actualMathSign, MathSign.DIVISION);

        rawSign = "*";
        actualMathSign = MathSign.recogniseMathSign(rawSign);
        Assert.assertNotNull(actualMathSign);
        Assert.assertEquals(actualMathSign, MathSign.MULTIPLICATION);

        rawSign = "^";
        actualMathSign = MathSign.recogniseMathSign(rawSign);
        Assert.assertNotNull(actualMathSign);
        Assert.assertEquals(actualMathSign, MathSign.BITWISE_XOR);
    }
}