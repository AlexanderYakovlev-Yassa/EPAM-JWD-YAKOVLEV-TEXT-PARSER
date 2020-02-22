package by.epam.jwd.yakovlev.textparser.entity.comparator;

import by.epam.jwd.yakovlev.textparser.entity.TextComponent;
import by.epam.jwd.yakovlev.textparser.entity.TypeEnum;

import java.util.Comparator;

public class ByNumberOfWord implements Comparator<TextComponent> {

    @Override
    public int compare(TextComponent o1, TextComponent o2) {

        int firstWordNumber = o1.getComponentsOfType(TypeEnum.WORD).size();
        int secondWordNumber = o2.getComponentsOfType(TypeEnum.WORD).size();

        return firstWordNumber - secondWordNumber;
    }
}
