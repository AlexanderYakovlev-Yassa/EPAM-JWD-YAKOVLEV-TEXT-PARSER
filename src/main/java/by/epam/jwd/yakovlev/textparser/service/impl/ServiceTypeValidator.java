package by.epam.jwd.yakovlev.textparser.service.impl;

public class ServiceTypeValidator {

    public boolean isTypeWord(String string){

        if (string == null){
            return false;
        }

        return TypeEnum.WORD.getPattern().matcher(string).matches();
    }

    public boolean isTypeSymbol(String string){

        if (string == null){
            return false;
        }

        return  (string.length() == 1);
    }

    public boolean isTypeNumber(String string){

        return TypeEnum.NUMBER.getPattern().matcher(string).matches();
    }

    public boolean isTypeEquation(String string){

        return TypeEnum.EQUATION.getPattern().matcher(string).matches();
    }

    public boolean isTypeToken(String string){

        if (string == null){
            return false;
        }

        return TypeEnum.TOKEN.getPattern().matcher(string).matches();
    }

    public boolean isTypeSentence(String string){

        if (string == null){
            return false;
        }

        return TypeEnum.SENTENCE.getPattern().matcher(string).matches();
    }

    public boolean isTypeParagraph(String string){

        if (string == null){
            return false;
        }

        return TypeEnum.PARAGRAPH.getPattern().matcher(string).matches();
    }

}
