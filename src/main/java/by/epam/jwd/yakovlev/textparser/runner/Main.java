package by.epam.jwd.yakovlev.textparser.runner;

import by.epam.jwd.yakovlev.textparser.dao.exception.DAOLogicException;
import by.epam.jwd.yakovlev.textparser.entity.TextComponent;
import by.epam.jwd.yakovlev.textparser.entity.TypeEnum;
import by.epam.jwd.yakovlev.textparser.entity.nextypesolver.NextTypeSolver;
import by.epam.jwd.yakovlev.textparser.entity.nextypesolver.RegularTextSolver;
import by.epam.jwd.yakovlev.textparser.parser.Parser;
import by.epam.jwd.yakovlev.textparser.service.ServiceFactory;
import by.epam.jwd.yakovlev.textparser.service.ServiceLogic;

import java.util.List;

public class Main {

    private static final ServiceLogic LOGIC = ServiceFactory.getINSTANCE().getServiceLogic();
    private static final Parser PARSER = ServiceFactory.getINSTANCE().getParser();

    public static void main(String[] args) {

        String TEXT = null;
        try {
            TEXT = LOGIC.loadText("src\\main\\resources\\SourceText.txt");
        } catch (DAOLogicException e) {
            System.out.println("Can't load text from file. " + e.getMessage());
        }

        NextTypeSolver regularSolver = new RegularTextSolver();

        TextComponent text = PARSER.buildComponentOf(TypeEnum.TEXT, TEXT, regularSolver);

        System.out.println("Paragraphs sorted by number of sentences:\n");

        List<TextComponent> list = LOGIC.paragraphsSortedByNumberOfSentences(text);

        for (TextComponent tc : list){
            System.out.println(tc.getStringRepresentation());
        }

        System.out.println("Sentence with words sorted by length:\n");

        TextComponent sentence = text.getComponentsOfType(TypeEnum.SENTENCE).get(3);
        list = LOGIC.wordsSortedByLength(sentence);

        for (TextComponent tc : list){
            System.out.println(tc.getStringRepresentation());
        }

        System.out.println("Paragraph with sentences sorted by the number of words:\n");

        TextComponent paragraph = text.getComponentsOfType(TypeEnum.PARAGRAPH).get(1);
        list = LOGIC.sentencesSortedByNumberOfWords(paragraph);

        for (TextComponent tc : list){
            System.out.println(tc.getStringRepresentation());
        }
    }
}
