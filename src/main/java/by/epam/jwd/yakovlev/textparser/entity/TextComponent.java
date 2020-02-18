package by.epam.jwd.yakovlev.textparser.entity;

import by.epam.jwd.yakovlev.textparser.entity.exception.NotCompatibleType;

import java.util.ArrayList;

public interface TextComponent {

    TextComponent processTextComponent(TextComponent textComponent);

    void append(TextComponent textComponent) throws NotCompatibleType;

    void add(int index, TextComponent textComponent) throws NotCompatibleType;

    boolean remove(Object o);

    TextComponent remove(int index);

    String getText();

    ArrayList<TextComponent> getComponents();

    TextComponentType getType();


}
