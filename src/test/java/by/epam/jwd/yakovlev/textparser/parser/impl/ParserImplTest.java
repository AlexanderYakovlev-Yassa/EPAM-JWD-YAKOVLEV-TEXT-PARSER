package by.epam.jwd.yakovlev.textparser.parser.impl;

import by.epam.jwd.yakovlev.textparser.entity.TypeEnum;
import by.epam.jwd.yakovlev.textparser.parser.Parser;
import by.epam.jwd.yakovlev.textparser.service.impl.ServiceLogicImpl;
import by.epam.jwd.yakovlev.textparser.validator.Validator;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class ParserImplTest {

    public static final Parser LOGIC = new ParserImpl();
    public static final Validator VALIDATOR = new Validator();
    public static final String TEXT = "It has survived - not only (five) centuries, but also the leap into 13<<2 electronic type setting, remaining 3>>5 essentially ~6&9|(3&4) unchanged. It was popularised in the 5|(1&2&(3|(4&(6^5|6&47)|3)|2)|1) with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.\n" +
            "\tIt is a long established fact that a reader will be distracted by the readable content of a page when looking at its layout. The point of using (~71&(2&3|(3|(2&1>>2|2)&2)|10&2))|78 Ipsum is that it has a more-or-less normal distribution of letters, as opposed to using (Content here), content here', making it look like readable English.\n" +
            "\tIt is a (4^5|1&2<<(2|5>>2&71))|1200 established fact that a reader will be of a page when looking at its layout.\n" +
            "\tBye.\n";

    @Test
    public void splitIntoSymbolPositiveTest() {

        String snippet = "stress";

        List<String> newSnippets = LOGIC.splitInto(TypeEnum.SYMBOL, snippet);

        Assert.assertNotNull(newSnippets);

        Assert.assertTrue(newSnippets.size() == 6);

        Assert.assertTrue(newSnippets.get(0).equals("s"));
        Assert.assertTrue(newSnippets.get(1).equals("t"));
        Assert.assertTrue(newSnippets.get(2).equals("r"));
        Assert.assertTrue(newSnippets.get(3).equals("e"));
        Assert.assertTrue(newSnippets.get(4).equals("s"));
        Assert.assertTrue(newSnippets.get(5).equals("s"));

    }

    @Test
    public void splitIntoParagraphPositiveTest() {

        List<String> newSnippets = LOGIC.splitInto(TypeEnum.PARAGRAPH, TEXT);

        Assert.assertNotNull(newSnippets);

        Assert.assertTrue(newSnippets.size() == 4);

        Assert.assertTrue(newSnippets.get(0).equals("It has survived - not only (five) centuries, but also the leap into 13<<2 electronic type setting, remaining 3>>5 essentially ~6&9|(3&4) unchanged. It was popularised in the 5|(1&2&(3|(4&(6^5|6&47)|3)|2)|1) with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.\n"));
        Assert.assertTrue(newSnippets.get(1).equals("\tIt is a long established fact that a reader will be distracted by the readable content of a page when looking at its layout. The point of using (~71&(2&3|(3|(2&1>>2|2)&2)|10&2))|78 Ipsum is that it has a more-or-less normal distribution of letters, as opposed to using (Content here), content here', making it look like readable English.\n"));
        Assert.assertTrue(newSnippets.get(2).equals("\tIt is a (4^5|1&2<<(2|5>>2&71))|1200 established fact that a reader will be of a page when looking at its layout.\n"));
        Assert.assertTrue(newSnippets.get(3).equals("\tBye.\n"));

    }

    @Test
    public void splitIntoSentencePositiveTest() {

        String snippet = "\tIt is a long established fact that a reader will be distracted by the readable content of a page when looking at its layout. The point of using (~71&(2&3|(3|(2&1>>2|2)&2)|10&2))|78 Ipsum is that it has a more-or-less normal distribution of letters, as opposed to using (Content here), content here', making it look like readable English.\n";

        List<String> newSnippets = LOGIC.splitInto(TypeEnum.SENTENCE, snippet);

        Assert.assertNotNull(newSnippets);

        Assert.assertTrue(newSnippets.size() == 5);
        //printTextComponent(newSnippets);

        Assert.assertTrue(newSnippets.get(0).equals("\t"));
        Assert.assertTrue(newSnippets.get(1).equals("It is a long established fact that a reader will be distracted by the readable content of a page when looking at its layout."));
        Assert.assertTrue(newSnippets.get(2).equals(" "));
        Assert.assertTrue(newSnippets.get(3).equals("The point of using (~71&(2&3|(3|(2&1>>2|2)&2)|10&2))|78 Ipsum is that it has a more-or-less normal distribution of letters, as opposed to using (Content here), content here', making it look like readable English."));
        Assert.assertTrue(newSnippets.get(4).equals("\n"));
    }

    @Test
    public void splitIntoTokenPositiveTest() {

        String snippet = "It is a long of a page when (~71&(2&3|(3|(2&1>>2|2)&2)|10&2))|78 looking-forward at its layout.";

        List<String> newSnippets = LOGIC.splitInto(TypeEnum.TOKEN, snippet);

        Assert.assertNotNull(newSnippets);

        Assert.assertTrue(newSnippets.size() == 25);
        //printTextComponent(newSnippets);

        Assert.assertTrue(newSnippets.get(0).equals("It"));
        Assert.assertTrue(newSnippets.get(1).equals(" "));
        Assert.assertTrue(newSnippets.get(16).equals("(~71&(2&3|(3|(2&1>>2|2)&2)|10&2))|78"));
        Assert.assertTrue(newSnippets.get(18).equals("looking-forward"));
        Assert.assertTrue(newSnippets.get(24).equals("layout."));
    }

    @Test
    public void splitIntoWordPositiveTest() {

        String snippet = "looking-forward";

        List<String> newSnippets = LOGIC.splitInto(TypeEnum.WORD, snippet);

        Assert.assertNotNull(newSnippets);

        Assert.assertTrue(newSnippets.size() == 3);

        //printTextComponent(newSnippets);

        Assert.assertTrue(newSnippets.get(0).equals("looking"));
        Assert.assertTrue(newSnippets.get(1).equals("-"));
        Assert.assertTrue(newSnippets.get(2).equals("forward"));
    }

    @Test
    public void splitIntoEquationPositiveTest() {

        String snippet = "(~71&(2&3|(3|(2&1>>2|2)&2)|10&2))|78";

        List<String> newTokens = LOGIC.splitInto(TypeEnum.TOKEN, snippet);

        printTextComponent(newTokens);

        snippet = newTokens.get(0);

        List<String> newSnippets = LOGIC.splitInto(TypeEnum.EQUATION, snippet);

        printTextComponent(newSnippets);

        //List<String> newSnippets = LOGIC.splitInto(TypeEnum.EQUATION, snippet);

        Assert.assertNotNull(newSnippets);

        Assert.assertTrue(newSnippets.size() == 1);

        Assert.assertTrue(newSnippets.get(0).equals("(~71&(2&3|(3|(2&1>>2|2)&2)|10&2))|78"));
    }

    private void printTextComponent(List<String> newSnippets) {

        if (newSnippets.size() == 0) {
            System.out.println("There are no snippets!!!");
        }

        String symbol;
        for (String s : newSnippets) {

            if (s.equals("\n")) {
                s = "\"\\n\"";
            }
            if (s.equals("\t")) {
                s = "\"\\t\"";
            }
            if (s.equals(" ")) {
                s = "\" \"";
            }
            System.out.println(s);
        }
    }
}