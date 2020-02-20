package by.epam.jwd.yakovlev.textparser.entity;

import javax.naming.OperationNotSupportedException;
import java.util.ArrayList;
import java.util.HashSet;

public class TextSymbol implements TextComponent {

    private static final HashSet<TextSymbol> symbolSet = new HashSet<>();

    private TypeEnum type = TypeEnum.SYMBOL;
    private char symbol;

    private TextSymbol(char symbol) {
        this.symbol = symbol;
    }

    @Override
    public TextComponent processTextComponent(TextComponent textComponent) throws OperationNotSupportedException {
        throw new OperationNotSupportedException("Can't process symbol!!!");
    }

    @Override
    public ArrayList<TextComponent> getComponentList() {
        ArrayList<TextComponent> list = new ArrayList<>();
        list.add(this);
        return list;
        //throw new OperationNotSupportedException("Can't return list of text components!!!");
    }

    @Override
    public TypeEnum getType() {
        return type;
    }

    public static TextSymbol getSymbol(char character) {

        TextSymbol textSymbol = null;

        for (TextSymbol tc : symbolSet){
            if (tc.getChar() == character){
                textSymbol = tc;
            }
        }

        if (textSymbol == null) {
            textSymbol = new TextSymbol(character);
            symbolSet.add(textSymbol);
        }

        return textSymbol;
    }

    @Override
    public String getStringRepresentation() {
        return String.valueOf(symbol);
    }

    public char getChar(){
        return this.symbol;
    }
}
