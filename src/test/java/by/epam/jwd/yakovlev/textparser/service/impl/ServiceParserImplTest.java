package by.epam.jwd.yakovlev.textparser.service.impl;

import by.epam.jwd.yakovlev.textparser.entity.TextComponent;
import by.epam.jwd.yakovlev.textparser.entity.TextComponentTypesEnum;
import by.epam.jwd.yakovlev.textparser.entity.TextSymbol;
import org.junit.Assert;
import org.junit.Test;

import javax.naming.OperationNotSupportedException;

public class ServiceParserImplTest {

    public static final ServiceParserImpl PARSER = new ServiceParserImpl();

    public static final String TEXT = "\tIt has survived - not only (five) centuries, but also the leap into 13<<2 electronic type setting, remaining 3>>5 essentially ~6&9|(3&4) unchanged. It was popularised in the 5|(1&2&(3|(4&(6^5|6&47)|3)|2)|1) with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.\n" +
            "\tIt is a long established fact that a reader will be distracted by the readable content of a page when looking at its layout. The point of using (~71&(2&3|(3|(2&1>>2|2)&2)|10&2))|78 Ipsum is that it has a more-or-less normal distribution of letters, as opposed to using (Content here), content here', making it look like readable English.\n" +
            "\tIt is a (4^5|1&2<<(2|5>>2&71))|1200 established fact that a reader will be of a page when looking at its layout.\n" +
            "\tBye.\n";

    public static final String FIRST_PARAGRAPH = "\tIt has survived - not only (five) centuries, but also the leap into 13<<2 electronic type setting, remaining 3>>5 essentially ~6&9|(3&4) unchanged. It was popularised in the 5|(1&2&(3|(4&(6^5|6&47)|3)|2)|1) with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.";
    public static final String SECOND_PARAGRAPH = "\tIt is a long established fact that a reader will be distracted by the readable content of a page when looking at its layout. The point of using (~71&(2&3|(3|(2&1>>2|2)&2)|10&2))|78 Ipsum is that it has a more-or-less normal distribution of letters, as opposed to using (Content here), content here', making it look like readable English.";
    public static final String THIRD_PARAGRAPH = "\tIt is a (4^5|1&2<<(2|5>>2&71))|1200 established fact that a reader will be of a page when looking at its layout.";
    public static final String FORTH_PARAGRAPH = "\tBye.";


    @Test
    public void buildSymbolPositiveTest() {

        String text = "s";

        TextComponent textComponent = PARSER.buildSymbol("s");

        Assert.assertNotNull(textComponent);
        Assert.assertEquals(textComponent.getType(), TextComponentTypesEnum.SYMBOL);

        TextSymbol textSymbol = (TextSymbol) textComponent;

        Assert.assertEquals(textSymbol.getChar(), 's');
    }

    @Test
    public void buildWordPositiveTest() {

        String text = "survived";

        TextComponent textComponent = null;

        textComponent = PARSER.buildWord(text);

        Assert.assertNotNull(textComponent);

        Assert.assertEquals(textComponent.getType(), TextComponentTypesEnum.WORD);

        Assert.assertTrue(textComponent.getStringRepresentation().equals(text));
    }

    @Test
    public void buildTokenPositiveTest() {

        String text = "(five)";

        TextComponent textComponent = null;


        textComponent = PARSER.buildToken(text);

        Assert.assertNotNull(textComponent);

        Assert.assertEquals(textComponent.getType(), TextComponentTypesEnum.TOKEN);

        //printTextComponent(textComponent);

        try {
            Assert.assertEquals(textComponent.getComponents(0).getType(), TextComponentTypesEnum.SYMBOL);
            Assert.assertTrue(textComponent.getComponents(0).getStringRepresentation().equals("("));
            Assert.assertEquals(textComponent.getComponents(1).getType(), TextComponentTypesEnum.WORD);
            Assert.assertTrue(textComponent.getComponents(1).getStringRepresentation().equals("five"));
            Assert.assertEquals(textComponent.getComponents(2).getType(), TextComponentTypesEnum.SYMBOL);
            Assert.assertTrue(textComponent.getComponents(2).getStringRepresentation().equals(")"));
        } catch (OperationNotSupportedException e) {
            Assert.fail(e.getMessage());
        }

        Assert.assertTrue(textComponent.getStringRepresentation().equals(text));
    }

    @Test
    public void buildTokenEquationTypePositiveTest() {

        String text = "(4^5|1&2<<(2|5>>2&71))|1200";

        TextComponent textComponent = null;

        textComponent = PARSER.buildToken(text);

        textComponent = PARSER.buildToken(text);
    }


    @Test
    public void buildNumberPositiveTest() {

        String text = "356.58";

        TextComponent textComponent = null;

        textComponent = PARSER.buildNumber(text);

        Assert.assertNotNull(textComponent);

        Assert.assertEquals(textComponent.getType(), TextComponentTypesEnum.NUMBER);

        Assert.assertTrue(textComponent.getStringRepresentation().equals(text));
    }

    @Test
    public void buildNumberNegativeTest() {

        String text1 = "5|(1&2&(3|(4&(6^5|6&47)|3)|2)|1)";

        TextComponent textComponent = PARSER.buildEquation(text1);

        Assert.assertNotNull(textComponent);

        Assert.assertTrue(textComponent.getStringRepresentation().equals(text1));
    }

    @Test
    public void buildEquationPositiveTest() {

        String text = "356.58";

        TextComponent textComponent = null;

        textComponent = PARSER.buildNumber(text);

        Assert.assertNotNull(textComponent);

        Assert.assertEquals(textComponent.getType(), TextComponentTypesEnum.NUMBER);

        Assert.assertTrue(textComponent.getStringRepresentation().equals(text));
    }

    @Test
    public void buildSentencePositiveTest() {

        String text = "It has survived - not only (five) centuries, but also the leap into 13<<2 electronic type setting, remaining 3>>5 essentially ~6&9|(3&4) unchanged.";

        TextComponent textComponent = null;

        textComponent = PARSER.buildSentence(text);

        Assert.assertNotNull(textComponent);

        Assert.assertEquals(textComponent.getType(), TextComponentTypesEnum.SENTENCE);

        Assert.assertTrue(textComponent.getStringRepresentation().equals(text));

        //printTextComponent(textComponent);
    }

    @Test
    public void buildParagraphPositiveTest() {

        String text = "\tIt has survived - not only (five) centuries, but also the leap into 13<<2 electronic type setting, remaining 3>>5 essentially ~6&9|(3&4) unchanged. It was popularised in the 5|(1&2&(3|(4&(6^5|6&47)|3)|2)|1) with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.\n";

        TextComponent textComponent = null;

        textComponent = PARSER.buildParagraph(text);

        Assert.assertNotNull(textComponent);

        Assert.assertEquals(textComponent.getType(), TextComponentTypesEnum.PARAGRAPH);

        Assert.assertTrue(textComponent.getStringRepresentation().equals(text));

        //printTextComponent(textComponent);
    }

    @Test
    public void buildTextPositiveTest() {

        String text = TEXT;

        TextComponent textComponent = null;

        textComponent = PARSER.buildText(text);

        Assert.assertNotNull(textComponent);

        Assert.assertEquals(textComponent.getType(), TextComponentTypesEnum.TEXT);

        Assert.assertTrue(textComponent.getStringRepresentation().equals(text));

        //printTextComponent(textComponent);

        //System.out.println(textComponent.getStringRepresentation());
    }

    private void printTextComponent(TextComponent textComponent) {

        try {
            String symbol;
            for (TextComponent tc : textComponent.getComponents()) {
                symbol = tc.getStringRepresentation();
                if (symbol.equals("\n")){
                    symbol = "\"\\n\"";
                }
                if (symbol.equals("\t")){
                    symbol = "\"\\t\"";
                }
                if (symbol.equals(" ")){
                    symbol = "\" \"";
                }
                System.out.println("type - " + tc.getType().name() + "  value - " + symbol);
            }
        } catch (OperationNotSupportedException e) {
            System.out.println(e.getMessage());
        }
    }
}