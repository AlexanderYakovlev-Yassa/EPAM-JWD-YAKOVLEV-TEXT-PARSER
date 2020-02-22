package by.epam.jwd.yakovlev.textparser.validator;

import by.epam.jwd.yakovlev.textparser.entity.TypeEnum;

public class Validator {

    public boolean isConvertibleIntoType(TypeEnum type, String snippet){

       return type.getPattern().matcher(snippet).matches();
    }
}
