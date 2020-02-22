package by.epam.jwd.yakovlev.textparser.validator;

import by.epam.jwd.yakovlev.textparser.entity.TypeEnum;
import org.junit.Assert;
import org.junit.Test;

public class ValidatorTest {

    public static final Validator VALIDATOR = new Validator();
    public static final String TEXT = "It has survived - not only (five) centuries, but also the leap into 13<<2 electronic type setting, remaining 3>>5 essentially ~6&9|(3&4) unchanged. It was popularised in the 5|(1&2&(3|(4&(6^5|6&47)|3)|2)|1) with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.\n" +
            "\tIt is a long established fact that a reader will be distracted by the readable content of a page when looking at its layout. The point of using (~71&(2&3|(3|(2&1>>2|2)&2)|10&2))|78 Ipsum is that it has a more-or-less normal distribution of letters, as opposed to using (Content here), content here', making it look like readable English.\n" +
            "\tIt is a (4^5|1&2<<(2|5>>2&71))|1200 established fact that a reader will be of a page when looking at its layout.\n" +
            "\tBye.\n";

    @Test
    public void isConvertibleIntoSentencePositiveTest() {

        String textVariant1 = "It has survived - not only (five) centuries.";
        String textVariant2 = "It has survived - not only (five) centuries?";
        String textVariant3 = "It has survived - not only (five) centuries!!!";
        String textVariant4 = "It has survived - not only (five) centuries?!";
        String textVariant5 = "It has survived - not 22.55 only (five) centuries?!";

        Assert.assertTrue(VALIDATOR.isConvertibleIntoType(TypeEnum.SENTENCE, textVariant1));
        Assert.assertTrue(VALIDATOR.isConvertibleIntoType(TypeEnum.SENTENCE, textVariant2));
        Assert.assertTrue(VALIDATOR.isConvertibleIntoType(TypeEnum.SENTENCE, textVariant3));
        Assert.assertTrue(VALIDATOR.isConvertibleIntoType(TypeEnum.SENTENCE, textVariant4));
        Assert.assertTrue(VALIDATOR.isConvertibleIntoType(TypeEnum.SENTENCE, textVariant5));

    }

    @Test
    public void isConvertibleIntoTextPositiveTest() {

        Assert.assertTrue(VALIDATOR.isConvertibleIntoType(TypeEnum.TEXT, TEXT));
    }

    @Test
    public void isConvertibleIntoEquationPositiveTest() {

        String snippet = "(~71&(2&3|(3|(2&1>>2|2)&2)|10&2))|78";
        Assert.assertTrue(VALIDATOR.isConvertibleIntoType(TypeEnum.EQUATION, snippet));
    }
}