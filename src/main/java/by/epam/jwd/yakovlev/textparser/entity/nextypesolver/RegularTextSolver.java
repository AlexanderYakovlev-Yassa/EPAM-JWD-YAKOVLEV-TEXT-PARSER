package by.epam.jwd.yakovlev.textparser.entity.nextypesolver;

import by.epam.jwd.yakovlev.textparser.entity.TypeEnum;
import by.epam.jwd.yakovlev.textparser.entity.exception.WrongType;

public class RegularTextSolver implements NextTypeSolver {
    @Override
    public TypeEnum getNextType(TypeEnum type, String rawText) {

            switch (type) {
                case TEXT: {
                    return TypeEnum.PARAGRAPH;
                }
                case PARAGRAPH: {
                    return TypeEnum.SENTENCE;
                }
                case SENTENCE: {
                    return TypeEnum.TOKEN;
                }
                case TOKEN: {
                    return TypeEnum.WORD;
                }
                case WORD: {
                    return TypeEnum.SYMBOL;
                }
                default: {
                    throw new WrongType("This component has no next text type yet!!!");
                }
            }
    }
}
