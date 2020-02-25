package by.epam.jwd.yakovlev.textparser.entity.comparator;

import by.epam.jwd.yakovlev.textparser.entity.TextComponent;

import java.util.Comparator;

public class ByWordLength implements Comparator<TextComponent> {

    @Override
    public int compare(TextComponent o1, TextComponent o2) {

        return o1.getComponentPartsList().size() - o2.getComponentPartsList().size();
    }
}
