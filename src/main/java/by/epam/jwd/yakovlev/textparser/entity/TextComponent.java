package by.epam.jwd.yakovlev.textparser.entity;

import by.epam.jwd.yakovlev.textparser.entity.exception.NotCompatibleType;

import javax.naming.OperationNotSupportedException;
import java.util.ArrayList;

public interface TextComponent {

    TextComponent processTextComponent(TextComponent textComponent) throws OperationNotSupportedException;

    void append(TextComponent textComponent) throws OperationNotSupportedException;

    void add(int index, TextComponent textComponent) throws NotCompatibleType, OperationNotSupportedException;

    boolean remove(Object o) throws OperationNotSupportedException;

    TextComponent remove(int index) throws OperationNotSupportedException;

    ArrayList<TextComponent> getComponents() throws OperationNotSupportedException;

    TextComponent getComponents(int index) throws OperationNotSupportedException;

    TextComponentTypesEnum getType();

    //TextComponent getSymbol(char character) throws OperationNotSupportedException;

    String getStringRepresentation();

}
