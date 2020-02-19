package by.epam.jwd.yakovlev.textparser.entity;


import by.epam.jwd.yakovlev.textparser.entity.exception.NotCompatibleType;

import javax.naming.OperationNotSupportedException;
import java.util.ArrayList;
import java.util.HashSet;

public class TextSymbol implements TextComponent {

    private static final HashSet<TextSymbol> symbolSet = new HashSet<>();

    private TextComponentTypesEnum type = TextComponentTypesEnum.SYMBOL;
    private char symbol;

    private TextSymbol(char symbol) {
        this.symbol = symbol;
    }

    @Override
    public TextComponent processTextComponent(TextComponent textComponent) throws OperationNotSupportedException {
        throw new OperationNotSupportedException("Can't process symbol!!!");
    }

    @Override
    public void append(TextComponent textComponent) throws OperationNotSupportedException {
        throw new OperationNotSupportedException("Can't append to symbol!!!");
    }

    @Override
    public void add(int index, TextComponent textComponent) throws OperationNotSupportedException {
        throw new OperationNotSupportedException("Can't add to symbol!!!");
    }

    @Override
    public boolean remove(Object o) throws OperationNotSupportedException {
        throw new OperationNotSupportedException("Can't remove text component!!!");
    }

    @Override
    public TextComponent remove(int index) throws OperationNotSupportedException {
        throw new OperationNotSupportedException("Can't remove text component!!!");
    }

    @Override
    public ArrayList<TextComponent> getComponents() throws OperationNotSupportedException {
        throw new OperationNotSupportedException("Can't return list of text components!!!");
    }

    @Override
    public TextComponent getComponents(int index) throws OperationNotSupportedException {
        throw new OperationNotSupportedException("Can't return component!!!");
    }

    @Override
    public TextComponentTypesEnum getType() {
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
