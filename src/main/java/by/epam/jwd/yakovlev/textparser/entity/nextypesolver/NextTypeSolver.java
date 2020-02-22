package by.epam.jwd.yakovlev.textparser.entity.nextypesolver;

import by.epam.jwd.yakovlev.textparser.entity.TypeEnum;

public interface NextTypeSolver {

    public TypeEnum getNextType(TypeEnum type, String rawText);
}
