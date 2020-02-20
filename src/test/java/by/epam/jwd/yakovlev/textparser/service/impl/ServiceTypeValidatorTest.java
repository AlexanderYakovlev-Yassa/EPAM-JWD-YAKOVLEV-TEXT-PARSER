package by.epam.jwd.yakovlev.textparser.service.impl;


import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class ServiceTypeValidatorTest {

    public static final ServiceTypeValidator VALIDATOR = new ServiceTypeValidator();

    @Test
    public void isTypeNumberPositiveTest() {

        String textVariant1 = "12.52";
        String textVariant2 = "12";
        String textVariant3 = "65456456.564564564";
        String textVariant4 = "0.52";
        String textVariant5 = "0";

        Assert.assertTrue(VALIDATOR.isTypeNumber(textVariant1));
        Assert.assertTrue(VALIDATOR.isTypeNumber(textVariant2));
        Assert.assertTrue(VALIDATOR.isTypeNumber(textVariant3));
        Assert.assertTrue(VALIDATOR.isTypeNumber(textVariant4));
        Assert.assertTrue(VALIDATOR.isTypeNumber(textVariant5));

    }

    @Test
    public void isTypeNumberNegativeTest() {

        String textVariant1 = "12.52a";
        String textVariant2 = ".";
        String textVariant3 = ".564564564";
        String textVariant4 = "";
        String textVariant5 = "5,6";

        Assert.assertFalse(VALIDATOR.isTypeNumber(textVariant1));
        Assert.assertFalse(VALIDATOR.isTypeNumber(textVariant2));
        Assert.assertFalse(VALIDATOR.isTypeNumber(textVariant3));
        Assert.assertFalse(VALIDATOR.isTypeNumber(textVariant4));
        Assert.assertFalse(VALIDATOR.isTypeNumber(textVariant5));
    }

    @Test
    public void isTypeSymbolPositiveTest() {

        String textVariant1 = "a";
        String textVariant2 = "3";
        String textVariant3 = ")";
        String textVariant4 = "?";
        String textVariant5 = "\n";

        Assert.assertTrue(VALIDATOR.isTypeSymbol(textVariant1));
        Assert.assertTrue(VALIDATOR.isTypeSymbol(textVariant2));
        Assert.assertTrue(VALIDATOR.isTypeSymbol(textVariant3));
        Assert.assertTrue(VALIDATOR.isTypeSymbol(textVariant4));
        Assert.assertTrue(VALIDATOR.isTypeSymbol(textVariant5));
    }

    @Test
    public void isTypeSymbolNegativeTest() {

        String textVariant1 = "";
        String textVariant2 = "63";
        String textVariant3 = ")\n";
        String textVariant4 = "??";
        String textVariant5 = "\n\t";

        Assert.assertFalse(VALIDATOR.isTypeSymbol(textVariant1));
        Assert.assertFalse(VALIDATOR.isTypeSymbol(textVariant2));
        Assert.assertFalse(VALIDATOR.isTypeSymbol(textVariant3));
        Assert.assertFalse(VALIDATOR.isTypeSymbol(textVariant4));
        Assert.assertFalse(VALIDATOR.isTypeSymbol(textVariant5));
    }

    @Test
    public void isTypeEquationPositiveTest() {

        String textVariant1 = "5|(1&2&(3|(4&(6^5|6&47)|3)|2)|1)";
        String textVariant2 = "(4^5|1&2<<(2|5>>2&71))|1200";
        String textVariant3 = "(~71&(2&3|(3|(2&1>>2|2)&2)|10&2))|78";
        String textVariant4 = "~6&9|(3&4)";
        String textVariant5 = "13<<2";

        Assert.assertTrue(VALIDATOR.isTypeEquation(textVariant1));
        Assert.assertTrue(VALIDATOR.isTypeEquation(textVariant2));
        Assert.assertTrue(VALIDATOR.isTypeEquation(textVariant3));
        Assert.assertTrue(VALIDATOR.isTypeEquation(textVariant4));
        Assert.assertTrue(VALIDATOR.isTypeEquation(textVariant5));
    }

    @Test
    public void isTypeEquationNegativeTest() {

        String textVariant1 = "";
        String textVariant2 = "63z";
        String textVariant3 = ")\n";
        String textVariant4 = "??";
        String textVariant5 = "\n\t";

        Assert.assertFalse(VALIDATOR.isTypeEquation(textVariant1));
        Assert.assertFalse(VALIDATOR.isTypeEquation(textVariant2));
        Assert.assertFalse(VALIDATOR.isTypeEquation(textVariant3));
        Assert.assertFalse(VALIDATOR.isTypeEquation(textVariant4));
        Assert.assertFalse(VALIDATOR.isTypeEquation(textVariant5));
    }

    @Test
    public void isTypeSentencePositiveTest() {

        String textVariant1 = "It has survived - not only (five) centuries.";
        String textVariant2 = "It has survived - not only (five) centuries?";
        String textVariant3 = "It has survived - not only (five) centuries!!!";
        String textVariant4 = "It has survived - not only (five) centuries?!";
        String textVariant5 = "It has survived - not 22.55 only (five) centuries?!";

        Assert.assertTrue(VALIDATOR.isTypeSentence(textVariant1));
        Assert.assertTrue(VALIDATOR.isTypeSentence(textVariant2));
        Assert.assertTrue(VALIDATOR.isTypeSentence(textVariant3));
        Assert.assertTrue(VALIDATOR.isTypeSentence(textVariant4));
        Assert.assertTrue(VALIDATOR.isTypeSentence(textVariant5));

    }

    @Test
    public void isTypeSentenceNegativeTest() {

        String textVariant1 = "\tIt has survived - not only (five) centuries";
        String textVariant2 = "\tIt! has survived - not only (five) centuries";
        String textVariant3 = "\tIt! has survived - not .5 only (five) centuries";
        String textVariant4 = "\tIt! has survived - not 5. only (five) centuries";
        String textVariant5 = "\tIt! has survived - not 5. \nonly (five) centuries";

        Assert.assertFalse(VALIDATOR.isTypeSentence(textVariant1));
        Assert.assertFalse(VALIDATOR.isTypeSentence(textVariant2));
        Assert.assertFalse(VALIDATOR.isTypeSentence(textVariant3));
        Assert.assertFalse(VALIDATOR.isTypeSentence(textVariant4));
        Assert.assertFalse(VALIDATOR.isTypeSentence(textVariant5));

    }

     @Test
    public void isTypeParagraphPositiveTest() {

        String textVariant1 = "\tIt has survived - not only (five) centuries, but also the leap into.\n";

        Assert.assertTrue(VALIDATOR.isTypeParagraph(textVariant1));
    }

    @Test
    public void isTypeParagraphNegativeTest() {

        String textVariant1 = "\tIt has survived - not only (five) centuries, but also the leap into.";
        String textVariant2 = "\tIt has survived - not only (five) centuries, but also the leap into.\n\n";

        Assert.assertFalse(VALIDATOR.isTypeParagraph(textVariant1));
        Assert.assertFalse(VALIDATOR.isTypeParagraph(textVariant2));
    }
}