package by.epam.jwd.yakovlev.textparser.parser;

import by.epam.jwd.yakovlev.textparser.entity.TextComponent;
import by.epam.jwd.yakovlev.textparser.entity.TypeEnum;
import by.epam.jwd.yakovlev.textparser.entity.nextypesolver.NextTypeSolver;

import java.util.List;

public interface Parser {

    public TextComponent buildComponentOf(TypeEnum type, String rawText, NextTypeSolver solver);
    public List<String> splitInto(TypeEnum type, String sourceSnippet);

}
