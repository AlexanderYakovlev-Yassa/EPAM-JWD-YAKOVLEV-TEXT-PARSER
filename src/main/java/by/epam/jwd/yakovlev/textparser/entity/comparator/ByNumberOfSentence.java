package by.epam.jwd.yakovlev.textparser.entity.comparator;

import by.epam.jwd.yakovlev.textparser.entity.TextComponent;
import by.epam.jwd.yakovlev.textparser.entity.TypeEnum;

import java.util.Comparator;

public class ByNumberOfSentence implements Comparator<TextComponent> {

    @Override
    public int compare(TextComponent o1, TextComponent o2) {

        int firstSentenceNumber = o1.getComponentPartsListOfType(TypeEnum.SENTENCE).size();
        int secondSentenceNumber = o2.getComponentPartsListOfType(TypeEnum.SENTENCE).size();

        return firstSentenceNumber - secondSentenceNumber;
    }
}
