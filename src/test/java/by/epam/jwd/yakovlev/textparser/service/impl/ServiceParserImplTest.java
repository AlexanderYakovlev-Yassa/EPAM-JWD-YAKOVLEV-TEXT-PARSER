package by.epam.jwd.yakovlev.textparser.service.impl;

import by.epam.jwd.yakovlev.textparser.entity.TextComponent;
import by.epam.jwd.yakovlev.textparser.entity.TextSymbol;
import by.epam.jwd.yakovlev.textparser.entity.TypeEnum;
import org.junit.Assert;
import org.junit.Test;

public class ServiceParserImplTest {

    public static final ServiceParserImpl PARSER = new ServiceParserImpl();

    public static final String TEXT = "\tIt has survived - not only (five) centuries, but also the leap into 13<<2 electronic type setting, remaining 3>>5 essentially ~6&9|(3&4) unchanged. It was popularised in the 5|(1&2&(3|(4&(6^5|6&47)|3)|2)|1) with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.\n" +
            "\tIt is a long established fact that a reader will be distracted by the readable content of a page when looking at its layout. The point of using (~71&(2&3|(3|(2&1>>2|2)&2)|10&2))|78 Ipsum is that it has a more-or-less normal distribution of letters, as opposed to using (Content here), content here', making it look like readable English.\n" +
            "\tIt is a (4^5|1&2<<(2|5>>2&71))|1200 established fact that a reader will be of a page when looking at its layout.\n" +
            "\tBye.\n";

    @Test
    public void buildSymbolPositiveTest() {

        String text = "s";

        TextComponent textComponent = PARSER.buildSymbol("s");

        Assert.assertNotNull(textComponent);
        Assert.assertEquals(textComponent.getType(), TypeEnum.SYMBOL);

        TextSymbol textSymbol = (TextSymbol) textComponent;

        Assert.assertEquals(textSymbol.getChar(), 's');
    }

    @Test
    public void buildWordPositiveTest() {

        String text = "survived";

        TextComponent textComponent = null;

        textComponent = PARSER.buildWord(text);

        Assert.assertNotNull(textComponent);

        Assert.assertEquals(textComponent.getType(), TypeEnum.WORD);

        Assert.assertTrue(textComponent.getStringRepresentation().equals(text));
    }

    @Test
    public void buildTokenPositiveTest() {

        String text = "(five)";

        TextComponent textComponent = null;


        textComponent = PARSER.buildToken(text);

        Assert.assertNotNull(textComponent);

        Assert.assertEquals(textComponent.getType(), TypeEnum.TOKEN);

        //printTextComponent(textComponent);

        Assert.assertEquals(textComponent.getComponentList().get(0).getType(), TypeEnum.SYMBOL);
        Assert.assertTrue(textComponent.getComponentList().get(0).getStringRepresentation().equals("("));
        Assert.assertEquals(textComponent.getComponentList().get(1).getType(), TypeEnum.WORD);
        Assert.assertTrue(textComponent.getComponentList().get(1).getStringRepresentation().equals("five"));
        Assert.assertEquals(textComponent.getComponentList().get(2).getType(), TypeEnum.SYMBOL);
        Assert.assertTrue(textComponent.getComponentList().get(2).getStringRepresentation().equals(")"));


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

        Assert.assertEquals(textComponent.getType(), TypeEnum.NUMBER);

        Assert.assertTrue(textComponent.getStringRepresentation().equals(text));
    }

    @Test
    public void buildEquationPositiveTest() {

        String text1 = "5|(1&2&(3|(4&(6^5|6&47)|3)|2)|1)";

        TextComponent textComponent = PARSER.buildEquation(text1);

        Assert.assertNotNull(textComponent);

        //printTextComponent(textComponent);

        Assert.assertTrue(textComponent.getStringRepresentation().equals(text1));
    }

    @Test
    public void buildSentencePositiveTest() {

        String text = "It has survived - not only (five) centuries, but also the leap into 13<<2 electronic type setting, remaining 3>>5 essentially ~6&9|(3&4) unchanged.";

        TextComponent textComponent = null;

        textComponent = PARSER.buildSentence(text);

        Assert.assertNotNull(textComponent);

        Assert.assertEquals(textComponent.getType(), TypeEnum.SENTENCE);

        Assert.assertTrue(textComponent.getStringRepresentation().equals(text));

        //printTextComponent(textComponent);
    }

    @Test
    public void buildParagraphPositiveTest() {

        String text = "\tIt has survived - not only (five) centuries, but also the leap into 13<<2 electronic type setting, remaining 3>>5 essentially ~6&9|(3&4) unchanged. It was popularised in the 5|(1&2&(3|(4&(6^5|6&47)|3)|2)|1) with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.\n";

        TextComponent textComponent = null;

        textComponent = PARSER.buildParagraph(text);

        Assert.assertNotNull(textComponent);

        Assert.assertEquals(textComponent.getType(), TypeEnum.PARAGRAPH);

        Assert.assertTrue(textComponent.getStringRepresentation().equals(text));

        //printTextComponent(textComponent);
    }

    @Test
    public void buildTextPositiveTest() {

        String text = TEXT;

        TextComponent textComponent = null;

        textComponent = PARSER.buildText(text);

        Assert.assertNotNull(textComponent);

        Assert.assertEquals(textComponent.getType(), TypeEnum.TEXT);

        Assert.assertTrue(textComponent.getStringRepresentation().equals(text));

        //printTextComponent(textComponent);
    }

    @Test
    public void buildTextPositive2Test() {

        String text = "Hi!";

        TextComponent textComponent = null;

        textComponent = PARSER.buildText(text);

        Assert.assertNotNull(textComponent);

        //printTextComponent(textComponent);

        Assert.assertEquals(textComponent.getType(), TypeEnum.TEXT);

        Assert.assertTrue(textComponent.getComponentList().size() == 1);

        Assert.assertEquals(textComponent.getComponentList().get(0).getType(), TypeEnum.SENTENCE);

        Assert.assertTrue(textComponent.getStringRepresentation().equals(text));
    }

    @Test
    public void buildTextPositive3Test() {

        String text = "Hi!\n";

        TextComponent textComponent = null;

        textComponent = PARSER.buildText(text);

        Assert.assertNotNull(textComponent);

        //printTextComponent(textComponent);

        Assert.assertEquals(textComponent.getType(), TypeEnum.TEXT);

        Assert.assertTrue(textComponent.getComponentList().size() == 1);

        Assert.assertEquals(textComponent.getComponentList().get(0).getType(), TypeEnum.PARAGRAPH);

        Assert.assertTrue(textComponent.getStringRepresentation().equals(text));
    }

    @Test
    public void buildTextPositive4Test() {

        String text = "Self-extractor";

        TextComponent textComponent = null;

        textComponent = PARSER.buildText(text);

        Assert.assertNotNull(textComponent);

        //printTextComponent(textComponent);

        Assert.assertEquals(textComponent.getType(), TypeEnum.TEXT);

        Assert.assertTrue(textComponent.getComponentList().size() == 1);

        Assert.assertEquals(textComponent.getComponentList().get(0).getType(), TypeEnum.TOKEN);

        Assert.assertTrue(textComponent.getStringRepresentation().equals(text));
    }

    @Test
    public void buildTextPositive5Test() {

        String text = "S";

        TextComponent textComponent = null;

        textComponent = PARSER.buildText(text);

        Assert.assertNotNull(textComponent);

        printTextComponent(textComponent);

        Assert.assertEquals(textComponent.getType(), TypeEnum.TEXT);

        Assert.assertTrue(textComponent.getComponentList().size() == 1);

        Assert.assertEquals(textComponent.getComponentList().get(0).getType(), TypeEnum.SYMBOL);

        Assert.assertTrue(textComponent.getStringRepresentation().equals(text));
    }

    private void printTextComponent(TextComponent textComponent) {

        if (textComponent.getComponentList().size() == 0) {
            System.out.println("There are no components!!!");
        }

        String symbol;
        for (TextComponent tc : textComponent.getComponentList()) {
            symbol = tc.getStringRepresentation();
            if (symbol.equals("\n")) {
                symbol = "\"\\n\"";
            }
            if (symbol.equals("\t")) {
                symbol = "\"\\t\"";
            }
            if (symbol.equals(" ")) {
                symbol = "\" \"";
            }
            System.out.println("type - " + tc.getType().name() + "  value - " + symbol);
        }
    }
}