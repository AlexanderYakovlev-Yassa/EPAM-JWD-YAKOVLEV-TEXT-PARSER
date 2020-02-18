package by.epam.jwd.yakovlev.textparser.entity;

public enum TextComponentType {

    TEXT (TextComponentType.PARAGRAPH),
    PARAGRAPH (TextComponentType.SENTENCE),
    SENTENCE (TextComponentType.TOKEN),
    TOKEN (TextComponentType.WORD),
    WORD (TextComponentType.SYMBOL),
    DIGIT_EQUATION (TextComponentType.SYMBOL),
    NUMBER (TextComponentType.SYMBOL),
    MATH_OPERATOR (TextComponentType.SYMBOL),
    SYMBOL (null);

    TextComponentType innerType;

    TextComponentType(TextComponentType innerType) {
        this.innerType = innerType;
    }

     public TextComponentType getInnerType(){
        return innerType;
     }
}
