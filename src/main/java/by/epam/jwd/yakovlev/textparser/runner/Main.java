package by.epam.jwd.yakovlev.textparser.runner;

import by.epam.jwd.yakovlev.textparser.dao.exception.DAOLogicException;
import by.epam.jwd.yakovlev.textparser.entity.TextComponent;
import by.epam.jwd.yakovlev.textparser.entity.TypeEnum;
import by.epam.jwd.yakovlev.textparser.service.Parser;
import by.epam.jwd.yakovlev.textparser.service.ServiceFactory;
import by.epam.jwd.yakovlev.textparser.service.ServiceLogic;

import java.util.List;

public class Main {

    private static final ServiceLogic LOGIC = ServiceFactory.getINSTANCE().getServiceLogic();

    public static void main(String[] args) {

        String TEXT = null;
        try {
            TEXT = LOGIC.loadText("src\\main\\resources\\SourceText.txt");
        } catch (DAOLogicException e) {
            System.out.println("Can't load text from file. " + e.getMessage());
        }

        Parser parserSymbol = new Parser(TypeEnum.SYMBOL);
        Parser parserWord = new Parser(TypeEnum.WORD, parserSymbol);
        Parser parserToken = new Parser(TypeEnum.TOKEN, parserWord);
        Parser parserSentence = new Parser(TypeEnum.SENTENCE, parserToken);
        Parser parserParagraph = new Parser(TypeEnum.PARAGRAPH, parserSentence);
        Parser parser = new Parser(TypeEnum.TEXT, parserParagraph);

        TextComponent text = parser.parse(TEXT);

        System.out.println("\nThe whole reconstructed text:\n");
        System.out.println(text);
        System.out.println("\nParagraphs sorted by number of sentences:\n");

        List<TextComponent> list = LOGIC.paragraphsSortedByNumberOfSentences(text);

        for (TextComponent tc : list){
            System.out.println(tc.toString());
        }

        System.out.println("\nSentence with words sorted by length:\n");

        TextComponent sentence = text.getComponentPartsListOfType(TypeEnum.SENTENCE).get(3);
        list = LOGIC.wordsSortedByLength(sentence);

        for (TextComponent tc : list){
            System.out.print(tc.toString() + " ");
        }

        System.out.println("\n\nParagraph with sentences sorted by the number of words:\n");

        TextComponent paragraph = text.getComponentPartsListOfType(TypeEnum.PARAGRAPH).get(1);
        list = LOGIC.sentencesSortedByNumberOfWords(paragraph);

        for (TextComponent tc : list){
            System.out.println(tc.toString());
        }
    }
}
