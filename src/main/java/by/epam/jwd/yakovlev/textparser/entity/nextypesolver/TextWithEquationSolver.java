package by.epam.jwd.yakovlev.textparser.entity.nextypesolver;

import by.epam.jwd.yakovlev.textparser.entity.TypeEnum;
import by.epam.jwd.yakovlev.textparser.entity.exception.WrongType;
import by.epam.jwd.yakovlev.textparser.validator.Validator;

public class TextWithEquationSolver implements NextTypeSolver{

    public static final Validator VALIDATOR = new Validator();

    @Override
    public TypeEnum getNextType(TypeEnum type, String rawText) {

        TypeEnum nextType = null;

        switch (type) {
            case TEXT: {
                nextType = TypeEnum.PARAGRAPH;
                break;
            }
            case PARAGRAPH: {
                nextType = TypeEnum.SENTENCE;
                break;
            }
            case SENTENCE: {
                nextType =  TypeEnum.TOKEN;
                break;
            }
            case TOKEN: {
                if (VALIDATOR.isConvertibleIntoType(TypeEnum.EQUATION, rawText)){
                    nextType = TypeEnum.EQUATION;
                } else {
                    nextType = TypeEnum.WORD;
                }
                break;
            }
            case EQUATION: {
                nextType =  TypeEnum.FLOAT_POINT_NUMBER;
                break;
            }
            case WORD: {
                nextType =  TypeEnum.SYMBOL;
                break;
            }
            default: {
                throw new WrongType("This component has no next text type yet!!!");
            }
        }

        return nextType;
    }
}
